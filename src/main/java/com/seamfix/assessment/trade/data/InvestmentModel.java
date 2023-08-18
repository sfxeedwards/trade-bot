package com.seamfix.assessment.trade.data;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InvestmentModel {

    /**
     * Preferably an email address
     */
    private String userName;

    /**
     * Investment capital
     */
    private Double principal;

    private List<Float> proceedsHistory = new ArrayList<>();

    /**
     * Tracks the number of trade attempts
     */
    private int tradeAttempt;

    /**
     * The maximum number of trade attempts where the bot no longer
     * performs a trade for this investment
     */
    private int maxTradeAttempt;

    /**
     * Tracks the total number of trade performed
     */
    private int totalTradePerformed;

    private InvestmentStatus status;
}
