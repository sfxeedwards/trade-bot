package com.seamfix.assessment.trade.engine;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class TradingBot {

    private final AtomicBoolean doTrading = new AtomicBoolean();

    /**
     * Predicts the likelihood of future trades are profitable for the investors
     *
     * What this means;
     * 1. If this function returns TRUE, This means that the bot should invest in
     * next market cycle
     * 2. If this function returns FALSE, This means that the bot should not invest
     * in next market cycle
     *
     * TODO:
     * TASK 1:
     * Using The moving Average algorithm, Implement a prediction model that uses
     * the last 10 interest rate indices
     * to determine trades by an investor for a period of time will yield profit.
     * This function should return true if the output of the algorithm is
     * non-negative and return false otherwise
     *
     * NOTE: if the outcome after the trade period is less than the initial
     * capital/principal,
     * it's not a problem forgive the BOT, it's just a machine willing to help :).
     *
     * @param interestHistory the list of past interest rate indexes
     * @return TRUE to indicate there's a likelihood for next trades to be
     *         profitable, FALSE otherwise
     */
    public boolean predictTrade(List<Float> interestHistory) {
        boolean isProfitable = false;
        // List<Float> minHistory = new ArrayList<Float>();
        // interestHistory.subList(0, 10);
        // for(int i = 0; i < 10; i++) {
        // minHistory.add(i, interestHistory.get(i));
        // // if(minHistory.size() >= 10) {
        // isProfitable = getProfitable(minHistory);
        // // minHistory.clear();
        // // }

        // }
        // System.out.println("Is Profitabl " + isProfitable);

        if (interestHistory.size() >= 10) {
            List<Float> minHistory = interestHistory.subList(0, 9);
            isProfitable = getProfitable(minHistory);

        }
        return isProfitable;
    }

    private boolean getProfitable(List<Float> minHistory) {
        boolean isProfitable = false;
        Float sum = 0f;
        for (int i = 0; i < minHistory.size(); i++) {
            // if(minHistory.get(i) > 0) {
            sum += minHistory.get(i);
            Float avg = sum / minHistory.size();
            System.out.println("Sum" + sum + ", Average " + avg);
            isProfitable = avg > 0 ? true : false;
            System.out.println("IsProfitable " + isProfitable);
            // }
        }
        return isProfitable;
    }

    /**
     * Performs interest rate analysis over a period of time
     */
    public void analyseHistory(List<Float> interestHistory) {
        boolean isDoTrade = predictTrade(interestHistory);
        doTrading.getAndSet(isDoTrade);
    }

    /**
     * Checks if last decision is either to trade or not
     * 
     * @return TRUE if trading should start, FALSE otherwise
     */
    public boolean shouldTrade() {
        return doTrading.get();
    }

}
