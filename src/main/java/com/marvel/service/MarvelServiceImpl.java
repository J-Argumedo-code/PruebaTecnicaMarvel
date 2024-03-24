package com.marvel.service;

import com.marvel.model.dto.BaseResponse;
import com.marvel.model.dto.ComicResponse;
import com.marvel.model.dto.HeroDescImgResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class MarvelServiceImpl extends AbstractClient implements MarvelService {
    protected MarvelServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    private UriComponentsBuilder buildUri(String endPoint) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl + endPoint)
                .queryParam("ts", ts)
                .queryParam("apikey", apiKey)
                .queryParam("hash", hash);
    }

    @Override
    public BaseResponse searchHero(String heroName, Integer comicId, Integer serieId) {
        UriComponentsBuilder builder = buildUri("characters")
                .queryParam("name", heroName)
                .queryParam("comics", comicId)
                .queryParam("series", serieId);
        URI uri = builder.build().encode().toUri();

        ResponseEntity<BaseResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null, BaseResponse.class);
        return response.getBody();
    }

    @Override
    public ComicResponse searchComicsByHeroId(Integer heroId) {
        UriComponentsBuilder builder = buildUri(String.format("characters/%d/comics", heroId));
        URI uri = builder.build().encode().toUri();

        ResponseEntity<ComicResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null, ComicResponse.class);
        return response.getBody();
    }

    @Override
    public Object getHeroDescriptionImage(Integer heroId) {
        UriComponentsBuilder builder = buildUri(String.format("characters/%d", heroId));
        URI uri = builder.build().encode().toUri();

        ResponseEntity<HeroDescImgResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null, HeroDescImgResponse.class);
        HeroDescImgResponse heroResponse = response.getBody();
        assert heroResponse != null;
        return heroResponse.getData().getResults().get(0);
    }

    @Override
    public BaseResponse getComics(Integer comicId) {
        UriComponentsBuilder builder = buildUri("comics" + (comicId != null ? "/" + comicId : ""));
        URI uri = builder.build().encode().toUri();

        ResponseEntity<BaseResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null, BaseResponse.class);
        return response.getBody();
    }
}
