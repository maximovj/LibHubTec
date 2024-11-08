package com.github.maximovj.libhubtec.response;

public class ApiResponse 
{
	
	// !! Propiedades
		String ctx_title;
		String ctx_content;
		String uri;
		String type;
		int code; 
		String status;
		boolean success;
		
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
		public ApiResponse(String ctx_title, String ctx_content, String uri, String type, int code, String status,
				boolean success) {
			super();
			this.ctx_title = ctx_title;
			this.ctx_content = ctx_content;
			this.uri = uri;
			this.type = type;
			this.code = code;
			this.status = status;
			this.success = success;
		}
		
	// !! Métodos
		
		@Override
		public String toString() {
			return "ApiResponse [ctx_title=" + ctx_title + ", ctx_content=" + ctx_content + ", uri=" + uri + ", type="
					+ type + ", code=" + code + ", status=" + status + ", success=" + success + "]";
		}
		
		public String getCtx_title() {
			return ctx_title;
		}
		
		public void setCtx_title(String ctx_title) {
			this.ctx_title = ctx_title;
		}
		
		public String getCtx_content() {
			return ctx_content;
		}
		
		public void setCtx_content(String ctx_content) {
			this.ctx_content = ctx_content;
		}
		
		public String getUri() {
			return uri;
		}
		
		public void setUri(String uri) {
			this.uri = uri;
		}
		
		public String getType() {
			return type;
		}
		
		public void setType(String type) {
			this.type = type;
		}
		
		public int getCode() {
			return code;
		}
		
		public void setCode(int code) {
			this.code = code;
		}
		
		public String getStatus() {
			return status;
		}
		
		public void setStatus(String status) {
			this.status = status;
		}
		
		public boolean isSuccess() {
			return success;
		}
		
		public void setSuccess(boolean success) {
			this.success = success;
		}
	
}

