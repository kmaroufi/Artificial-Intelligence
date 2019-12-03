package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class DFA extends Strategy
{
    int current;
    public int[] outputs;
    public int[][] transitions;

    public DFA (int[] outputs, int[][] transitions)
    {
        this.outputs = new int[outputs.length];
        for(int i=0; i<outputs.length; i++)
            this.outputs[i] = outputs[i];
        this.transitions = new int[transitions.length][];
        for(int i=0; i<transitions.length; i++)
        {
            this.transitions[i] = new int[transitions[i].length];
            for (int j = 0; j < transitions[i].length; j++)
                this.transitions[i][j] = transitions[i][j];
        }
        current = 0;
    }

    public void reset(int[][] p1payoffs, int[][] p2payoffs, int count, int thisPlayer)
    {
        current = 0;
    }

    public int output()
    {
        return outputs[current];
    }

    public void input(int otherAction, int p1payoff, int p2payoff)
    {
        current = transitions[current][otherAction];
    }

    public void print()
    {
        System.out.println("States: " + outputs.length);
        System.out.println("Outputs: ");
        for(int i=0; i<outputs.length; i++)
            System.out.println(outputs[i]);
        System.out.println("Transitions: ");
        for(int i=0; i<transitions.length; i++)
        {
            System.out.println("From State: " + i);
            for(int j=0; j<transitions[i].length; j++)
                System.out.println(transitions[i][j]);
        }
    }

    public void saveToFile(String name) throws Exception
    {
        FileOutputStream fout = new FileOutputStream(name + "_transitions", true);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(transitions);
        oos.close();
        fout.close();

        fout = new FileOutputStream(name + "_outputs", true);
        oos = new ObjectOutputStream(fout);
        oos.writeObject(outputs);
        oos.close();
        fout.close();
    }

    public static DFA loadFromFile(String name) throws Exception
    {
        FileInputStream fin = new FileInputStream(name + "_transitions");
        ObjectInputStream ois = new ObjectInputStream(fin);
        int[][] transitions = (int[][])ois.readObject();
        ois.close();
        fin.close();

        fin = new FileInputStream(name + "_outputs");
        ois = new ObjectInputStream(fin);
        int[] outputs = (int[])ois.readObject();
        ois.close();
        fin.close();

        return new DFA(outputs, transitions);
    }
}
