package Interface;

import BancoDeDados.BDLivros;
import BancoDeDados.BDUsuarios;
import Modelos.Usuario;

public class InterfaceUsuario {
	private BDLivros bdL;
	private BDUsuarios bdU;
	private boolean statusUser;
	private Usuario user;
	
	
	public InterfaceUsuario(BDUsuarios bdU2, BDLivros bdL2) {
		bdU = bdU2;
		bdL = bdL2;
		statusUser = false;
	}


	public boolean logIn(String nome) {
		if(bdU.getStatusUsuario(nome)=="ok") {
			statusUser = true;
			user = bdU.getUsuario(nome);
		}
		else {
			statusUser = false;
			user = bdU.getUsuario(nome);
		}
		return statusUser;
	}


	public String fazerPesquisa(String nome_livro_autor) {
		if(statusUser){
			return bdL.acharLivro(nome_livro_autor);
		}
		return "Usuario está com pendência e não pode realizar consultas";
	}


	public String livrosQueRetirei() {
		return user.getLivrosStatus();
	}


	public String getStatusUsuario() {
		if(statusUser) return "Usuario nao tem pendencias";
		else{
			return "O usuario esta bloqueado por "+user.getDias_Bloqueio()+" dias";
		}
	}

}
