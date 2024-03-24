package com.marvel.model.dto;

import lombok.Data;

@Data
public class BaseResponse {
    public String code;
    public String status;
    public String copyright;
    public String attributionText;
    public String attributionHTML;
    public String etag;
    public Object data;
}
