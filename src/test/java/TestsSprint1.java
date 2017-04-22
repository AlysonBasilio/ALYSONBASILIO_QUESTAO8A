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
		b = new Usuario("Jéssica");
		c = new Usuario("Matos");
		l = mock(Livro.class);
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
		assertTrue(sistema.usuarioExistenteNoSistema(a));
		sistema.adicionarUsuarioNoSistema(b);
		assertTrue(sistema.usuarioExistenteNoSistema(b));
		sistema.adicionarUsuarioNoSistema(c);
		assertTrue(sistema.usuarioExistenteNoSistema(c));
	}
	
	@Test
	public void testUsuarioEmprestaLivros() {
		testAdicionarUsuarioInterface();
		sistema.adicionaLivroNoSistema(l);
		assertTrue(sistema.fazerEmprestimo(a, l, 10));
	}

	@Test
	public void testRemoverUsuarioDoSistema() {
		testUsuarioEmprestaLivros();
		sistema.removerUsuario(b);
		assertFalse(sistema.fazerEmprestimo(b, l, 10));
	}
	
	@Test
	public void testBloqueioTemporarioDeUsuario() {
		testUsuarioEmprestaLivros();
		sistema.bloquearUsuario(b,5);
		assertFalse(sistema.fazerEmprestimo(b, l, 10));
		assertEquals("bloqueado por 5 dias",sistema.VerificaStatusDeUsuario(b));
		sistema.bloquearUsuario(c,10);
		assertEquals("bloqueado por 10 dias",sistema.VerificaStatusDeUsuario(c));
	}
	
}
