package BancoDeDados;

import java.util.HashMap;

import Modelos.Livro;
import Modelos.Usuario;

public class BDLivros {
	private HashMap<Integer,Livro> bdL = new HashMap<Integer,Livro>();
	private int cod=0;
	
	public void adicionarLivro(Livro l) {
		l.setNCatalogo(cod++);
		bdL.put(l.getNcatalogo(), l);
	}
	
	public void atualizaLivro(int codigo, Usuario u, int prazo) {
		Livro l = bdL.get(codigo);
		l.empresta(u,prazo);
		bdL.replace(codigo, l);
	}

	public String getStatusLivro(int i) {
		return bdL.get(i).getStatus();
	}

	public Livro getLivro(int codLivro) {
		return bdL.get(codLivro);
	}
}
