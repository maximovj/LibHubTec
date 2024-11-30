package com.github.maximovj.libhubtec.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.github.maximovj.libhubtec.model.Search;

public interface ISearchDao extends CrudRepository<Search,Long> {

    // Buscar búsquedas existentes con account_id, query y search
    @Query("SELECT s FROM Search s WHERE s.account_id = :accountId AND s.query = :query AND s.search = :search ORDER BY s.updated_at DESC")
    Optional<Search> findByAccountIdAndQueryAndSearch(Long accountId, String query, String search);

    // Buscar búsquedas existentes con account_id
    @Query("SELECT s FROM Search s WHERE s.account_id = :accountId ORDER BY s.updated_at DESC")
    List<Search> findByAccountId(Long accountId);
    
}
