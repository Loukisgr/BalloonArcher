package com.el.balloonArcher;

import com.el.balloonArcher.util.Constants;

import java.util.Random;

/**
 * Created by Louki on 18/9/2016.
 */
public class Balloon
{
    private boolean has_gift;
    private float speed;
    private boolean is_hit=false;
    private float y = Constants.VIEWPORT_GUI_HEIGHT;
    private float x = Constants.VIEWPORT_GUI_WIDTH-100;


    public Balloon(boolean has_gift, float speed)
    {
        this.has_gift=has_gift;
        this.speed=speed+Constants.BALLOON_STARTING_SPEED;
        Random rnd = new Random();
        x=rnd.nextInt(100)+Constants.VIEWPORT_GUI_WIDTH-100;
    }

    public Balloon(boolean has_gift, float speed, float x)
    {
        this.has_gift=has_gift;
        this.speed=speed+Constants.BALLOON_STARTING_SPEED;
        this.x=x;
    }

    public boolean get_has_gift()
    {
        return has_gift;
    }

    public boolean is_top()
    {
        if(y< 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean is_hit()
    {
        return is_hit;
    }

    public void move(float deltaTime)
    {
        if (!is_hit())
        {
            if(is_top())
            {
                y= Constants.VIEWPORT_GUI_HEIGHT;
            }
            else
            {
                y=y+this.speed*deltaTime;
            }
        }
    }

    public float get_y()
    {
        return y;
    }

    public float get_x()
    {
        return x;
    }

}
