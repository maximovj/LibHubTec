package com.github.maximovj.libhubtec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.libhubtec.response.SearchResponse;
import com.github.maximovj.libhubtec.services.ISearchServiceImpl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/v1")
public class SearchRestController implements ISearchServiceImpl {

    @Autowired
    private ISearchServiceImpl searchServiceImpl;

    @GetMapping("/searches/{account_id}/account")
    @Override
    public ResponseEntity<SearchResponse> listSearches(@PathVariable Long account_id) {
        return this.searchServiceImpl.listSearches(account_id);
    }

    @DeleteMapping("/searches/{search_id}")
    @Override
    public ResponseEntity<SearchResponse> deleteSearch(@PathVariable Long search_id) {
        return this.searchServiceImpl.deleteSearch(search_id);
    }
    
}
