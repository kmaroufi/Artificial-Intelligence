package com.company.Str;

import com.company.Strategy;


public class GrimTrigger extends Strategy
{
    int otherWorst = 0;

    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        otherWorst = 0;
    }
    public int output()
    {
        return otherWorst;
    }
    public void input(int otherAction, int p1payoff, int p2payoff)
    {
        otherWorst = Math.max(otherWorst, otherAction);
    }
}
