package com.github.maximovj.libhubtec.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.github.maximovj.libhubtec.model.Search;

public interface ISearchDao extends CrudRepository<Search,Long> {

    //@Query("SELECT s FROM searches s WHERE s.account_id = :accountId AND s.query = :query AND s.search = :search")
    //Optional<Search> findByAccountIdAndQueryAndSearch(Long accountId, String query, String search);

    // Buscar búsquedas existentes con account_id
    @Query("SELECT s FROM Search s WHERE s.account_id = :accountId AND s.query = :query AND s.search = :search")
    Optional<Search> findByAccountIdAndQueryAndSearch(Long accountId, String query, String search);
    
}
