import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import BancoDeDados.BDLivros;
import BancoDeDados.BDUsuarios;
import Interface.InterfaceBiblioteca;
import Modelos.Livro;
import Modelos.Usuario;
import static org.mockito.Mockito.mock;

public class TestsSprint1 {
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
		b = new Usuario("J�ssica");
		c = new Usuario("Matos");
		l = new Livro("Machado de Assis", "Dom Casmurro");
	}

	@Test
	public void testAdicionarUsuarioBD() {
		bdU.adicionarUsuario(a);
		assertEquals(1, bdU.getQtdUsuarios());
		bdU.adicionarUsuario(b);
		assertEquals(2, bdU.getQtdUsuarios());
		bdU.adicionarUsuario(c);
		assertEquals(3, bdU.getQtdUsuarios());
	}
	
	@Test
	public void testAdicionarUsuarioInterface() {
		sistema = new InterfaceBiblioteca(bdU, bdL);
		sistema.adicionarUsuarioNoSistema(a);
		assertTrue(sistema.usuarioExistenteNoSistema("Alyson"));
		sistema.adicionarUsuarioNoSistema(b);
		assertTrue(sistema.usuarioExistenteNoSistema("J�ssica"));
		sistema.adicionarUsuarioNoSistema(c);
		assertTrue(sistema.usuarioExistenteNoSistema("Matos"));
	}
	
	@Test
	public void testUsuarioEmprestaLivros() {
		testAdicionarUsuarioInterface();
		sistema.adicionaLivroNoSistema(l);
		assertTrue(sistema.fazerEmprestimo("Alyson", 0, 10));
	}

	@Test
	public void testRemoverUsuarioDoSistema() {
		testUsuarioEmprestaLivros();
		sistema.removerUsuario(b);
		assertFalse(sistema.fazerEmprestimo("J�ssica", 0, 10));
	}
	
	@Test
	public void testBloqueioTemporarioDeUsuario() {
		testUsuarioEmprestaLivros();
		sistema.bloquearUsuario("J�ssica",5);
		assertFalse(sistema.fazerEmprestimo("J�ssica", 0, 10));
		assertEquals("bloqueado por 5 dias",sistema.VerificaStatusDeUsuario("J�ssica"));
		sistema.bloquearUsuario("Matos",10);
		assertEquals("bloqueado por 10 dias",sistema.VerificaStatusDeUsuario("Matos"));
	}
	
}
