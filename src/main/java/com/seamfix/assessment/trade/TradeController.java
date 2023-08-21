package com.seamfix.assessment.trade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
  
@RestController()
@RequestMapping("/api/v1/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping
    public ResponseEntity<TradeResponse> cretaeNewInvestment(@RequestBody StartTradeRequest request) {
       return ResponseEntity.ok(this.tradeService.createNewInvestment(request));
    }

    @GetMapping
    public ResponseEntity<TradeResponse> getInvestment(@RequestParam("userName") String userName) {
        return ResponseEntity.ok(this.tradeService.getInvestment(userName));
    }
}
