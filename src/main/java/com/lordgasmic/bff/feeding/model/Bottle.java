package com.lordgasmic.bff.feeding.model;

import lombok.Data;

@Data
public class Bottle {
    private int ordinal;
    private int given;
    private int quantity;
    private UnitOfMeasure givenUom;
    private UnitOfMeasure quantityUom;
    private boolean vitamin;
    private boolean probiotic;
    private boolean gas;
    private String note;
}
