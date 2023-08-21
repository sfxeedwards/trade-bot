package com.seamfix.assessment.trade.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class TradingBotTest {

    @Spy
    private TradingBot tradingBot;

    @BeforeEach
    void setUp() {
    }

    // @Test
    void given_aListOfInterestRateHistory_determineThatThePredictionIsTrue() {
        List<Float> indices = Arrays.asList(
                -0.347405f, 0.184853f, -0.231427f, 0.179826f, -0.227218f,
                0.326757f, 0.487013f, 0.033610f, -0.226489f, 0.471357f,
                -0.282540f, 0.479764f, -0.420162f, 0.103325f, -0.302444f, 0.156428f);

        boolean trade = Mockito.spy(tradingBot).predictTrade(indices);
        Assertions.assertTrue(trade, "The logic proved otherwise, should not be false");
    }

    // @Test
    void given_aListOfInterestRateHistory_determineThatThePredictionIsFalse() {
        List<Float> indices = Arrays.asList(
                -0.347405f, 0.184853f, -0.231427f, 0.179826f, -0.227218f,
                -0.326757f, -0.487013f, 0.033610f, -0.226489f, -0.471357f,
                -0.282540f, 0.479764f, -0.420162f, 0.103325f, -0.302444f, 0.156428f);

        boolean trade = Mockito.spy(tradingBot).predictTrade(indices);
        Assertions.assertFalse(trade, "The logic proved otherwise, should not be true");
    }
}