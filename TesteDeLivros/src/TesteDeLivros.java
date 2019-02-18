import org.junit.*;
import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteDeLivros {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void TesteDeLivros() throws Exception {
		// Abrindo o site da submarino
		driver.get("https://www.submarino.com.br/");
		driver.findElement(By.id("h_search-input")).click();
		driver.findElement(By.id("h_search-input")).clear();

		// Buscando os livros
		driver.findElement(By.id("h_search-input")).sendKeys("livros");

		// Abrindo o primeiro livro da pagina
		driver.findElement(By.id("h_search-btn")).click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Refinar'])[1]/following::img[1]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Autor(a)'])[1]/following::td[1]"))
				.click();

		// gravando o ISBN do livro
		String isbnSubmarino = driver
				.findElement(
						By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='ISBN'])[1]/following::span[1]"))
				.getText();

		// Salvando o nome do Autor no site da Submarino
		String autorSubmarino = driver
				.findElement(By
						.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Autor(a)'])[1]/following::span[1]"))
				.getText();

		
		// Abrindo o site da Americanas
		driver.get("https://www.americanas.com.br/");
		driver.findElement(By.id("h_search-input")).click();
		driver.findElement(By.id("h_search-input")).clear();

		// Buscando o livro pelo ISBN salvo na submarino
		driver.findElement(By.id("h_search-input")).sendKeys(isbnSubmarino);
		driver.findElement(By.id("h_search-input")).sendKeys(Keys.ENTER);

		// Selecionando o livro encontrado
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Refinar'])[1]/following::img[1]"))
				.click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Autor(a)'])[1]/following::td[1]"))
				.click();

		// Salvando o autor do livro encontrado no site da Americanas
		String autorAmericanas = driver
				.findElement(By
						.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Autor(a)'])[1]/following::span[1]"))
				.getText();

		
		
		// Abre o site da Livraria Cultura
		driver.get("https://www.livrariacultura.com.br/");

		// Busca o livro pelo ISBN (código) da submarino
		driver.findElement(By.id("Ntt-responsive")).click();
		driver.findElement(By.id("Ntt-responsive")).clear();
		driver.findElement(By.id("Ntt-responsive")).sendKeys(isbnSubmarino);

		// Seleciona o livro 
		driver.findElement(By
				.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Papelaria & Etc'])[2]/following::input[2]"))
				.click();
		driver.findElement(By.linkText("Dois a Dois")).click();

		// Autor do livro encontrado na livraria cultura
		String autorCultura = driver.findElement(By.linkText("SPARKS, NICHOLAS")).getText();

	
		if (autorSubmarino.equals(autorAmericanas)) {
			//JOptionPane.showMessageDialog(null, "O autor do livro é igual nos sites da Americanas e Submarino!!");
			System.out.println("O autor do livro é igual nos sites da Americanas e Submarino!");
		} else {
			//JOptionPane.showMessageDialog(null, "O autor do livro é diferente no site da Americanas!!");
			System.out.println("O autor do livro é diferente no site da Americanas!");
		}
		
		if (autorCultura.equals(autorSubmarino)) {
			//JOptionPane.showMessageDialog(null, "O autor do livro é igual em ambos os sites!!");
			System.out.println("O autor do livro é igual nos dois sites!");
		} else {
			
			//JOptionPane.showMessageDialog(null, "O autor do livro é diferente no site da Livraria Cultura!");
			System.out.println("O autor do livro é diferente no site da Livraria Cultura!");
		}

	
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}


}