package com.el.balloonArcher;

import com.el.balloonArcher.util.Constants;

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
    private float y=Constants.VIEWPORT_GUI_HEIGHT;

    public Archer(int level,int score)
    {
        this.level=level;
        balloons = new ArrayList<Balloon>();
        arrows = new ArrayList<Arrow>();
        init_level(this.level);

        if (level ==1)
        {
            this.score=0;
        }
        else
        {
            this.score=score;
        }
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

    public float get_pos()
    {
        return y;
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

    public void move(float pix)
    {
        /*if(y+pix > Constants.VIEWPORT_HEIGHT)
        {
            y=Constants.VIEWPORT_HEIGHT;
        }
        else */ if(y+pix <0 )
        {            System.out.println("oh"+pix);
            y=0;
        }
        else
        {
            y=y+pix;
            System.out.println("YEP"+y);
        }
    }


}
