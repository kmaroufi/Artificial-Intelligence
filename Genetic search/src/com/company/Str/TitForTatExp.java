package com.company.Str;

import com.company.Strategy;

import java.util.Random;


public class TitForTatExp extends Strategy
{
    Random random = new Random();
    double exprProb = 0.1;
    int lastAction = 0;
    int actionCount = 0;

    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        lastAction = 0;
        random.setSeed(System.currentTimeMillis());
        if(thisPlayer == 1)
            actionCount = p1payoffs.length;
        else
            actionCount = p1payoffs[0].length;
    }
    public int output()
    {
        if(random.nextDouble() < exprProb)
            return random.nextInt(actionCount-1)+1;
        else
            return lastAction;
    }
    public void input(int otherAction, int p1payoff, int p2payoff)
    {
        lastAction = otherAction;
    }
}
