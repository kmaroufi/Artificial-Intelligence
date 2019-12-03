package com.company.Str;

import com.company.Strategy;

import java.util.Random;


public class TitForTatExpRemorse extends Strategy
{
    Random random = new Random();
    double exprProb = 0.1;
    int lastAction = 0;
    int actionCount = 0;
    boolean cheated = false;

    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        lastAction = 0;
        random.setSeed(System.currentTimeMillis());
        cheated = false;
        if(thisPlayer == 1)
            actionCount = p1payoffs.length;
        else
            actionCount = p1payoffs[0].length;
    }
    public int output()
    {
        if(cheated && lastAction > 0)
        {
            cheated = false;
            return 0;
        }
        if(random.nextDouble() < exprProb)
        {
            cheated = true;
            return random.nextInt(actionCount-1)+1;
        }
        else
        {
            cheated = false;
            return lastAction;
        }
    }
    public void input(int otherAction, int p1payoff, int p2payoff)
    {
        lastAction = otherAction;
    }
}
