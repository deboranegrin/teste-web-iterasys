package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProdutoPage {

	private WebDriver driver;

	private By nomeProduto = By.className("h1");
	private By precoProduto = By.cssSelector(".current-price span:nth-child(1)");
	private By tamanhoProduto = By.id("group_1");
	private By corProduto = By.xpath("//ul[@id='group_2']//input[@value='11']");
	private By qtdProduto = By.id("quantity_wanted");
	private By botaoAddtoCart = By.className("add-to-cart");
	
	public ProdutoPage(WebDriver driver) {
		this.driver = driver;
	}

	public String obterNomeProduto() {
		return driver.findElement(nomeProduto).getText();
	}

	public String obterPrecoProduto() {
		return driver.findElement(precoProduto).getText();
	}

	public Select comboBoxTamanho() {
		return new Select(driver.findElement(tamanhoProduto));
	}

	public List<String> opcoesComboBoxTamanho() {
		List<WebElement> elementosSelecionados = comboBoxTamanho().getAllSelectedOptions();

		List<String> listaOpcoesSelecionadas = new ArrayList();

		for (WebElement elemento : elementosSelecionados) {
			listaOpcoesSelecionadas.add(elemento.getText());
		}
		return listaOpcoesSelecionadas;
	}

	public void selecionarTamanho(String opcao) {
		comboBoxTamanho().selectByVisibleText(opcao);
	}
	
	public void selecionarCorPreta() {
		driver.findElement(corProduto).click();
		
	}
	
	public void alterarQtd(int qtd) {
		driver.findElement(qtdProduto).clear();
		driver.findElement(qtdProduto).sendKeys(Integer.toString(qtd));
	}
	
	public ModalProdutoPage adicionarCarrinho() {
		driver.findElement(botaoAddtoCart).click();
		return new ModalProdutoPage(driver);
	}
	
}
