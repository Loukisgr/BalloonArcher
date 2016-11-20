package com.el.balloonArcher.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.el.balloonArcher.util.Constants;

/**
 * Created by Louki on 12/11/2016.
 */

public class SimpleGameLevelLimit extends SimpleGame
{
    private int level_limit=10;

    public SimpleGameLevelLimit(int level_limit)
    {
        super();
        this.level_limit=level_limit;
    }

    @Override
    public void load_level()
    {
        if(app.get_level()<=level_limit)
        {
            super.load_level();
        }

    }

    public void game_status()
    {
        if( app.get_game_state().equals(Constants.Game_State.ACTIVE))
        {
            //if balloon exists but no arrows
            if ((!app.get_Archer().has_remaining_arrows()) && (has_remaining_balloons()))
            {
                //check for high Score
                Preferences prefs = Gdx.app.getPreferences("BalloonArcher");

                if ((!prefs.contains("highScoreSimpleGameTill"+level_limit)) || (prefs.getInteger("highScoreSimpleGameTill"+level_limit) < app.get_score()))
                {
                    prefs.putInteger("highScoreSimpleGameTill"+level_limit, app.get_score());
                    prefs.flush();
                    app.set_game_state(Constants.Game_State.HIGH_SCORE);
                }
                else
                {
                    app.set_game_state(Constants.Game_State.GAME_OVER);
                }

            }
            else if ((app.get_Archer().has_remaining_arrows()) && (!has_remaining_balloons()))
            {
                if(app.get_level()>level_limit)
                {
                    app.set_game_state(Constants.Game_State.GAME_WINNER);

                    //check for high Score
                    Preferences prefs = Gdx.app.getPreferences("BalloonArcher");
                    prefs.putBoolean("SimpleGameTill"+level_limit, true);

                    if ((!prefs.contains("highScoreSimpleGameTill"+level_limit)) || (prefs.getInteger("highScoreSimpleGameTill"+level_limit) < app.get_score()))
                    {
                        prefs.putInteger("highScoreSimpleGameTill"+level_limit, app.get_score());
                        prefs.flush();
                        app.set_game_state(Constants.Game_State.HIGH_SCORE);
                    }

                }
                else
                {
                    app.set_game_state(Constants.Game_State.LEVEL_PASSED);
                }
            }
        }
    }

    public int get_level_limit()
    {
        return level_limit;
    }

}
