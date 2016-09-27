package com.el.balloonArcher;

import com.el.balloonArcher.util.Constants;

/**
 * Created by Louki on 18/9/2016.
 */
public class Arrow
{

    private float speed;
    private float x = 100;
    private float y = 0;
    private boolean shot;
    private boolean remove=false;


    public Arrow(float speed)
    {
        this.speed=speed+ Constants.ARROW_STARTING_SPEED;
        shot=false;
    }

    public void shoot(float y)
    {
        this.shot=true;
        this.y=y;
    }

    public boolean is_shot()
    {
        return this.shot;
    }

    public boolean to_remove()
    {
        return remove;
    }

    public void move(float deltaTime)
    {
        if (is_shot())
        {
            if(x>BalloonArcher.GUI_WIDTH)
            {
                remove=true;
            }
            else
            {
                x=x+speed*deltaTime;
            }
        }
    }

    public float get_x()
    {
        return x;
    }

    public float get_y()
    {
        return y;
    }

}
