package com.seamfix.assessment.trade.tasks;


import com.seamfix.assessment.trade.engine.Market;
import com.seamfix.assessment.trade.engine.TradingBot;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;


/**
 * TODO:
 *  TASK 4:
 *  Seek all usages of "System.out" and replace them with log4j implemented logger.
 */

@Component
@Slf4j
@AllArgsConstructor
public class TaskRunner {

    private  final Market market;

    private  final TradingBot tradingBot;

    /**
     * Generates profit interest rates (Using Random Function)
     * The interest rate index can either be negative or positive
     * (It simulates the random fall and rise trend in the stock market indices)
     *
     * Trades are performed at every generation of an index
     */
    @Scheduled(fixedDelay = 1000)
    public void runInterestIndicesMarket(){
        double newInterestRate = ThreadLocalRandom.current().nextDouble(-0.50, 0.50);
       log.info("New interest rate indices: {}", newInterestRate);
        System.out.println();
        market.saveNewInterest((float)newInterestRate);
        market.doBotTrades();
    }


    /**
     * This schedule runs to check if it can go ahead and perform a trade.
     * A trade is simply multiplying the interest rate index by the investors capital and adding it to the
     * original capital.
     *
     * Step. 1
     * proceeds = interest rate x investors capital
     *
     * Step. 2
     * investor's capital = investor's capital + (proceeds)
     * The proceeds of this trade can be negative or positive, so it is allowed if the capital finally becomes negative
     *
     * Note that the current interest rate is gotten from the zero index of InterestRateHistory
     * using {@link Market#getInterestRateHistory()#interestHistory#}
     */
    @Scheduled(fixedDelay = 4000)
    private void doBotAnalysis(){
        tradingBot.analyseHistory(market.getInterestRateHistory());
    }

}
