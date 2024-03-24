package com.marvel.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class HeroDescImgResponse {
    private DataContainer data;

    @Data
    public static class DataContainer {
        private List<DescImg> results;

        @Data
        public static class DescImg {
            private String description;
            private Thumbnail thumbnail;

            public String getImageUrl() {
                if (thumbnail != null) {
                    return thumbnail.getPath() + "." + thumbnail.getExtension();
                } else {
                    return null;
                }
            }
        }

        @Data
        public static class Thumbnail {
            private String path;
            private String extension;
        }
    }
}
