package com.josemauro.tscontrolepatrimonion2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.josemauro.tscontrolepatrimonion2.entities.Pessoa;
import com.josemauro.tscontrolepatrimonion2.repository.PessoaRepository;


import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PessoaTest {

	private WebDriver driver;

	@Autowired
    private PessoaRepository pessoaRepository;

	@BeforeAll
    public void setUpClass() {   

        WebDriverManager.chromedriver().setup();      

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        
        options.addArguments("--disable-blink-features=AutomationControlled");
        
        this.driver = new ChromeDriver(options);        
    }

	@Test
	public void testCadastro() throws InterruptedException{
		System.out.println("Teste de cadastro de pessoa iniciado.");
		driver.get("http://127.0.0.1:5501/html/home.html");

		Thread.sleep(2000);

		System.out.println("Página carregada com sucesso.");
		WebElement btnPessoa = driver.findElement(By.id("btnPessoa"));
		btnPessoa.click();

        Thread.sleep(2000);

        WebElement nome = driver.findElement(By.name("nome"));
        WebElement cpf = driver.findElement(By.name("nome"));
        WebElement email = driver.findElement(By.name("nome"));
        WebElement departamento = driver.findElement(By.name("nome"));
        WebElement cargo = driver.findElement(By.name("email"));

        nome.sendKeys("Teste Selenium");
        cpf.sendKeys("123.456.789-00");
        email.sendKeys("teste@teste.com");
        departamento.sendKeys("Teste Automatizado");
        cargo.sendKeys("Teste QA");

		System.out.println("Dados da pessoa preenchidos.");

		WebElement submitButton = driver.findElement(By.cssSelector("form#personForm button[type='submit']"));
        submitButton.click();

		Thread.sleep(2000);

		String pageSource = this.driver.getPageSource();
		assertTrue(pageSource.contains("Teste Selenium"));
	}
}
