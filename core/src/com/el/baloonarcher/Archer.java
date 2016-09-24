package com.el.baloonarcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louki on 18/9/2016.
 */
public class Archer
{

    private ArrayList<Baloon> baloons;
    private ArrayList<Arrow> arrows;
    private int score;
    private int level;

    public Archer(int level)
    {
        this.level=level;
        baloons = new ArrayList<Baloon>();
        arrows = new ArrayList<Arrow>();
        init_level(this.level);
    }

    public void init_level(int level)
    {
        arrows.clear();
        baloons.clear();

        int i = level;
        while (i>0)
        {
            if (i%10==0)
            {
                baloons.add(new Baloon(true,level));
            }
            else
            {
                baloons.add(new Baloon(false,level));
            }

            arrows.add(new Arrow(level));
            i-=1;
        }

    }

    public int get_no_of_baloons()
    {
       return baloons.size();
    }

    public void shoot()
    {
        if(arrows.size() >0)
        {
            arrows.remove(0);
        }
    }



}
