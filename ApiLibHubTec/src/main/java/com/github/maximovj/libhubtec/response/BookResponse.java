package com.github.maximovj.libhubtec.response;

import java.util.List;
import java.util.Optional;

import com.github.maximovj.libhubtec.model.Book;

public class BookResponse {
	
	private ApiResponse response;
    private Optional<List<Book>> data;

    /**
     * Obtiene la respuesta general de la operación.
     *
     * @return un objeto {@link ApiResponse} que contiene el estado y detalles de la respuesta.
     */
    public ApiResponse getResponse() {
        return response;
    }

    /**
     * Establece la respuesta general de la operación.
     *
     * @param response un objeto {@link ApiResponse} que contiene el estado y detalles de la respuesta.
     */
    public void setResponse(ApiResponse response) {
        this.response = response;
    }

    /**
     * Obtiene los datos específicos de cuentas en la respuesta.
     *
     * @return un {@link Optional} que contiene una lista de objetos {@link Book}, si están presentes.
     */
    public Optional<List<Book>> getData() {
        return data;
    }

    /**
     * Establece los datos específicos de cuentas en la respuesta.
     *
     * @param data un {@link Optional} que contiene una lista de objetos {@link Book}.
     */
    public void setData(Optional<List<Book>> data) {
        this.data = data;
    }

}
