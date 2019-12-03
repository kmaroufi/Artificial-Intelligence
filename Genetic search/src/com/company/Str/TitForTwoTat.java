package com.company.Str;

import com.company.Strategy;


public class TitForTwoTat extends Strategy
{
    int lastAction = 0;
    int lastLastAction = 0;
    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        lastAction = 0;
        lastLastAction = 0;
    }
    public int output()
    {
        return Math.min(lastAction, lastLastAction);
    }
    public void input(int otherAction, int p1payoff, int p2payoff)
    {
        lastLastAction = lastAction;
        lastAction = otherAction;
    }
}
