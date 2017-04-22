package Modelos;

import java.util.HashMap;

public class Usuario {
	private String nome;
	private HashMap<Livro, Integer> LivrosEmprestados; 
	private String status;
	private int dias_bloqueado;

	public Usuario(String string) {
		nome = string;
		LivrosEmprestados = new HashMap<Livro,Integer>();
		status = "ok";
	}

	public String getNome() {
		return nome;
	}

	public String getStatus() {
		return status;
	}
	
	public void emprestaLivro(Livro l, int prazo) {
		LivrosEmprestados.put(l, prazo);
	}

	public void bloquear(int i) {
		status = "bloqueado";
		dias_bloqueado = i;
	}
	
	public int getDias_Bloqueio(){
		if(status == "bloqueado") return dias_bloqueado;
		else return 0;
	}
	
}
