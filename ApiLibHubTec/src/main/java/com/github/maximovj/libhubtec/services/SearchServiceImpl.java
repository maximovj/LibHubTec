package com.github.maximovj.libhubtec.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.maximovj.libhubtec.dao.IAccountDao;
import com.github.maximovj.libhubtec.dao.ISearchDao;
import com.github.maximovj.libhubtec.model.Account;
import com.github.maximovj.libhubtec.model.Search;
import com.github.maximovj.libhubtec.response.ApiResponse;
import com.github.maximovj.libhubtec.response.SearchResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements ISearchServiceImpl {

    private final ISearchDao searchDao;
    private final IAccountDao accountDao;
    private ApiResponse apiResponse;

    // Crear una respuesta exitosa
    private ResponseEntity<SearchResponse> buildSuccessReponse(String content, Optional<List<Search>> data)
    {
        SearchResponse response = new SearchResponse();
        this.apiResponse.setCtx_title("Búsquedas");
        this.apiResponse.setCtx_content(content);
        this.apiResponse.setCode(HttpStatus.OK.value());
        this.apiResponse.setStatus("success");
        this.apiResponse.setSuccess(true);
        response.setResponse(apiResponse);
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    // Crear una respuesta de error
    private ResponseEntity<SearchResponse> buildErrorReponse(HttpStatus status, String content)
    {
        SearchResponse response = new SearchResponse();
        this.apiResponse.setCtx_title("Búsquedas");
        this.apiResponse.setCtx_content(content);
        this.apiResponse.setCode(status.value());
        this.apiResponse.setStatus("success");
        this.apiResponse.setSuccess(true);
        response.setResponse(apiResponse);
        response.setData(null);
        return ResponseEntity.status(status).body(response);
    }

    @Override
    public ResponseEntity<SearchResponse> listSearches(Long account_id) {
        this.apiResponse = new ApiResponse();
        this.apiResponse.setUri("/v1/searches/"+account_id+"/account");
        this.apiResponse.setType("GET");
        List<Search> list = new ArrayList<>();

        if(account_id == null) {
            return this.buildErrorReponse(HttpStatus.BAD_REQUEST, "Oops cuenta no proporcionada");
        }

        Optional<Account> account = this.accountDao.findById(account_id);
        if(!account.isPresent()){ 
            return this.buildErrorReponse(HttpStatus.NOT_FOUND, "Oops cuenta no encontrada en el sistema");
        } 

        list = (List<Search>) this.searchDao.findByAccountId(account.get().getId());

        return this.buildSuccessReponse("Listando búsquedas exitosamente", Optional.ofNullable(list));
    }

    @Override
    public ResponseEntity<SearchResponse> deleteSearch(Long search_id) {
        this.apiResponse = new ApiResponse();
        this.apiResponse.setUri("/v1/searches/"+search_id);
        this.apiResponse.setType("DELETE");
        List<Search> list = new ArrayList<>();

        if(search_id == null) {
            return this.buildErrorReponse(HttpStatus.BAD_REQUEST, "Oops búesqueda no proporcionada");
        }

        Optional<Search> search = this.searchDao.findById(search_id);
        if(!search.isPresent()) {
            return this.buildErrorReponse(HttpStatus.NOT_FOUND, "Oops búsqueda no encontrada en el sistema");
        }

        list.add(search.get());
        this.searchDao.delete(search.get());
        return this.buildSuccessReponse("Búsqueda eliminado correctamente", Optional.ofNullable(list));
    }
    
}
