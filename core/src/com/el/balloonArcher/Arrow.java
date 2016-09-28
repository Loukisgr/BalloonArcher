package com.el.balloonArcher;

import com.badlogic.gdx.math.Rectangle;
import com.el.balloonArcher.util.Constants;

/**
 * Created by Loukis on 18/9/2016.
 */
public class Arrow
{

    private float speed;
    private float x = 100;
    private float y = 0;
    private boolean shot;
    private boolean remove=false;
    private Rectangle body;


    public Arrow(float speed)
    {
        this.speed=speed+ Constants.ARROW_STARTING_SPEED;
        this.body = new Rectangle();
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
                body.set(x,y,Constants.ARROW_WIDTH,Constants.ARROW_HEIGHT);
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

    public Rectangle get_body()
    {
        return body;
    }


}
