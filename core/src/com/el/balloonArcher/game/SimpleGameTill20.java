package com.el.balloonArcher.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.el.balloonArcher.util.Constants;

/**
 * Created by Louki on 12/11/2016.
 */

public class SimpleGameTill20 extends SimpleGame
{

    @Override
    public void load_level()
    {
        if(app.get_level()<=2)
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

                if ((!prefs.contains("highScoreSimpleGameTill20")) || (prefs.getInteger("highScoreSimpleGameTill20") < app.get_score()))
                {
                    prefs.putInteger("highScoreSimpleGameTill20", app.get_score());
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
                if(app.get_level()>2)
                {
                    app.set_game_state(Constants.Game_State.GAME_WINNER);

                    //check for high Score
                    Preferences prefs = Gdx.app.getPreferences("BalloonArcher");
                    prefs.putBoolean("SimpleGameTill20", true);

                    if ((!prefs.contains("highScoreSimpleGameTill20")) || (prefs.getInteger("highScoreSimpleGameTill20") < app.get_score()))
                    {
                        prefs.putInteger("highScoreSimpleGameTill20", app.get_score());
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



}
