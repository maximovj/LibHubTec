package com.github.maximovj.libhubtec.response;

import java.util.List;
import java.util.Optional;

import com.github.maximovj.libhubtec.model.Account;

/**
 * La clase AccountResponse representa la respuesta de una operación que
 * incluye detalles de la respuesta general y datos específicos de cuentas.
 */
public class AccountResponse {

    private ApiResponse response;
    private Optional<List<Account>> data;

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
     * @return un {@link Optional} que contiene una lista de objetos {@link Account}, si están presentes.
     */
    public Optional<List<Account>> getData() {
        return data;
    }

    /**
     * Establece los datos específicos de cuentas en la respuesta.
     *
     * @param data un {@link Optional} que contiene una lista de objetos {@link Account}.
     */
    public void setData(Optional<List<Account>> data) {
        this.data = data;
    }
}

