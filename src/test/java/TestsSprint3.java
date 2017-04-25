import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import BancoDeDados.BDLivros;
import BancoDeDados.BDUsuarios;
import Interface.InterfaceBiblioteca;
import Interface.InterfaceUsuario;
import Modelos.Livro;
import Modelos.Usuario;

public class TestsSprint3 {

	BDLivros bdL;
	BDUsuarios bdU;
	Usuario a,b,c;
	InterfaceBiblioteca sistema;
	InterfaceUsuario sistemaU;
	Livro l;
	
	@Before
	public void setUp(){
		bdU = new BDUsuarios();
		bdL = new BDLivros();
		a = new Usuario("Alyson");
		b = new Usuario("Jéssica");
		c = new Usuario("Matos");
		sistema = new InterfaceBiblioteca(bdU, bdL);
		sistemaU = new InterfaceUsuario(bdU, bdL);
		sistema.adicionarUsuarioNoSistema(a);
		sistema.adicionarUsuarioNoSistema(b);
		sistema.adicionarUsuarioNoSistema(c);
		l = new Livro("Autor", "Titulo");
		sistema.adicionaLivroNoSistema(l);
		l = new Livro("Machado de Assis", "Dom Casmurro");
		sistema.adicionaLivroNoSistema(l);
	}

	@Test
	public void testLoginUsuario() {
		assertTrue(sistemaU.logIn("Alyson"));
		assertTrue(sistemaU.logIn("Jéssica"));
		assertFalse(sistemaU.logIn("Panda"));	
	}
	
	@Test
	public void testUsuarioProcuraLivro() {
		sistemaU.logIn("Alyson");
		assertEquals("livre",sistemaU.fazerPesquisa("Autor"));
		assertEquals("Livro não encontrado",sistemaU.fazerPesquisa("Parada"));
		sistema.fazerEmprestimo("Alyson", 1, 10);
		assertEquals("emprestado",sistemaU.fazerPesquisa("Dom Casmurro"));
		sistemaU.logIn("Panda");
		assertEquals("Usuario está com pendência e não pode realizar consultas",sistemaU.fazerPesquisa("Dom Casmurro"));
	}

}
