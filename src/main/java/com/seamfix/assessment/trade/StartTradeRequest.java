package com.seamfix.assessment.trade;


import lombok.Data;

@Data
public class StartTradeRequest {

    /**
     * Preferably an email address
     */
    private String userName;

    /**
     * Amount to invest
     */
    private Double principal;
}
