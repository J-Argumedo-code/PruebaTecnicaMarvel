package com.marvel.service;

import com.marvel.model.dto.ComicResponse;
import com.marvel.model.dto.BaseResponse;

public interface MarvelService {
    public BaseResponse searchHero(String heroName, Integer comicId, Integer serieId);

    public ComicResponse searchComicsByHeroId(Integer heroId);

    public Object getHeroDescriptionImage(Integer heroId);

    public BaseResponse getComics(Integer comicId);
}
