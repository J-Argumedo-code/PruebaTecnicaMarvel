package com.marvel.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ComicResponse {
    public Results data;
}

@Data
class Results {
    private List<ComicTitle> results;
}

@Data
class ComicTitle{
    private String title;
}
