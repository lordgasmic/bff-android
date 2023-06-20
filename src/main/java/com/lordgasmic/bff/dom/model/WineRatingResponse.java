package com.lordgasmic.bff.dom.model;

import lombok.Data;

@Data
public class WineRatingResponse {
    private int id;
    private int wineId;
    private String user;
    private String date;
    private String rating;

    public String formatRating() {
        return String.format("%s (%s)", rating, date);
    }
}
