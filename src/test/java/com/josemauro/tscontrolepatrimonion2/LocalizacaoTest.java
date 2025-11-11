package com.josemauro.tscontrolepatrimonion2;

import com.google.common.base.Optional;
import com.josemauro.tscontrolepatrimonion2.entities.Localizacao;
import com.josemauro.tscontrolepatrimonion2.repository.LocalizacaoRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.Keys;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LocalizacaoTest {

    private WebDriver driver;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

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
        System.out.println("Teste de cadastro de localização iniciado.");
        this.driver.get("http://127.0.0.1:5500/html/home.html");

        Thread.sleep(3000);

        WebElement submitButton = driver.findElement(By.xpath("/html/body/div/button[3]"));
        submitButton.click();

        this.driver.get("http://127.0.0.1:5500/html/localizacao.html");

        System.out.println("Página carregada com sucesso.");
        WebElement nome = driver.findElement(By.id("local-nome"));
        WebElement descricao = driver.findElement(By.id("local-desc"));


        nome.sendKeys("praça sao joao");
        descricao.sendKeys("Praça em palmeiras");


        System.out.println("Dados da localização preenchidos.");

        WebElement submitButtonn = driver.findElement(By.xpath("//*[@id=\"local-form\"]/button"));
        submitButtonn.click();

        Thread.sleep(3000);

        String pageSource = this.driver.getPageSource();
        assertTrue(pageSource.contains("praça sao joao")
                && pageSource.contains("Praça em palmeiras"));
    }






    @Test
    public void testAlteracao() throws InterruptedException{
        System.out.println("Teste de alteração de Localização iniciado.");
        driver.get("\"http://127.0.0.1:5500/html/home.html\"");
        Thread.sleep(2000);

        WebElement submitButton = driver.findElement(By.xpath("/html/body/div/button[3]"));
        submitButton.click();

        this.driver.get("http://127.0.0.1:5500/html/localizacao.html");

        driver.findElement(By.id("local-nome")).sendKeys("ALT4321");
        driver.findElement(By.id("local-desc")).sendKeys("Volkswagen");
        
        WebElement submitButtonn = driver.findElement(By.xpath("//*[@id=\"local-form\"]/button"));
        submitButtonn.click();
        
        System.out.println("Localização base para alteração cadastrado.");
        Thread.sleep(2000);

        WebElement botaoEditar = driver.findElement(By.xpath("//li[contains(text(), 'ALT4321')]/button[text()='Editar']"));
        
        botaoEditar.click();
        Thread.sleep(1000);

        WebElement corField = driver.findElement(By.id("local-nome"));
        corField.clear();
        corField.sendKeys("praça da feira");
        System.out.println("Nome alterado para 'praça da feira'.");

        driver.findElement(By.cssSelector("form#personForm button[type='submit']")).click();
        System.out.println("Alteração enviada.");
        
        Thread.sleep(2000); 
        
        Optional<Localizacao> localizacaoAlteradoOpt = this.localizacaoRepository.findByNome("praça da feira");

        assertTrue(localizacaoAlteradoOpt.isPresent(), 
                   "O veículo com placa 'ALT4321' não foi encontrado no banco de dados.");

        Localizacao localizacaoAlterada = localizacaoAlteradoOpt.get();
        localizacaoAlteradoOpt.assertEquals("praça da feira", localizacaoAlterada.getNome(), 
                                "A localizacao no banco não foi atualizada para 'praça da feira'.");
        
        System.out.println("Alteração verificada com sucesso no banco de dados.");
    }


}