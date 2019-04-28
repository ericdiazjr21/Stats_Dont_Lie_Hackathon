package com.example.statsdontlie;

import com.example.statsdontlie.utils.RandomNumberGenerator;

import org.junit.Assert;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void NoRepeatNumbers() {
        for (int i = 0; i < 1000; i++) {
            int player1 = RandomNumberGenerator.getRandomNumber1();
            int player2 = RandomNumberGenerator.getRandomNumber2();
            Assert.assertNotEquals(player1, player2);
        }


    }
}