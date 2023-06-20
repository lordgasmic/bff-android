package com.lordgasmic.bff.dom.model;

import lombok.Data;

import java.util.List;

@Data
public class WineNoteResponse {
    List<WineNoteOutput> wineNotes;
}
