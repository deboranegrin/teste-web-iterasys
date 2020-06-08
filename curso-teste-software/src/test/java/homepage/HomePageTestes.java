package homepage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import base.BaseTests;
import pages.CarrinhoPage;
import pages.LoginPage;
import pages.ModalProdutoPage;
import pages.ProdutoPage;

public class HomePageTestes extends BaseTests {

	@Test
	public void testContarProdutos() {
		carregarPagInicial();
		assertThat(homePage.contarProdutos(), is(8));
	}

	@Test
	public void testValidarCarrinhoVazio() {
		int produtosCarrinho = homePage.obterQtdProdutoCarrinho();
//		System.out.println(produtosCarrinho);
		assertThat(produtosCarrinho, is(0));
	}

	ProdutoPage produtoPage;
	String nomeProdutoPage;

	@Test
	public void testValidarDetalhesProduto() {
		int indice = 0;
		String nomeProdutoHome = homePage.obterNomeProduto(indice);
		String precoProdutoHome = homePage.obterPrecoProduto(indice);

		System.out.println(nomeProdutoHome);
		System.out.println(precoProdutoHome);

		produtoPage = homePage.clicarProduto(indice);

		nomeProdutoPage = produtoPage.obterNomeProduto();
		String precoProdutoPage = produtoPage.obterPrecoProduto();

		System.out.println(nomeProdutoPage);
		System.out.println(precoProdutoPage);

		assertThat(nomeProdutoHome.toUpperCase(), is(nomeProdutoPage.toUpperCase()));
		assertThat(precoProdutoHome, is(precoProdutoPage));

	}

	LoginPage loginPage;

	@Test
	public void testLoginComSucesso() {
		loginPage = homePage.clicarBotaoSignIn();
		loginPage.preencherEmail("teste@teste.com");
		loginPage.preencherSenha("teste");
		loginPage.clicarBotaoSignIn();
		assertThat(homePage.estaLogado("Teste Testador"), is(true));
		carregarPagInicial();
	}

	
	ModalProdutoPage modalProdutoPage;
	
	@Test
	public void incluirProdutoCarrinho() {
		String tamanhoProduto = "M";
		String corProduto = "Black";
		int qtdProduto = 2;

		if (!homePage.estaLogado("Teste Testador")) {
			testLoginComSucesso();
		}
		testValidarDetalhesProduto();

		List<String> listaOpcoes = produtoPage.opcoesComboBoxTamanho();
		System.out.println(listaOpcoes.get(0));
		System.out.println("Tamanho da lista: " + listaOpcoes.size());

		produtoPage.selecionarTamanho(tamanhoProduto);

		listaOpcoes = produtoPage.opcoesComboBoxTamanho();
		System.out.println(listaOpcoes.get(0));
		System.out.println("Tamanho da lista: " + listaOpcoes.size());

		produtoPage.selecionarCorPreta();

		produtoPage.alterarQtd(qtdProduto);

		modalProdutoPage = produtoPage.adicionarCarrinho();

		assertTrue(modalProdutoPage.obterMsgProdutoAdd().endsWith("Product successfully added to your shopping cart"));

		assertThat(modalProdutoPage.obterDescricaoProduto().toUpperCase(), is(nomeProdutoPage.toUpperCase()));
		
		String precoProdutoString = modalProdutoPage.obterPrecoProduto();
		precoProdutoString = precoProdutoString.replace("$", "");
		Double precoProduto = Double.parseDouble(precoProdutoString);
		
		assertThat(modalProdutoPage.obterTamanhoProduto(), is(tamanhoProduto));
		assertThat(modalProdutoPage.obterCorProduto(), is(corProduto));
		assertThat(modalProdutoPage.obterQtdProduto(), is(Integer.toString(qtdProduto)));

		String subTotalString = modalProdutoPage.obterSubtotal();
		subTotalString = subTotalString.replace("$", "");
		Double subTotal = Double.parseDouble(subTotalString);
		
		Double subtotalCalculado = qtdProduto * precoProduto;

		assertThat(subTotal, is(subtotalCalculado));
	}
	
	@Test
	public void irParaCarrinho() {
		incluirProdutoCarrinho();
		CarrinhoPage carrinhoPage = modalProdutoPage.clicarBotaoCheckout();
		
	}

}