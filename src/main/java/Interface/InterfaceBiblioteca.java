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


	public boolean fazerEmprestimo(Usuario a, Livro l, int prazo) {
		if(bdU.getStatusUsuario(a.getNome())=="ok"){
			a.emprestaLivro(l,prazo);
			bdU.atualizaUsuario(a);
			bdL.atualizaLivro(l.getNcatalogo(), a, prazo);
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


	public boolean usuarioExistenteNoSistema(Usuario u) {
		return bdU.usuarioExiste(u);
	}


	public void removerUsuario(Usuario b) {
		bdU.removerUsuario(b.getNome());
	}


	public void bloquearUsuario(Usuario b, int i) {
		b.bloquear(i);
	}


	public String VerificaStatusDeUsuario(Usuario b) {
		if(bdU.usuarioExiste(b)){
			Usuario u = bdU.getUsuario(b.getNome());
			if(u.getStatus()=="bloqueado")
				return "bloqueado por "+u.getDias_Bloqueio()+" dias";
			else return u.getStatus();
		}else return "Usuário não existe";
	}

}
