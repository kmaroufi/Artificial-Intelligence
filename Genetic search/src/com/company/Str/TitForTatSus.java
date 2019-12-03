package com.company.Str;

import com.company.Strategy;

import java.util.Random;


public class TitForTatSus extends Strategy
{
    int lastAction = 0;
    Random random = new Random();

    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        int actionCount;
        if(thisPlayer == 1)
            actionCount = p1payoffs.length;
        else
            actionCount = p1payoffs[0].length;

        lastAction = random.nextInt(actionCount-1) +1;
    }
    public int output()
    {
        return lastAction;
    }
    public void input(int otherAction, int p1payoff, int p2payoff)
    {
        lastAction = otherAction;
    }
}
