package Modelos;

public class Livro {
	private String titulo;
	private String autor;
	private int Ncatalogo;
	private String status;
	private Usuario usuario;
	private int prazo;
	
	public Livro(String autor, String titulo) {
		this.autor = autor;
		this.titulo = titulo;
		status = "livre";
		setPrazo(0);
	}

	public String getTitulo() {
		return titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public String getStatus() {
		return status;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public int getNcatalogo() {
		return Ncatalogo;
	}

	public void atualiza(Usuario u, int prazo) {
		usuario = u;
		if(prazo==0){
			status="livre";
		}else status = "emprestado";
		this.setPrazo(prazo);
	}

	public void setNCatalogo(int i) {
		Ncatalogo = i;		
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}
	
}
