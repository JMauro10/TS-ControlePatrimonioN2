package com.josemauro.tscontrolepatrimonion2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.josemauro.tscontrolepatrimonion2.repository.PessoaRepository;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
    public void testAdicionarPessoa() {
       System.out.println("Teste de cadastro de veículo iniciado.");
       driver.get("http://127.0.0.1:5500/crud-veiculo.html");
        
    }

    
}
