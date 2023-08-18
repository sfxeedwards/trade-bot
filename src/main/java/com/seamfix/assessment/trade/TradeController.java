package com.seamfix.assessment.trade;


/**
 * TODO:
 *  TASK 2:
 *  Implement this controller class to expose the TradingService functionalities, matching the cURL REST endpoint
 *  i.e {@link TradeService#createNewInvestment(StartTradeRequest)}
 *  {@link TradeService#getInvestment(String)}
 *-----------------------------------------------------------------------------------
 *  get-user-investment
 *
 *  curl --location 'http://localhost:8090/api/v1/trade?userName=userfoo%40gmail.com'
 *-----------------------------------------------------------------------------------
 *
 * ----------------------------------------------------------------------------------
 *  start-user-investment
 *
 *  curl --location 'http://localhost:8090/api/v1/trade' \
 * --header 'Content-Type: application/json' \
 * --data-raw '{
 *     "userName":"userfoo@gmail.com",
 *     "principal":100
 * }'
 * -----------------------------------------------------------------------------------
 */


public class TradeController {

}
