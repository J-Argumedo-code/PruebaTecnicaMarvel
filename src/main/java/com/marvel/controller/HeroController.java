package com.marvel.controller;

import com.marvel.model.dto.History;
import com.marvel.service.HistoryService;
import com.marvel.service.MarvelService;
import com.marvel.model.dto.ComicResponse;
import com.marvel.model.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
@Slf4j
public class HeroController {
    private final MarvelService marvelService;
    @Autowired
    private HistoryService historyService;

    public HeroController(MarvelService marvelService) {
        this.marvelService = marvelService;
    }

    @GetMapping("marvel/searchHero")
    public BaseResponse findHero(@RequestParam("heroName") String heroName, @RequestParam("comicId") Integer comicId, @RequestParam("serieId") Integer serieId) {
        AddHistory("searchHero");
        return this.marvelService.searchHero(heroName, comicId, serieId);
    }

    @GetMapping("marvel/searchComicsByHeroId")
    public ComicResponse searchComicsByHeroId(@RequestParam(name = "heroId") Integer heroId) {
        AddHistory("searchComicsByHeroId");
        return this.marvelService.searchComicsByHeroId(heroId);
    }

    @GetMapping("marvel/getHeroDescriptionImage")
    public Object getHeroDescriptionImage(@RequestParam("heroId") Integer heroId) {
        AddHistory("getHeroDescriptionImage");
        return this.marvelService.getHeroDescriptionImage(heroId);
    }

    @GetMapping("marvel/getComics")
    public Object getComics() {
        AddHistory("getComics");
        return this.marvelService.getComics(null);
    }

    @GetMapping("marvel/getComicById")
    public Object getComicsById(@RequestParam("comicId") Integer comicId) {
        AddHistory("getComicById");
        return this.marvelService.getComics(comicId);
    }

    @GetMapping("marvel/getComicsSearchHistory")
    public List<History> getComicsSearchHistory() {
        return this.historyService.getComicsHistory();
    }

    @GetMapping("marvel/getUserSearchHistory")
    public List<History> getUserSearchHistory(@RequestParam("user") String user) {
        return this.historyService.getUserHistory(user);
    }

    public void AddHistory(String action){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            History history = new History();
            history.setId_history(null);
            history.setUser(authentication.getName());
            history.setAction(action);
            historyService.createHistory(history);
        }
    }
}
