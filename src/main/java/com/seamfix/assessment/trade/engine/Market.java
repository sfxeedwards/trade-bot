package com.seamfix.assessment.trade.engine;

import com.seamfix.assessment.trade.data.InvestmentModel;
import com.seamfix.assessment.trade.data.InvestmentStatus;
import com.seamfix.assessment.trade.repository.UserInvestment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * TODO:
 *  TASK 4:
 *  Seek all usages of "System.out" and replace them with log4j implemented logger.
 */

@Component
@RequiredArgsConstructor
@Log4j
public class Market {

    private  final UserInvestment userInvestment;

    private  final TradingBot tradingBot;

    private static final Logger logger = LogManager.getLogger();

    private final List<Float> interestRateHistory = new ArrayList<>();


    public void saveNewInterest(Float interest){
        interestRateHistory.add(0, interest);
    }

    public List<Float> getInterestRateHistory(){
        return interestRateHistory;
    }

    public float getCurrentInterest(){
        return !interestRateHistory.isEmpty()? interestRateHistory.get(0) : 0;
    }

    /**
     * Performs a trade at every change in interest rate index for
     * all investments that subscribed to this bot
     *
     */
    public void doBotTrades(){
        List<InvestmentModel> users = userInvestment.getAllInvestments();
        users.forEach(this::doBotTrade);
    }


    /**
     * Performs a trade for a given investment entity
     *
     * TODO:
     *  TASK 3:
     *  Update the status each investment to either
     *  {@link com.seamfix.assessment.trade.data.InvestmentStatus#ENDED}, when maxTradeAttempt reached
     *  or
     *  {@link com.seamfix.assessment.trade.data.InvestmentStatus#TRADING} when it is yet to attain maxTradeAttempt
     *  or
     *  {@link com.seamfix.assessment.trade.data.InvestmentStatus#PAUSED} when the bot is not willing to do a trade
     *  ......
     *  HINT:{@link InvestmentModel#getTradeAttempt()} tracks the number of attempts of performing a trade
     */
    public void doBotTrade(InvestmentModel investment){
        boolean shouldTrade = tradingBot.shouldTrade();
        if ( investment.getTradeAttempt() >= investment.getMaxTradeAttempt()) {
            investment.setStatus(InvestmentStatus.ENDED);
            return;
        }
        if(shouldTrade){
            float interestIndex = getCurrentInterest();
            double proceed = investment.getPrincipal() * interestIndex;
            double newPrincipal = proceed + investment.getPrincipal();
            investment.setPrincipal(newPrincipal);
            investment.getProceedsHistory().add(0, (float)proceed);
            investment.setTotalTradePerformed(investment.getTotalTradePerformed() + 1);
            investment.setStatus(InvestmentStatus.TRADING);
        } else {
            investment.setStatus(InvestmentStatus.PAUSED);
        }
        investment.setTradeAttempt(investment.getTradeAttempt() + 1);

//        System.out.printf("INVESTMENT STATUS FOR: %s is %s", investment.getUserName(), investment.getStatus());
        logger.info("INVESTMENT STATUS FOR: %s is %s", investment.getUserName(), investment.getStatus());
    }
}
