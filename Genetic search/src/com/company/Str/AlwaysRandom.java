package com.company.Str;

import com.company.Strategy;

import java.util.Random;


public class AlwaysRandom extends Strategy
{
    Random random = new Random();
    double peaceProb = 0.7;
    int actionCount = 0;

    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        random.setSeed(System.currentTimeMillis());
        if(thisPlayer == 1)
            actionCount = p1payoffs.length;
        else
            actionCount = p1payoffs[0].length;
    }
    public int output()
    {
        if(random.nextDouble() < peaceProb)
            return 0;
        else
            return random.nextInt(actionCount-1) +1;
    }
    public void input(int otherAction, int p1payoff, int p2payoff)
    {

    }
}
