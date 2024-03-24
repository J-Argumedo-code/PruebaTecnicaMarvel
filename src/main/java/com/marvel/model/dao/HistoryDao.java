package com.marvel.model.dao;

import com.marvel.model.dto.History;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryDao extends CrudRepository<History, Long> {
    // Script to search by action
    @Query("SELECT h FROM History h WHERE action = 'searchComicsByHeroId' OR action = 'getComics' OR action = 'getComicById'")
    List<History> findByAction();

    // Script to get user history
    @Query("SELECT h FROM History h WHERE user = :user")
    List<History> getUserHistory(String user);
}
