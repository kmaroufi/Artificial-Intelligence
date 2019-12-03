package com.company.Str;

import com.company.Strategy;


public class TitForTat extends Strategy
{
    int lastAction = 0;
    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        lastAction = 0;
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
