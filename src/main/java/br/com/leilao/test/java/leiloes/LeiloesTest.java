package br.com.leilao.test.java.leiloes;

import br.com.leilao.test.java.LoginPage;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class LeiloesTest {

    private LeiloesPage paginaDeLeiloes;
    private CadastroLeilaoPage paginaDeCadastro;


    @BeforeEach
    public void beforEach() {
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        this.paginaDeLeiloes = paginaDeLogin.efetuaLogin();
        this.paginaDeCadastro = paginaDeLeiloes.carregarFormulario();
    }

    @AfterEach
    public void afterEach() {
        this.paginaDeLeiloes.fechar();
        this.paginaDeCadastro.fechar();
    }

    @Test
    public void deveriaCadastrarLeilao() {
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilão do dia" + hoje;
        String valor = "500.00";

        this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
        Assert.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));
    }

    @Test
    public void deveriaValidarCadastroDeLeilao() {
        this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao("", "", "");

        Assert.assertFalse(this.paginaDeCadastro.isPaginaAtual());
        Assert.assertTrue(this.paginaDeLeiloes.isPaginaAtual());
        Assert.assertTrue(this.paginaDeCadastro.isMensagensDeValidacaoVisiveis());
    }
}
