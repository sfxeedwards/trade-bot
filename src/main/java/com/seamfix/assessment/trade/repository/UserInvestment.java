package com.seamfix.assessment.trade.repository;

import com.seamfix.assessment.trade.data.InvestmentModel;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class UserInvestment {

    /**
     * This is an investment tracker for registered user which must be unique
     */
    private final Map<String, InvestmentModel> investments = new HashMap<>();


    /**
     * Saves a new investment entry
     *
     * @param username the username of the investor; typically an email address
     * @param investment the investment;
     */
    public boolean saveNewInvestment(String username, InvestmentModel investment){
        if(!investments.containsKey(username)){
            return false;
        }
        investments.put(username, investment);
        return true;
    }

    /**
     * Update a new investment entry
     *
     * @param username the username of the investor; typically an email address
     * @param investment the investment;
     */
    public boolean updateNewInvestment(String username, InvestmentModel investment){
        if(investments.containsKey(username)){
            return false;
        }
        investments.put(username, investment);
        return true;
    }


    /**
     * Gets the investment for an investor
     *
     * @param userName the investor's username
     * @return Investment
     */
    public InvestmentModel getInvestment(String userName){
        return investments.get(userName);
    }

    public List<InvestmentModel> getAllInvestments(){
        return new ArrayList<>(investments.values());
    }
}
