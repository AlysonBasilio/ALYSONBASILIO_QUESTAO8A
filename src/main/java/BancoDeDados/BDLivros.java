package BancoDeDados;

import java.util.HashMap;
import java.util.Set;

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
		l.atualiza(u,prazo);
		bdL.replace(codigo, l);
	}

	public String getStatusLivro(int i) {
		return bdL.get(i).getStatus();
	}

	public Livro getLivro(int codLivro) {
		return bdL.get(codLivro);
	}

	public Usuario getUsuarioQuePegouLivroEmprestado(int cod2) {
		return bdL.get(cod2).getUsuario();
	}

	public String acharLivro(String nome_livro_autor) {
		Livro l;
		Set<Integer> SetI = bdL.keySet();
		for(int i : SetI){
			l = bdL.get(i);
			if(l.getAutor() == nome_livro_autor) return l.getStatus();
			if(l.getTitulo() == nome_livro_autor) return l.getStatus();
		}
		return "Livro não encontrado";
	}
}
