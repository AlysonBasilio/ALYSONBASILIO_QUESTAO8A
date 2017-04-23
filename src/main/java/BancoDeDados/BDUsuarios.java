package BancoDeDados;

import java.util.HashMap;

import Modelos.Usuario;

public class BDUsuarios {
	private HashMap<String,Usuario> bdU = new HashMap<String,Usuario>();
	
	public void adicionarUsuario(Usuario u) {
		bdU.put(u.getNome(), u);
	}
	
	public int getQtdUsuarios(){
		return bdU.size();
	}

	public void atualizaUsuario(String nome, Usuario a) {
		bdU.replace(nome, a);
	}

	public boolean usuarioExiste(String nome) {
		if(bdU.containsKey(nome)){
			return true;
		}
		else return false;
	}

	public String getStatusUsuario(String nome){
		if(bdU.containsKey(nome)){
			return bdU.get(nome).getStatus();
		}
		else return "Usuario não encontrado";
	}

	public void removerUsuario(String nome) {
		bdU.remove(nome);
	}

	public Usuario getUsuario(String nome) {
		return bdU.get(nome);
	}
}
