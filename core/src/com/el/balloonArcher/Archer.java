package com.el.balloonArcher;

import com.el.balloonArcher.screens.GameScreen;
import com.el.balloonArcher.util.Constants;

import java.util.ArrayList;

/**
 * Created by Louki on 18/9/2016.
 */
public class Archer
{
    private ArrayList<Arrow> arrows;
    private float y= GameScreen.GUI_HEIGHT/10;
    private float shoot_timer=0;

    public Archer(int level)
    {
        arrows = new ArrayList<Arrow>();
        init_level(level);
    }

    public void init_level(int level)
    {
        shoot_timer=Constants.SHOOT_TIMER;
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

        if (level <10)
        {
            for (int j =0 ; j < 5; j++)
            {
                arrows.add(new Arrow(level));
            }
        }

    }

    public float get_pos()
    {
        return y;
    }

    public void shoot()
    {
        if(shoot_timer==0)
        {
            for (int i = 0; i < arrows.size(); i++) {
                if (!arrows.get(i).is_shot())
                {
                    arrows.get(i).shoot(Constants.ARCHER_HEIGHT / 2 + this.y);
                    shoot_timer = Constants.SHOOT_TIMER;
                    return;
                }
            }
        }

    }

    public void move(float pix)
    {
       /* if(y+pix > Constants.VIEWPORT_HEIGHT)
        {
            y=Constants.VIEWPORT_HEIGHT;
        }
        else */if(y+Constants.ARCHER_HEIGHT+pix <0 )
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

    public void add_arrow(int level)
    {
        if(arrows.size()<Constants.NO_OF_ARROW_LIMIT)
        {
            arrows.add(new Arrow(level));
        }
    }

    public ArrayList<Arrow> get_arrows()
    {
        return arrows;
    }

    public int remaining_arrows()
    {
        int i=0;

        for (int j =0 ; j < arrows.size(); j++)
        {
            if(!arrows.get(j).is_shot())
            {
                i++;
            }
        }

        return i;
    }

    public boolean has_remaining_arrows()
    {

        for (int i =0 ; i < arrows.size();i++)
        {
            if((!arrows.get(i).is_shot()) ||(!arrows.get(i).to_remove()))
            {
                return true;
            }
        }

        return false;
    }

    public void update_timers(float deltaTime)
    {
       if (shoot_timer>0)
       {
           this.shoot_timer -= deltaTime;
           if (this.shoot_timer<0)
           {
               this.shoot_timer=0;
           }
       }
    }

}
