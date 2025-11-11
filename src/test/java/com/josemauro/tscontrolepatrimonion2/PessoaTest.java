package com.josemauro.tscontrolepatrimonion2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Assertions;
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

import com.josemauro.tscontrolepatrimonion2.entities.Pessoa;
import com.josemauro.tscontrolepatrimonion2.repository.PessoaRepository;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PessoaTest {

    private WebDriver driver;

    @Autowired
    private PessoaRepository pessoaRepository;

    private static String cpfTeste = "123.456.789-00";
    private static String nomeTeste = "Teste Selenium";
    private static String emailTeste = "teste@teste.com";
    private static String cargoTeste = "Teste QA";
    private static String nomeEditado = "Teste Selenium Editado";

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
        System.out.println("Teste de cadastro de pessoa iniciado.");
        driver.get("http://127.0.0.1:5501/html/home.html");

        Thread.sleep(2000);

        System.out.println("Página carregada com sucesso.");
        WebElement btnPessoa = driver.findElement(By.id("btnCadastrarPessoa"));
        btnPessoa.click();

        Thread.sleep(2000);

        WebElement nome = driver.findElement(By.id("btnCampoNome"));
        WebElement email = driver.findElement(By.id("btnCampoEmail"));
        WebElement cpf = driver.findElement(By.id("btnCampoCpf"));
        WebElement telefone = driver.findElement(By.id("btnCampoTelefone"));
        WebElement departamento = driver.findElement(By.id("btnCampoDepartamento"));
        WebElement cargo = driver.findElement(By.id("btnCampoCargo"));

        nome.sendKeys(nomeTeste);
        email.sendKeys("teste@teste.com");
        cpf.sendKeys(cpfTeste);
        telefone.sendKeys("(12) 34567-8901");
        departamento.sendKeys("Teste Automatizado");
        cargo.sendKeys("Teste QA");

        System.out.println("Dados da pessoa preenchidos.");

        WebElement submitButton = driver.findElement(By.id("btnSalvarPessoa"));
        submitButton.click();

        Thread.sleep(2000);

        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(cpfTeste);

        assertTrue(pessoa.isPresent(), "Pessoa não foi encontrada no banco após o cadastro.");
        
        Pessoa pessoaCadastrada = pessoa.get();
        assertEquals(nomeTeste, pessoaCadastrada.getNome());
        assertEquals(emailTeste, pessoaCadastrada.getEmail());
        assertEquals(cargoTeste, pessoaCadastrada.getCargo());
        
        System.out.println("Teste de cadastro concluído e verificado no banco.");
	}

    @Test
    @Order(2)
    public void testAlteracao() throws InterruptedException {
        System.out.println("Teste de alteração de pessoa iniciado.");

        driver.get("http://127.0.0.1:5501/html/pessoa.html");
        Thread.sleep(2000);

        String xpathLi = String.format("//ul[@id='btnListaPessoas']/li[contains(., '%s')]", nomeTeste);
        WebElement liPessoa = driver.findElement(By.xpath(xpathLi));
        System.out.println("Pessoa encontrada na lista.");

        WebElement selectAcao = liPessoa.findElement(By.tagName("select"));

        new Select(selectAcao).selectByValue("editar");

        Thread.sleep(1000);
        WebElement nomeInput = driver.findElement(By.id("btnCampoNome"));
        nomeInput.clear();
        nomeInput.sendKeys(nomeEditado);
        System.out.println("Nome alterado para: " + nomeEditado);

        WebElement btnSalvar = driver.findElement(By.id("btnSalvarPessoa"));

        Assertions.assertEquals("Salvar Edição", btnSalvar.getText(),
                "O texto do botão não mudou para 'Salvar Edição'.");

        btnSalvar.click();
        System.out.println("Alterações salvas.");

        Thread.sleep(2000);

        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(cpfTeste);

        assertTrue(pessoa.isPresent(), "Pessoa não encontrada no banco após a alteração.");
        
        Pessoa pessoaAlterada = pessoa.get();
        assertEquals(nomeEditado, pessoaAlterada.getNome(), "O nome da pessoa no banco não foi atualizado.");
        assertEquals(cpfTeste, pessoaAlterada.getCpf());

        System.out.println("Teste de alteração concluído");
    }

    @Test
    @Order(3)
    public void testDelecao() throws InterruptedException {
        System.out.println("Teste de deleção de pessoa iniciado.");

        driver.get("http://127.0.0.1:5501/html/pessoa.html");
        Thread.sleep(2000);
        String xpathLi = String.format("//ul[@id='btnListaPessoas']/li[contains(., '%s')]", nomeEditado);
        WebElement liPessoa = driver.findElement(By.xpath(xpathLi));
        System.out.println("Pessoa (editada) encontrada na lista para deleção.");
        WebElement selectAcao = liPessoa.findElement(By.tagName("select"));
        new Select(selectAcao).selectByValue("excluir");
        System.out.println("Modo de deleção ativado.");

        Thread.sleep(1000);

        WebElement btnConfirmar = driver.findElement(By.id("btnConfirmarExclusao"));
        btnConfirmar.click();
        System.out.println("Deleção confirmada.");

        Thread.sleep(2000);

        System.out.println("Verificando o banco de dados...");
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(cpfTeste);

        assertFalse(pessoa.isPresent(), "A pessoa ainda foi encontrada no banco após a deleção.");

        System.out.println("Teste de deleção concluído");
    }
}