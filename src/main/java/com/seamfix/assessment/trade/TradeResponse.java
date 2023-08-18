package com.seamfix.assessment.trade;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TradeResponse <H>{
    private String description;

    private H responseObject;
}
