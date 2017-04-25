import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import BancoDeDados.BDLivros;
import BancoDeDados.BDUsuarios;
import Interface.InterfaceBiblioteca;
import Interface.InterfaceUsuario;
import Modelos.Livro;
import Modelos.Usuario;

public class TestsSprint4 {
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
		b = new Usuario("Jessica");
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
	public void testUsuarioProcuraLivro() {
		sistemaU.logIn("Alyson");
		assertEquals("O Usuario não pegou livros emprestados",sistemaU.livrosQueRetirei());
		sistema.fazerEmprestimo("Alyson", 1, 10);
		sistemaU.logIn("Alyson");
		assertEquals("Código: 1 | 10 dias\n",sistemaU.livrosQueRetirei());
		sistema.fazerEmprestimo("Alyson", 0, 5);
		sistemaU.logIn("Alyson");
		assertEquals("Código: 0 | 5 dias\nCódigo: 1 | 10 dias\n",sistemaU.livrosQueRetirei());
		sistemaU.logIn("Panda");
		assertEquals("Usuario está com pendência e não pode realizar consultas",sistemaU.fazerPesquisa("Dom Casmurro"));
	}
	
	@Test
	public void testUsuarioAcessaPerfil() {
		sistemaU.logIn("Alyson");
		assertEquals("Usuario nao tem pendencias",sistemaU.getStatusUsuario());
		sistema.bloquearUsuario("Jessica", 5);
		assertFalse(sistemaU.logIn("Jessica"));
		assertEquals("O usuario esta bloqueado por 5 dias",sistemaU.getStatusUsuario());
	}
}
