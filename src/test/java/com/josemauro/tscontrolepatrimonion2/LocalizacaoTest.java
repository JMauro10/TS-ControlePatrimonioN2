package com.josemauro.tscontrolepatrimonion2;

// Imports necessários (java.util.Optional, List, Assertions, etc.)
import com.josemauro.tscontrolepatrimonion2.entities.Localizacao;
import com.josemauro.tscontrolepatrimonion2.repository.LocalizacaoRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select; // Import para o Dropdown
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LocalizacaoTest {

    private WebDriver driver;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    private static String nomeTeste = "Local Teste Selenium";
    private static String descTeste = "Descricao Teste QA";
    private static String nomeEditado = "Local Teste Editado";

    @BeforeAll
    public void setUpClass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        this.driver = new ChromeDriver(options);
    }

    @Test
    @Order(1)
    public void testCadastro() throws InterruptedException {
        System.out.println("Teste de cadastro de localização iniciado.");
        this.driver.get("http://127.0.0.1:5501/html/home.html"); // Assumindo porta 5501
        Thread.sleep(2000);

        WebElement btnLocal = driver.findElement(By.id("btnCadastrarLocalizacao"));
        btnLocal.click();
        Thread.sleep(2000);

        WebElement nome = driver.findElement(By.id("btnCampoNomeLocalizacao"));
        WebElement descricao = driver.findElement(By.id("btnCampoDescricaoLocalizacao"));

        nome.sendKeys(nomeTeste);
        descricao.sendKeys(descTeste);
        System.out.println("Dados da localização preenchidos.");

        WebElement submitButton = driver.findElement(By.id("btnSalvarLocalizacao"));
        submitButton.click();
        Thread.sleep(2000);

        System.out.println("Verificando o banco de dados...");
        List<Localizacao> localList = localizacaoRepository.findByNome(nomeTeste);

        assertFalse(localList.isEmpty(), "Localização não foi encontrada no banco após o cadastro.");
        
        Localizacao localCadastrada = localList.get(0);
        assertEquals(nomeTeste, localCadastrada.getNome());
        assertEquals(descTeste, localCadastrada.getDescricao());
        
        System.out.println("Teste de cadastro concluído e verificado no banco.");
    }

    @Test
    @Order(2)
    public void testAlteracao() throws InterruptedException {
        System.out.println("Teste de alteração de Localização iniciado.");
        driver.get("http://127.0.0.1:5501/html/localizacao.html");
        Thread.sleep(2000);

        String xpathLi = String.format("//ul[@id='btnListaLocalizacoes']/li[contains(., '%s')]", nomeTeste);
        WebElement liLocal = driver.findElement(By.xpath(xpathLi));
        System.out.println("Localização original encontrada na lista.");

        WebElement selectAcao = liLocal.findElement(By.tagName("select"));
        new Select(selectAcao).selectByValue("editar");
        System.out.println("Modo de edição ativado.");
        Thread.sleep(1000);

        WebElement nomeInput = driver.findElement(By.id("btnCampoNomeLocalizacao"));
        nomeInput.clear();
        nomeInput.sendKeys(nomeEditado);
        System.out.println("Nome alterado para: " + nomeEditado);

        WebElement btnSalvar = driver.findElement(By.id("btnSalvarLocalizacao"));
        btnSalvar.click();
        System.out.println("Alterações salvas.");
        Thread.sleep(2000);

        System.out.println("Verificando o banco de dados...");
        List<Localizacao> localEditadaList = localizacaoRepository.findByNome(nomeEditado);
        assertFalse(localEditadaList.isEmpty(), "A localização com o nome editado não foi encontrada.");
        assertEquals(nomeEditado, localEditadaList.get(0).getNome());

        List<Localizacao> localAntigaList = localizacaoRepository.findByNome(nomeTeste);
        assertTrue(localAntigaList.isEmpty(), "A localização com o nome antigo ainda foi encontrada.");

        System.out.println("Teste de alteração concluído e verificado no banco.");
    }

    @Test
    @Order(3)
    public void testDelecao() throws InterruptedException {
        System.out.println("Teste de deleção de pessoa iniciado.");
        driver.get("http://127.0.0.1:5501/html/localizacao.html");
        Thread.sleep(2000);

        String xpathLi = String.format("//ul[@id='btnListaLocalizacoes']/li[contains(., '%s')]", nomeEditado);
        WebElement liLocal = driver.findElement(By.xpath(xpathLi));
        System.out.println("Localização (editada) encontrada na lista para deleção.");

        WebElement selectAcao = liLocal.findElement(By.tagName("select"));
        new Select(selectAcao).selectByValue("excluir");
        System.out.println("Modo de deleção ativado.");
        Thread.sleep(1000);

        WebElement btnConfirmar = driver.findElement(By.id("btnConfirmarExclusao"));
        btnConfirmar.click();
        System.out.println("Deleção confirmada.");
        Thread.sleep(2000);

        System.out.println("Verificando o banco de dados...");
        List<Localizacao> localDeletadaList = localizacaoRepository.findByNome(nomeEditado);

        assertTrue(localDeletadaList.isEmpty(), "A localização ainda foi encontrada no banco após a deleção.");

        System.out.println("Teste de deleção concluído e verificado no banco.");
    }
}