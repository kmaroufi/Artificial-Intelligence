package com.company.Str;

import com.company.Strategy;


public class TitForTatHarsh extends Strategy
{
    int toDo = 0;
    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        toDo = 0;
    }
    public int output()
    {
        return toDo;
    }
    public void input(int otherAction, int p1payoff, int p2payoff)
    {
        if(otherAction > 0)
            toDo = 5;
        else
            toDo = 0;
    }
}
