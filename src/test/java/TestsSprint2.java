import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import BancoDeDados.BDLivros;
import BancoDeDados.BDUsuarios;
import Interface.InterfaceBiblioteca;
import Modelos.Livro;
import Modelos.Usuario;
import static org.mockito.Mockito.mock;

public class TestsSprint2 {
	BDLivros bdL;
	BDUsuarios bdU;
	Usuario a,b,c;
	InterfaceBiblioteca sistema;
	Livro l;
	
	@Before
	public void setUp(){
		bdU = new BDUsuarios();
		bdL = new BDLivros();
		a = new Usuario("Alyson");
		b = new Usuario("Jéssica");
		c = new Usuario("Matos");
		sistema = new InterfaceBiblioteca(bdU, bdL);
		sistema.adicionarUsuarioNoSistema(a);
		sistema.adicionarUsuarioNoSistema(b);
		sistema.adicionarUsuarioNoSistema(c);
		l = new Livro("Autor", "Titulo");
	}

	@Test
	public void testRegistrarEmprestimoDeLivro() {
		sistema.adicionaLivroNoSistema(l);
		assertTrue(sistema.fazerEmprestimo("Alyson", 0, 10));//Usuário a faz o empréstimo do livro 0
		l = new Livro("Machado de Assis", "Dom Casmurro");
		sistema.adicionaLivroNoSistema(l);
		assertTrue(sistema.fazerEmprestimo("Jéssica", 1, 10));//Usuário a faz o empréstimo do livro 1
		l = new Livro("Monteiro Lobato", "Sítio do PicaPau Amarelo");
		sistema.adicionaLivroNoSistema(l);
		assertTrue(sistema.fazerEmprestimo("Matos", 2, 10));//Usuário a faz o empréstimo do livro 2
		assertEquals("emprestado",sistema.verificaStatusDeLivro(0));
		assertEquals("emprestado",sistema.verificaStatusDeLivro(1));
		assertEquals("emprestado",sistema.verificaStatusDeLivro(2));
	}

	@Test
	public void testRegistrarDevolucaoDeLivro() {
		testRegistrarEmprestimoDeLivro();
		sistema.devolverLivro(0);
		assertEquals("livre",sistema.verificaStatusDeLivro(0));
		sistema.devolverLivro(1);
		assertEquals("livre",sistema.verificaStatusDeLivro(1));
		sistema.devolverLivro(2);
		assertEquals("livre",sistema.verificaStatusDeLivro(2));
	}
	
}
