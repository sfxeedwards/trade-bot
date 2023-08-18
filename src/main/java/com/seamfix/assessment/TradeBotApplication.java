package com.seamfix.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//TODO Fill in the missing annotation to enable the bot in trade/task/TradingBot.java to run
@EnableScheduling
public class TradeBotApplication {
	public static void main(String[] args) {
		SpringApplication.run(TradeBotApplication.class, args);
	}
}
