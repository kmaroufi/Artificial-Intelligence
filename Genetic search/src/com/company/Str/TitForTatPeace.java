package com.company.Str;

import com.company.Strategy;

import java.util.Random;


public class TitForTatPeace extends Strategy
{
    Random random = new Random();
    double peaceProb = 0.4;
    int lastAction = 0;

    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        lastAction = 0;
        random.setSeed(System.currentTimeMillis());

    }
    public int output()
    {
        if(random.nextDouble() < peaceProb)
            return 0;
        else
            return lastAction;
    }
    public void input(int otherAction, int p1payoff, int p2payoff)
    {
        lastAction = otherAction;
    }
}
