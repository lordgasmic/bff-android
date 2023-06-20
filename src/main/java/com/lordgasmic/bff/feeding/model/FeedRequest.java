package com.lordgasmic.bff.feeding.model;

import lombok.Data;

import java.util.List;

@Data
public class FeedRequest {
    private String date;
    private int timeHour;
    private int timeMinute;
    private Meridiem meridiem;
    private List<Bottle> bottles;
}
