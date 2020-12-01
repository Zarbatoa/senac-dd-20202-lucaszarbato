package br.sc.senac.model.utilidades;

public class StatusMensagem {

	private boolean status;
	private String mensagem;
	
	public StatusMensagem() {
		this.status = false;
		this.mensagem = null;
	}
	
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
