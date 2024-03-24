package com.marvel.service;

import com.marvel.model.dao.HistoryDao;
import com.marvel.model.dto.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryDao historyDao;

    @Override
    @Transactional(readOnly = true)
    public List<History> listHistory() {
        return (List<History>) historyDao.findAll();
    }

    @Override
    @Transactional
    public void createHistory(History history) {
        historyDao.save(history);
    }

    @Override
    @Transactional(readOnly = true)
    public List<History> getComicsHistory() {
        return historyDao.findByAction();
    }

    @Override
    @Transactional(readOnly = true)
    public List<History> getUserHistory(String user) {
        return historyDao.getUserHistory(user);
    }
}
