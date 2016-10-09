package com.el.balloonArcher.util;

import com.el.balloonArcher.BalloonArcher;
import com.el.balloonArcher.screens.GameScreen;

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
    //public static final float ARCHER_SPEED = 200f;
    public static final float ARCHER_SPEED = GameScreen.GUI_HEIGHT/4;
    public static final int ARCHER_HEIGHT = GameScreen.GUI_HEIGHT/6;
    public static final int ARCHER_WIDTH = GameScreen.GUI_WIDTH/8;
    //public static final float BALLOON_STARTING_SPEED=140f;
    //public static final float ARROW_STARTING_SPEED=200f;
    public static final float BALLOON_STARTING_SPEED=GameScreen.GUI_HEIGHT/6;
    public static final float ARROW_STARTING_SPEED=GameScreen.GUI_WIDTH/2;
    public static final int NO_OF_ARROW_LIMIT=100;
    public static final int BALLOON_WIDTH= GameScreen.GUI_WIDTH/20;
    public static final int BALLOON_HEIGHT= GameScreen.GUI_HEIGHT/24;
    public static final int ARROW_WIDTH= GameScreen.GUI_WIDTH/25;
    public static final int ARROW_HEIGHT=GameScreen.GUI_HEIGHT/80;
    public static final float SHOOT_TIMER=1f;
    public static final int SCORE_TEXT_X=GameScreen.GUI_WIDTH/2;
    public static final int SCORE_TEXT_Y=GameScreen.GUI_HEIGHT/20 * 19;
    public static final int ARROW_TEXT_X=GameScreen.GUI_WIDTH/4;
    public static final int ARROW_TEXT_Y=GameScreen.GUI_HEIGHT/20 * 19;
    public static final int INFO_TEXT_X=GameScreen.GUI_WIDTH/6 * 2;
    public static final int INFO_TEXT_Y=GameScreen.GUI_HEIGHT/2;
    public static final String SKIN_LIBGDX_UI = "images/uiskin.json";
    public static final String SKIN_BALLOONARCHER_UI = "images/balloonarcher-ui.json";
    //public static final String TEXTURE_ATLAS_UI = "images/canyonbunny-ui.pack";
    public static final String TEXTURE_ATLAS_LIBGDX_UI = "images/uiskin.atlas";
    public static final String PREFERENCES ="pref.txt";

    public enum Game_State
    {
        PAUSED,LOADING,ACTIVE,GAME_OVER,GAME_WINNER

    }

}
