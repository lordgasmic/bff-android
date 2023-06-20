package com.lordgasmic.bff.collection.model;

import lombok.Data;

import java.util.List;

@Data
public class WineNoteRequest {
    private int wineId;
    private String user;
    private List<String> wineNotes;
    private List<WineNoteUpsert> upsert;
    private String date;
}
