package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarrinhoPage {

	private WebDriver driver;

	private By nomeProduto = By.cssSelector("div.product-line-info a");
	private By precoProduto = By.cssSelector("span.price");
	private By tamanhoProduto = By.xpath("//span[text()='M']");
	private By corProduto = By.xpath("//span[text()='Black']");
	private By qtdProduto = By.className("js-cart-line-product-quantity form-control");
	private By subtotal = By.cssSelector("span.product-price strong");
	private By nItensTotal = By.cssSelector("span.js-subtotal");
	private By subtotalTotal = By.cssSelector("#cart-subtotal-products span.value");
	private By shippingTotal = By.cssSelector("#cart-subtotal-shipping span.value");
	private By totalTaxExclTotal = By.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(1) span.value");
	private By totalTaxIncTotal = By.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(2) span.value");
	private By taxesTotal = By.cssSelector("div.cart-summary-totals div.cart-summary-line:nth-child(3) span.value");

	public CarrinhoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String obter_nomeProduto() {
		return driver.findElement(nomeProduto).getText();
	}
	
	public String obter_precoProduto() {
		return driver.findElement(precoProduto).getText();
	}
    
	public String obter_corProduto() {
		return driver.findElement(corProduto).getText();
	}
	
	public String obter_qtdProduto() {
		return driver.findElement(qtdProduto).getText();
	}
	
	public String obter_tamanhoProduto() {
		return driver.findElement(tamanhoProduto).getText();
	}
	
	public String obter_subtotal() {
		return driver.findElement(subtotal).getText();
	}
	
	public String obter_nItensTotal() {
		return driver.findElement(nItensTotal).getText();
	}
	
	public String obter_subtotalTotal() {
		return driver.findElement(subtotalTotal).getText();
	}
	
	public String obter_shippingTotal() {
		return driver.findElement(shippingTotal).getText();
	}
	
	public String obter_totalTaxExclTotal() {
		return driver.findElement(totalTaxExclTotal).getText();
	}
	
	public String obter_totalTaxIncTotal() {
		return driver.findElement(totalTaxIncTotal).getText();
	}
	
	public String obter_taxesTotal() {
		return driver.findElement(taxesTotal).getText();
	}
}
