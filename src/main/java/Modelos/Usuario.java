package Modelos;

import java.util.HashMap;

public class Usuario {
	private String nome;
	private HashMap<Integer, Integer> LivrosEmprestados; 
	private String status;
	private int dias_bloqueado;

	public Usuario(String string) {
		nome = string;
		LivrosEmprestados = new HashMap<Integer, Integer>();
		status = "ok";
	}

	public String getNome() {
		return nome;
	}

	public String getStatus() {
		return status;
	}
	
	public void emprestaLivro(int codLivro, int prazo) {
		LivrosEmprestados.put(codLivro,prazo);
	}

	public void bloquear(int i) {
		status = "bloqueado";
		dias_bloqueado = i;
	}
	
	public int getDias_Bloqueio(){
		if(status == "bloqueado") return dias_bloqueado;
		else return 0;
	}

	public void devolveLivro(Livro l) {
		LivrosEmprestados.remove(l);
	}
	
}
