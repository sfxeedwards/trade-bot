package com.seamfix.assessment.trade;


import com.seamfix.assessment.trade.data.InvestmentModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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


@RestController
@RequestMapping(path = "/api/v1/trade")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping()
    public ResponseEntity<TradeResponse<String>> createInvestment(@RequestBody StartTradeRequest dto){
        TradeResponse<String> response = tradeService.createNewInvestment(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<TradeResponse<InvestmentModel>> getInvestments(@RequestParam String userName){
        TradeResponse<InvestmentModel> response = tradeService.getInvestment(userName);
        return ResponseEntity.ok(response);
    }

}
