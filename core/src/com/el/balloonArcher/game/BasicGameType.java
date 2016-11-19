package com.el.balloonArcher.game;

import com.el.balloonArcher.Balloon;
import com.el.balloonArcher.screens.GameScreen;
import com.el.balloonArcher.util.Constants;

import java.util.ArrayList;

/**
 * Created by Louki on 12/11/2016.
 */

public abstract class BasicGameType implements GameType
{

    protected ArrayList<Balloon> balloons;
    protected GameScreen app;

    public BasicGameType()
    {

    }

    public BasicGameType(GameScreen app)
    {
        this.app=app;
    }

    @Override
    public void init()
    {
        balloons = new ArrayList<Balloon>();
    }

    @Override
    public void update(float deltaTime)
    {

    }

    @Override
    public void load_level()
    {

    }

    @Override
    public boolean is_game_over()
    {
        return app.get_game_state().equals((Constants.Game_State.GAME_OVER)) || app.get_game_state().equals((Constants.Game_State.HIGH_SCORE))
                || app.get_game_state().equals((Constants.Game_State.GAME_WINNER)) ;
    }

    @Override
    public ArrayList<Balloon> get_balloons()
    {
        return balloons;
    }

    public void set_app(GameScreen app)
    {
        this.app=app;
    }

    protected boolean has_remaining_balloons()
    {

        for (Balloon b : balloons)
        {
            if (!b.is_hit())
            {
                return true;
            }
        }

        return false;
    }
}
