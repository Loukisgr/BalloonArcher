package com.el.balloonArcher.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.el.balloonArcher.Balloon;
import com.el.balloonArcher.screens.GameScreen;
import com.el.balloonArcher.util.Assets;
import com.el.balloonArcher.util.AudioManager;
import com.el.balloonArcher.util.Constants;

import java.util.Random;

/**
 * Created by Louki on 8/11/2016.
 */

public class SimpleGame extends BasicGameType
{

    public SimpleGame()
    {
        init();
    }

    public SimpleGame(GameScreen app)
    {
        super(app);
        init();
    }

    @Override
    public void update(float deltaTime)
    {
        game_status();
        if(!is_game_over())
        {
            //app.get_Archer().update_timers(deltaTime);

            for (Balloon i : balloons)
            {
                if (i.is_hit() && i.get_destroy_timer()>0)
                {
                    i.update_timers(deltaTime);
                }
            }

            //handle_Input(deltaTime);
            move_objects(deltaTime);
            check_collisions();
        }
    }

    @Override
    public void load_level()
    {
        balloons.clear();

        int i = app.get_level();
        Random rnd = new Random();
        int base=0;
        float r=0;

        while (i>0)
        {
            r=rnd.nextInt(10);
            r=r/10+base;

            if (i%10==0)
            {
                balloons.add(new Balloon(true,(GameScreen.GUI_HEIGHT/150)*app.get_level(),r));
            }
            else
            {
                balloons.add(new Balloon(false,(GameScreen.GUI_HEIGHT/140)*app.get_level(),r));
            }

            i-=1;
            base+=1;
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

                if ((!prefs.contains("highScore")) || (prefs.getInteger("highScore") < app.get_score()))
                {
                    prefs.putInteger("highScore", app.get_score());
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
                app.set_game_state(Constants.Game_State.LEVEL_PASSED);
            }
        }
    }

    private void move_objects(float deltaTime)
    {
        //move balloons
        for (Balloon i : balloons)
        {
            if (!i.is_hit())
            {
                i.move(deltaTime);
            }

        }

        //move arrows
        app.get_Archer().move_arrows(deltaTime);
    }

    private void check_collisions()
    {
        if((app.get_Archer().has_remaining_arrows()) && (has_remaining_balloons()))
        {

            for (int  b=0; b<balloons.size(); b++)
            {
                if(!balloons.get(b).is_hit())
                {
                    for (int a=0; a<app.get_Archer().get_arrows().size(); a++)
                    {
                        if((app.get_Archer().get_arrows().get(a).is_shot()) && ((!app.get_Archer().get_arrows().get(a).to_remove())))
                        {
                            if(balloons.get(b).collides_with(app.get_Archer().get_arrows().get(a)))
                            {
                                app.add_to_score(app.get_level());
                                AudioManager.instance.play(Assets.instance.sounds.pop);

                                if (balloons.get(b).get_has_gift())
                                {
                                    app.get_Archer().add_arrow(app.get_level());
                                }
                            }
                        }
                    }
                }
            }

        }
    }

}
