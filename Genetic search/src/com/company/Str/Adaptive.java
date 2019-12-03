package com.company.Str;

import com.company.Strategy;


public class Adaptive extends Strategy
{
    int initialActions = 0;
    int actionCount = 0;
    int thisPlayer;

    int myLastAction = 0;
    int[] actionScore;
    int[] actionRepeat;


    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        initialActions = 10;
        if(thisPlayer == 1)
            actionCount = p1payoffs.length;
        else
            actionCount = p1payoffs[0].length;

        initialActions = 0;
        actionScore = new int[actionCount];
        actionRepeat = new int[actionCount];
        this.thisPlayer = thisPlayer;
    }
    public int output()
    {
        if(initialActions < 10)
        {
            initialActions++;
            myLastAction = 0;
            return 0;
        }
        for(int i=1; i<actionCount; i++)
        if(initialActions < 10+i*2)
        {
            initialActions++;
            myLastAction = i;
            return i;
        }

        int max = 0;

        for(int i=0; i<actionCount; i++)
            if(1.0*actionScore[i]/actionRepeat[i] > 1.0*actionScore[max]/actionRepeat[max])
                max = i;

        myLastAction = max;
        return max;
    }
    public void input(int otherAction, int p1payoff, int p2payoff)
    {
        if(thisPlayer == 1)
            actionScore[myLastAction] += p1payoff;
        else
            actionScore[myLastAction] += p2payoff;
        actionRepeat[myLastAction] ++;
    }
}
