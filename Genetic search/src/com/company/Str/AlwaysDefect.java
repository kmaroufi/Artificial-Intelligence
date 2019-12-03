package com.company.Str;

import com.company.Strategy;

import java.util.Random;


public class AlwaysDefect extends Strategy
{
    Random random = new Random();
    int actionCount = 0;

    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        if(thisPlayer == 1)
            actionCount = p1payoffs.length;
        else
            actionCount = p1payoffs[0].length;
    }
    public int output()
    {
        return random.nextInt(actionCount-1) +1;
    }
    public void input(int otherAction, int p1payoff, int p2payoff)
    {

    }
}
