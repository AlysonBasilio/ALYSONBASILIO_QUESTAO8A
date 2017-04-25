package Interface;

import BancoDeDados.BDLivros;
import BancoDeDados.BDUsuarios;
import Modelos.Livro;
import Modelos.Usuario;

public class InterfaceBiblioteca {
	private BDLivros bdL;
	private BDUsuarios bdU;
	
	
	public InterfaceBiblioteca(BDUsuarios bdU2, BDLivros bdL2) {
		bdU = bdU2;
		bdL = bdL2;
	}


	public boolean fazerEmprestimo(String nome, int codLivro, int prazo) {
		if(bdU.getStatusUsuario(nome)=="ok"){
			Usuario a = bdU.getUsuario(nome);
			a.emprestaLivro(codLivro,prazo);
			bdU.atualizaUsuario(nome, a);
			bdL.atualizaLivro(codLivro, a, prazo);
			return true;
		} else return false;
	}


	public String verificaStatusDeLivro(int i) {
		return bdL.getStatusLivro(i);
	}


	public void adicionaLivroNoSistema(Livro l) {
		bdL.adicionarLivro(l);
	}


	public void adicionarUsuarioNoSistema(Usuario a) {
		bdU.adicionarUsuario(a);
	}


	public boolean usuarioExistenteNoSistema(String nome) {
		return bdU.usuarioExiste(nome);
	}


	public void removerUsuario(Usuario b) {
		bdU.removerUsuario(b.getNome());
	}


	public void bloquearUsuario(String nome, int i) {
		Usuario u = bdU.getUsuario(nome);
		u.bloquear(i);
		bdU.atualizaUsuario(nome, u);
	}


	public String VerificaStatusDeUsuario(String nome) {
		if(bdU.usuarioExiste(nome)){
			if(bdU.getUsuario(nome).getStatus()=="bloqueado")
				return "bloqueado por "+bdU.getUsuario(nome).getDias_Bloqueio()+" dias";
			else return bdU.getUsuario(nome).getStatus();
		}else return "Usuário não existe";
	}


	public void devolverLivro(int cod) {
		Usuario u = bdL.getUsuarioQuePegouLivroEmprestado(cod);
		bdL.atualizaLivro(cod, null, 0);
		u.devolveLivro(bdL.getLivro(cod));
		bdU.atualizaUsuario(u.getNome(), u);
	}

}
