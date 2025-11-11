package com.josemauro.tscontrolepatrimonion2;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.josemauro.tscontrolepatrimonion2.entities.Patrimonio;
import com.josemauro.tscontrolepatrimonion2.entities.Categoria;
import com.josemauro.tscontrolepatrimonion2.entities.Localizacao;
import com.josemauro.tscontrolepatrimonion2.entities.Pessoa;
import com.josemauro.tscontrolepatrimonion2.repository.PatrimonioRepository;
import com.josemauro.tscontrolepatrimonion2.repository.CategoriaRepository;
import com.josemauro.tscontrolepatrimonion2.repository.LocalizacaoRepository;
import com.josemauro.tscontrolepatrimonion2.repository.PessoaRepository;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatrimonioTest {

    private WebDriver driver;

    @Autowired
    private PatrimonioRepository patrimonioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private LocalizacaoRepository localizacaoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    private static String qrCodeTeste = "PAT-SELENIUM-123";
    private static String tagTeste = "TAG-SELENIUM-456";
    private static String descTeste = "Notebook Teste Selenium";
    private static String descEditada = "Notebook Teste Editado";

    private static String helperCatNome = "Categoria Teste Selenium";
    private static String helperLocNome = "Localizacao Teste Selenium";
    private static String helperPessoaCpf = "999.999.999-99";

    private static Categoria helperCategoria;
    private static Localizacao helperLocalizacao;
    private static Pessoa helperPessoa;

    @AfterAll
    public void tearDownClass() {
        
        Optional<Patrimonio> patOpt = patrimonioRepository.findByQrcode(qrCodeTeste);
        patOpt.ifPresent(patrimonioRepository::delete);

        if (helperPessoa != null) { 
            pessoaRepository.deleteById(helperPessoa.getId());
        }
        if (helperLocalizacao != null) {
            localizacaoRepository.deleteById(helperLocalizacao.getId());
        }
        if (helperCategoria != null) {
            categoriaRepository.deleteById(helperCategoria.getId());
        }
        System.out.println("Limpeza de PatrimonioTest concluída.");
    }

    @BeforeAll
    public void setUpClass() {
        System.out.println("Iniciando limpeza pré-teste (Patrimonio)...");
        
        Optional<Patrimonio> oldPat = patrimonioRepository.findByQrcode(qrCodeTeste);
        oldPat.ifPresent(patrimonioRepository::delete);

        Optional<Pessoa> oldPessoa = pessoaRepository.findByCpf(helperPessoaCpf);
        oldPessoa.ifPresent(pessoaRepository::delete);

        List<Localizacao> oldLoc = localizacaoRepository.findByNome(helperLocNome);
        if (!oldLoc.isEmpty()) localizacaoRepository.deleteAll(oldLoc);
        
        List<Categoria> oldCat = categoriaRepository.findByNome(helperCatNome);
        if (!oldCat.isEmpty()) categoriaRepository.deleteAll(oldCat);
        System.out.println("Limpeza pré-teste concluída.");

        System.out.println("Criando novas dependências para Patrimônio...");
        
        helperCategoria = categoriaRepository.save(
            new Categoria(null, helperCatNome, "Categoria para testes de Selenium")
        );
        
        helperLocalizacao = localizacaoRepository.save(
            new Localizacao(null, helperLocNome, "Local para testes de Selenium", true)
        );

        helperPessoa = pessoaRepository.save(
            new Pessoa(null, "Pessoa Teste Helper", helperPessoaCpf, "pessoa@helper.com", "Depto Teste", "Cargo Teste", true)
        );

        System.out.println("Dependências criadas com IDs: " +
            "Cat=" + helperCategoria.getId() + ", " +
            "Loc=" + helperLocalizacao.getId() + ", " +
            "Pessoa=" + helperPessoa.getId());

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        this.driver = new ChromeDriver(options);
    }

    @Test
    @Order(1)
    public void testCadastro() throws InterruptedException {
        System.out.println("Teste de cadastro de patrimônio iniciado.");
        this.driver.get("http://127.0.0.1:5501/html/home.html");
        Thread.sleep(2000);

        // --- Ação do Selenium ---
        WebElement btnPatrimonio = driver.findElement(By.id("btnCadastrarPatrimonio"));
        btnPatrimonio.click();
        Thread.sleep(2000);

        driver.findElement(By.id("btnCampoDescricaoPatrimonio")).sendKeys(descTeste);
        driver.findElement(By.id("btnCampoQrCodePatrimonio")).sendKeys(qrCodeTeste);
        driver.findElement(By.id("btnCampoTagPatrimonio")).sendKeys(tagTeste);
        driver.findElement(By.id("btnCampoStatusPatrimonio")).sendKeys("ATIVO");
        driver.findElement(By.id("btnCampoDataAquisicaoPatrimonio")).sendKeys("10-01-2025"); 
        driver.findElement(By.id("btnCampoCustoPatrimonio")).sendKeys("1500.50");

        // Usar os IDs dos 'helpers'
        driver.findElement(By.id("btnCampoCategoriaIdPatrimonio")).sendKeys(String.valueOf(helperCategoria.getId()));
        driver.findElement(By.id("btnCampoLocalizacaoIdPatrimonio")).sendKeys(String.valueOf(helperLocalizacao.getId()));
        driver.findElement(By.id("btnCampoPessoaResponsavelIdPatrimonio")).sendKeys(String.valueOf(helperPessoa.getId()));

        System.out.println("Formulário de patrimônio preenchido.");
        driver.findElement(By.id("btnSalvarPatrimonio")).click();
        Thread.sleep(2000);

        // --- Verificação no Banco de Dados ---
        System.out.println("Verificando o banco de dados...");
        Optional<Patrimonio> patOpt = patrimonioRepository.findByQrcode(qrCodeTeste);

        assertTrue(patOpt.isPresent(), "Patrimônio não foi encontrado no banco (pelo QR Code) após o cadastro.");
        
        Patrimonio p = patOpt.get();
        assertEquals(descTeste, p.getDescricao());
        assertEquals(tagTeste, p.getTag());
        assertNotNull(p.getCategoria(), "Categoria não foi salva (está nula)");
        assertEquals(helperCategoria.getId(), p.getCategoria().getId(), "ID da Categoria está incorreto");
        assertNotNull(p.getLocalizacao(), "Localização não foi salva (está nula)");
        assertEquals(helperLocalizacao.getId(), p.getLocalizacao().getId(), "ID da Localização está incorreto");
        assertNotNull(p.getPessoaResponsavel(), "Pessoa Responsável não foi salva (está nula)");
        assertEquals(helperPessoa.getId(), p.getPessoaResponsavel().getId(), "ID da Pessoa está incorreto");

        System.out.println("Teste de cadastro de patrimônio concluído.");
    }

    @Test
    @Order(2)
    public void testAlteracao() throws InterruptedException {
        System.out.println("Teste de alteração de patrimônio iniciado.");
        driver.get("http://127.0.0.1:5501/html/patrimonio.html");
        Thread.sleep(2000); 

        // --- Ação do Selenium ---
        String xpathLi = String.format("//ul[@id='btnListaPatrimonios']/li[contains(., '%s')]", descTeste);
        WebElement liPat = driver.findElement(By.xpath(xpathLi));
        System.out.println("Patrimônio original encontrado na lista.");

        WebElement selectAcao = liPat.findElement(By.tagName("select"));
        new Select(selectAcao).selectByValue("editar");
        Thread.sleep(1000); 

        WebElement descInput = driver.findElement(By.id("btnCampoDescricaoPatrimonio"));
        descInput.clear();
        descInput.sendKeys(descEditada);
        System.out.println("Descrição alterada para: " + descEditada);

        driver.findElement(By.id("btnSalvarPatrimonio")).click();
        Thread.sleep(2000);

        // --- Verificação no Banco de Dados ---
        System.out.println("Verificando o banco de dados...");
        Optional<Patrimonio> patOpt = patrimonioRepository.findByQrcode(qrCodeTeste); 

        assertTrue(patOpt.isPresent(), "Patrimônio não encontrado no banco após a alteração.");
        
        Patrimonio p = patOpt.get();
        assertEquals(descEditada, p.getDescricao(), "A descrição no banco não foi atualizada.");
        assertEquals(tagTeste, p.getTag()); 
        
        System.out.println("Teste de alteração de patrimônio concluído.");
    }

    @Test
    @Order(3)
    public void testDelecao() throws InterruptedException {
        System.out.println("Teste de deleção de patrimônio iniciado.");
        driver.get("http://127.0.0.1:5501/html/patrimonio.html");
        Thread.sleep(2000); 

        // --- Ação do Selenium ---
        String xpathLi = String.format("//ul[@id='btnListaPatrimonios']/li[contains(., '%s')]", descEditada);
        WebElement liPat = driver.findElement(By.xpath(xpathLi));
        System.out.println("Patrimônio (editado) encontrado para deleção.");

        WebElement selectAcao = liPat.findElement(By.tagName("select"));
        new Select(selectAcao).selectByValue("excluir");
        Thread.sleep(1000); 

        WebElement btnConfirmar = driver.findElement(By.id("btnConfirmarExclusao"));
        btnConfirmar.click();
        System.out.println("Deleção confirmada.");
        Thread.sleep(2000);

        // --- Verificação no Banco de Dados ---
        System.out.println("Verificando o banco de dados...");
        Optional<Patrimonio> patOpt = patrimonioRepository.findByQrcode(qrCodeTeste);

        assertFalse(patOpt.isPresent(), "O patrimônio ainda foi encontrado no banco (pelo QR Code) após a deleção.");

        System.out.println("Teste de deleção de patrimônio concluído.");
    }
}