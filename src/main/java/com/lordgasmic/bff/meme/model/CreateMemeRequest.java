package com.lordgasmic.bff.meme.model;

import lombok.Data;

import java.util.List;

@Data
public class CreateMemeRequest {
    private String name;
    private Object image;
    private List<String> tags;
}
