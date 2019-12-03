package com.company.Str;

import com.company.Strategy;


public class GrimTriggerSoft extends Strategy
{
    int otherWorst = 0;
    int punishDuration = 0;

    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        otherWorst = 0;
        punishDuration = 0;
    }
    public int output()
    {
        if(punishDuration > 2)
        {
            punishDuration--;
            return otherWorst;
        }
        if(punishDuration > 0)
        {
            punishDuration--;
            if(punishDuration == 0)
                otherWorst = 0;
            return 0;
        }

        return 0;
    }
    public void input(int otherAction, int p1payoff, int p2payoff)
    {

        otherWorst = Math.max(otherWorst, otherAction);
        if(punishDuration == 0 && otherAction > 0)
            punishDuration = 6;
    }
}
