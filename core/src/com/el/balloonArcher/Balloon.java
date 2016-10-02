package com.el.balloonArcher;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
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
    private float y = BalloonArcher.GUI_HEIGHT;
    private float x = BalloonArcher.GUI_WIDTH /3 *2;
    private Rectangle body;
    private float show_timer=0;


    public Balloon(boolean has_gift, float speed)
    {
        this.has_gift=has_gift;
        this.speed=speed+Constants.BALLOON_STARTING_SPEED;
        this.body= new Rectangle();

        Random rnd = new Random();
        x=rnd.nextInt((int)BalloonArcher.GUI_WIDTH/6)+(int)BalloonArcher.GUI_WIDTH/3 *2 ;
    }

    public Balloon(boolean has_gift, float speed, float show_timer)
    {
        this.has_gift=has_gift;
        this.speed=speed+Constants.BALLOON_STARTING_SPEED;
        this.body= new Rectangle();

        Random rnd = new Random();
        x=rnd.nextInt((int)BalloonArcher.GUI_WIDTH/6)+(int)BalloonArcher.GUI_WIDTH/3 *2 ;
        this.show_timer=show_timer;
    }

    public Balloon(boolean has_gift, float speed, float show_timer,float x)
    {
        this.has_gift=has_gift;
        this.speed=speed+Constants.BALLOON_STARTING_SPEED;
        this.body= new Rectangle();
        this.x=x;
        this.show_timer=show_timer;
    }

    public boolean get_has_gift()
    {
        return has_gift;
    }

    public boolean is_top()
    {
        if(y>= BalloonArcher.GUI_HEIGHT)
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

            if(show_timer==0)
            {
                if (is_top()) {
                    y = 0;
                    body.set(x, y, Constants.BALLOON_WIDTH, Constants.BALLOON_HEIGHT);
                } else {
                    y = y + this.speed * deltaTime;
                    body.set(x, y, Constants.BALLOON_WIDTH, Constants.BALLOON_HEIGHT);
                }
            }
            else if(show_timer-deltaTime >0)
            {
                show_timer-=deltaTime;
            }
            else
            {
                show_timer=0;
            }

        }
    }

    public Rectangle get_body()
    {
        return body;
    }

    public float get_y()
    {
        return y;
    }

    public float get_x()
    {
        return x;
    }

    public boolean collides_with(Arrow a)
    {
        if(Intersector.overlaps(this.get_body(), a.get_body()))
        {
            this.is_hit=true;
            return true;
        }

        return false;
    }

}
