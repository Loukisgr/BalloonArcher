package com.el.balloonArcher;

import com.el.balloonArcher.util.Constants;

/**
 * Created by Louki on 18/9/2016.
 */
public class Balloon
{
    private boolean has_gift;
    private float speed;
    private static float STARTING_SPEED=20f;
    private boolean is_hit=false;
    private float x,y = 0;


    public Balloon(boolean has_gift, float speed)
    {
        this.has_gift=has_gift;
        this.speed=speed+20f;
    }

    public boolean get_has_gift()
    {
        return has_gift;
    }

    public boolean is_top()
    {
        if(y<= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean is_hit()
    {
        return is_hit;
    }

    public void move(float deltaTime)
    {
        if (!is_hit())
        {
            if(is_top())
            {
                y=-10;
            }
            else
            {
                y=y+this.speed*deltaTime;
            }
        }
    }


}
