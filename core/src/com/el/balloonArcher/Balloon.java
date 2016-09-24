package com.el.balloonArcher;

/**
 * Created by Louki on 18/9/2016.
 */
public class Balloon
{
    private boolean has_gift;
    private float speed;
    private static float STARTING_SPEED=20f;


    public Balloon(boolean has_gift, float speed)
    {
        this.has_gift=has_gift;
        this.speed=speed+20f;
    }

    public boolean get_has_gift()
    {
        return has_gift;
    }


}
