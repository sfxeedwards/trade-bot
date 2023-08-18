package com.seamfix.assessment.trade;


import com.seamfix.assessment.trade.data.InvestmentModel;
import com.seamfix.assessment.trade.data.InvestmentStatus;
import com.seamfix.assessment.trade.repository.UserInvestment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class TradeService {

    private UserInvestment investment;


    /**
     * Creates a new user investment
     * @param request new investment request
     * @return response
     */
    public TradeResponse<String> createNewInvestment(StartTradeRequest request){
        String username = request.getUserName();

        InvestmentModel model = new InvestmentModel();
        model.setUserName(username);
        model.setMaxTradeAttempt(60);
        model.setPrincipal(request.getPrincipal());
        model.setStatus(InvestmentStatus.WAITING);
        boolean saved = investment.saveNewInvestment(request.getUserName(), model);
        return new TradeResponse<>(saved ? "Created new investment Successfully" : "Investment currently exist", null);
    }

    /**
     * Gets a user's investment
     * @param userName investment username
     * @return response
     */
    public TradeResponse<InvestmentModel> getInvestment(String userName){
        InvestmentModel investmentModel = investment.getInvestment(userName);
        if(investmentModel == null){
            return new TradeResponse<>("Not found", null);
        }

        return new TradeResponse<>("Successful", investmentModel);
    }
}
