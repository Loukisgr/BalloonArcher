package com.el.balloonArcher;

import java.util.ArrayList;

/**
 * Created by Louki on 18/9/2016.
 */
public class Archer
{

    private ArrayList<Balloon> balloons;
    private ArrayList<Arrow> arrows;
    private int score;
    private int level;

    public Archer(int level)
    {
        this.level=level;
        balloons = new ArrayList<Balloon>();
        arrows = new ArrayList<Arrow>();
        init_level(this.level);
    }

    public void init_level(int level)
    {
        arrows.clear();
        balloons.clear();

        int i = level;
        while (i>0)
        {
            if (i%10==0)
            {
                balloons.add(new Balloon(true,level));
            }
            else
            {
                balloons.add(new Balloon(false,level));
            }

            arrows.add(new Arrow(level));
            i-=1;
        }

    }

    public int get_no_of_baloons()
    {
       return balloons.size();
    }

    public void shoot()
    {
        if(arrows.size() >0)
        {
            arrows.remove(0);
        }
    }



}
