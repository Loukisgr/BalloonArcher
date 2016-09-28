package com.el.balloonArcher.util;

import com.el.balloonArcher.BalloonArcher;

/**
 * Created by Louki on 20/9/2016.
 */
public class Constants
{
    // Visible game world is 10 meters wide
   // public static final float VIEWPORT_WIDTH = 10.0f;
    // Visible game world is 5 meters tall
   // public static final float VIEWPORT_HEIGHT = 5.0f;
    // GUI Width
    public static final float VIEWPORT_GUI_WIDTH = 800.0f;
    // GUI Height
    public static final float VIEWPORT_GUI_HEIGHT = 480.0f;
    public static final float ANIMATION_TIMER  = 4f;
    public static final int ANIMATION_SPLITS = 4;
    public static final float ARCHER_SPEED = 200f;
    public static final int ARCHER_HEIGHT = 255;
    public static final float BALLOON_STARTING_SPEED=50f;
    public static final float ARROW_STARTING_SPEED=200f;
    public static final int NO_OF_ARROW_LIMIT=100;
    public static final int BALLOON_WIDTH= BalloonArcher.GUI_WIDTH/10;
    public static final int BALLOON_HEIGHT= BalloonArcher.GUI_HEIGHT/12;
    public static final int ARROW_WIDTH= 53;
    public static final int ARROW_HEIGHT=14;
    public static final float SHOOT_TIMER=1f;


    public enum Game_State
    {
        PAUSED,LOADING,ACTIVE,GAME_OVER,GAME_WINNER

    }

}
