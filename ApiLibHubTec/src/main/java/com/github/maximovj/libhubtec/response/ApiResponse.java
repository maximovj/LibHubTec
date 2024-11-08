package com.github.maximovj.libhubtec.response;

public class ApiResponse 
{
	
	
	// !! Constructores
	
	/**
	 * Constructor para la clase ApiResponse.
	 *
	 * @param ctx_titulo   El título del mensaje de la respuesta.
	 * @param ctx_contenido El contenido del mensaje de la respuesta.
	 * @param uri           La URI asociada a la respuesta.
	 * @param tipo          El tipo de respuesta (por ejemplo, "error", "éxito", etc.).
	 * @param codigo        El código de estado HTTP asociado a la respuesta.
	 * @param estado        Estado de la respuesta (por ejemplo, "OK", "ERROR").
	 * @param exitosa       Indica si la operación fue exitosa (true) o fallida (false).
	 */
	public ApiResponse(String msg_titulo, String msg_contenido, String uri, String tipo, int codigo, String estado,
			boolean exitosa) {
		super();
		this.ctx_titulo = msg_titulo;
		this.ctx_contenido = msg_contenido;
		this.uri = uri;
		this.tipo = tipo;
		this.codigo = codigo;
		this.estado = estado;
		this.exitosa = exitosa;
	}
	
	// !! Métodos
	public String getCtx_titulo() {
		return ctx_titulo;
	}
	
	public void setCtx_titulo(String msg_titulo) {
		this.ctx_titulo = msg_titulo;
	}
	
	public String getCtx_contenido() {
		return ctx_contenido;
	}
	
	public void setCtx_contenido(String msg_contenido) {
		this.ctx_contenido = msg_contenido;
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public boolean isExitosa() {
		return exitosa;
	}
	
	public void setExitosa(boolean exitosa) {
		this.exitosa = exitosa;
	}
	
	@Override
	public String toString() {
		return "ApiResponse [ctx_titulo=" + ctx_titulo + ", ctx_contenido=" + ctx_contenido + ", uri=" + uri + ", tipo="
				+ tipo + ", codigo=" + codigo + ", estado=" + estado + ", exitosa=" + exitosa + "]";
	}
	
	// !! Propiedades
	String ctx_titulo;
	String ctx_contenido;
	String uri;
	String tipo;
	int codigo; 
	String estado;
	boolean exitosa;

}

