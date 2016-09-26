package com.el.balloonArcher;

import com.el.balloonArcher.util.Constants;

import java.util.ArrayList;

/**
 * Created by Louki on 18/9/2016.
 */
public class Archer
{
    private ArrayList<Arrow> arrows;
    private float y=Constants.VIEWPORT_GUI_HEIGHT;

    public Archer(int level)
    {
        arrows = new ArrayList<Arrow>();
        init_level(level);
    }

    public void init_level(int level)
    {
        arrows.clear();

        int i = level;

        //limit arrows to 100
        if (i>Constants.NO_OF_ARROW_LIMIT)
        {
            i=100;
        }

        while (i>0)
        {
            arrows.add(new Arrow(level));
            i-=1;
        }

    }

    public float get_pos()
    {
        return y;
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
       /* if(y+pix > Constants.VIEWPORT_HEIGHT)
        {
            y=Constants.VIEWPORT_HEIGHT;
        }
        else */if(y+pix <0 )
        {
            y=0;
        }
        else
        {
            y=y+pix;
        }
    }

    public void move_arrows(float deltaTime)
    {

        for (Arrow i : arrows)
        {

            i.move(deltaTime);
        }
    }

    public ArrayList<Arrow> get_arrows()
    {
        return arrows;
    }


}
