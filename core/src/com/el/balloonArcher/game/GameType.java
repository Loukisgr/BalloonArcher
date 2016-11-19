package com.el.balloonArcher.game;

import com.el.balloonArcher.Balloon;
import com.el.balloonArcher.screens.GameScreen;

import java.util.ArrayList;

/**
 * Created by Louki on 8/11/2016.
 */

public interface GameType
{

    public void init();

    public void update(float deltaTime);
    public void load_level();
    public boolean is_game_over();
    public void game_status();
    public ArrayList<Balloon> get_balloons();
    public void set_app(GameScreen app);
}
