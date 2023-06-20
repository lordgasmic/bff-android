package com.lordgasmic.bff.dom.model;

import lombok.Data;

@Data
public class WineResponse {
    private int id;
    private int wineryId;
    private String name;
    private String style;
}
