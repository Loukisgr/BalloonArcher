package com.el.balloonArcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.el.balloonArcher.util.Constants;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Loukis on 20/9/2016.
 */
public class WorldController extends InputAdapter
{
    private static final String TAG = WorldController.class.getName();
    private BalloonArcher app;
    private ArrayList<Balloon> balloons;

    public WorldController(BalloonArcher app)
    {
        Gdx.input.setInputProcessor(this);
        this.app = app;
        init();
    }

    private void init ()
    {
        balloons = new ArrayList<Balloon>();
        load_level();
    }

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
                balloons.add(new Balloon(true,app.get_level(),r));
            }
            else
            {
                balloons.add(new Balloon(false,app.get_level(),r));
            }

            i-=1;
            base+=1;
        }
    }

    public void update (float deltaTime)
    {

        game_status();
        if(!is_game_over())
        {
            app.get_Archer().update_timers(deltaTime);
            handle_Input(deltaTime);
            move_objects(deltaTime);
            check_collisions();
        }
    }

    private void handle_Input(float deltaTime)
    {
        //if (app.is_paused()) {return;}
        //if (Gdx.app.getType() != Application.ApplicationType.Desktop) return;
        // Selected Sprite Controls
        //pause\resume
        if (Gdx.input.isKeyPressed(Keys.P))
        {
            if(app.is_paused())
            {
                app.resume();
            }
            else
            {
                app.pause();
            }
        }

        if(!app.is_paused())
        {
            if (Gdx.input.isKeyPressed(Keys.W))
                app.get_Archer().move(-Constants.ARCHER_SPEED * deltaTime);
            if (Gdx.input.isKeyPressed(Keys.S))
                app.get_Archer().move(Constants.ARCHER_SPEED * deltaTime);
            if (Gdx.input.isKeyPressed(Keys.SPACE)) app.get_Archer().shoot();
        }

        if (Gdx.input.isTouched())
        {
            //System.out.println("W="+BalloonArcher.GUI_WIDTH+",H="+BalloonArcher.GUI_HEIGHT);
            //System.out.println("X="+Gdx.input.getX()+",Y="+Gdx.input.getY());
            //TODO: 2/10/2016  add pause fo mobile
            if(app.is_paused())
            {
                app.resume();
            }
 /*           else if(app.is_game_over())
            {
                app.
            } */
            else
            {
                //shoot
                if(Gdx.input.getX() >= BalloonArcher.GUI_WIDTH /2)
                {
                    app.get_Archer().shoot();
                }
                //move
                else
                {
                    if (app.get_Archer().get_pos() < (BalloonArcher.GUI_HEIGHT - Gdx.input.getY()))
                    {
                        app.get_Archer().move(Constants.ARCHER_SPEED * deltaTime);
                    }
                    else
                    {
                        app.get_Archer().move(-Constants.ARCHER_SPEED * deltaTime);
                    }
                    System.out.println(app.get_Archer().get_pos()+" y="+(BalloonArcher.GUI_HEIGHT - Gdx.input.getY()));
                }
            }
        }
    }

    public float get_Archer_pos()
    {
        return app.get_Archer().get_pos();
    }

    public ArrayList<Arrow> get_arrows()
    {
        return app.get_Archer().get_arrows();
    }

    public ArrayList<Balloon> get_baloons()
    {
        return balloons;
    }

    public int get_score()
    {
        return app.get_score();
    }

    public int get_no_of_balloons()
    {
        return balloons.size();
    }

    public int get_no_of_left_arrows()
    {
        return app.get_Archer().remaining_arrows();
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

    private boolean  has_remaining_balloons()
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


    private boolean is_game_over()
    {
        //if balloon exists but no arrows
        if(app.get_game_state().equals((Constants.Game_State.GAME_OVER)))
        {
            return true;
        }

        return false;
    }

    private void game_status()
    {
        if( app.get_game_state().equals(Constants.Game_State.ACTIVE))
        {
            //if balloon exists but no arrows
            if ((!app.get_Archer().has_remaining_arrows()) && (has_remaining_balloons())) {
                app.set_game_state(Constants.Game_State.GAME_OVER);
            } else if ((app.get_Archer().has_remaining_arrows()) && (!has_remaining_balloons())) {
                app.set_game_state(Constants.Game_State.GAME_WINNER);
            }
        }

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
                            }
                        }
                    }
                }
            }

        }
    }

    public int get_level()
    {
        return app.get_level();
    }


}
