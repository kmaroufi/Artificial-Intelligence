package com.company.Str;

import com.company.Strategy;


public class UpDown extends Strategy
{
    boolean tick = false;
    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        tick = false;
    }
    public int output()
    {
        if(!tick)
            return 0;
        else
            return 5;

    }
    public void input(int otherAction, int p1payoff, int p2payoff)
    {
        tick = !tick;
    }

}
