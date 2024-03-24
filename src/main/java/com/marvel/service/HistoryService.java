package com.marvel.service;

import com.marvel.model.dto.History;

import java.util.List;

public interface HistoryService {
    public List<History> listHistory();

    public void createHistory(History history);

    public List<History> getComicsHistory();

    public List<History> getUserHistory(String user);
}
