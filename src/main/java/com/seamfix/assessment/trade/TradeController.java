package com.seamfix.assessment.trade;


import com.seamfix.assessment.trade.data.InvestmentModel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

@Controller("/api/v1/trade")
@AllArgsConstructor
public class TradeController {

    protected final TradeService tradeService;

    @GetMapping()
    public ResponseEntity<TradeResponse<InvestmentModel>> getInvestment(@RequestParam("userName") String username) {
        return ResponseEntity.ok(this.tradeService.getInvestment(username));
    }
    @PostMapping()
    public ResponseEntity<TradeResponse<String>> createNewInvestment(@RequestBody StartTradeRequest request) {
        return ResponseEntity.ok(this.tradeService.createNewInvestment(request));
    }
}
