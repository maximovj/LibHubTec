package com.github.maximovj.libhubtec.services;

import org.springframework.http.ResponseEntity;

import com.github.maximovj.libhubtec.response.SearchResponse;

public interface ISearchServiceImpl {
    
    // Listar las búsquedas realizada por una cuenta
    public ResponseEntity<SearchResponse> listSearches(Long account_id);

    // Eliminar una búsqueda por su id
    public ResponseEntity<SearchResponse> deleteSearch(Long search_id);

}
