package com.el.balloonArcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.el.balloonArcher.util.Constants;

import java.util.ArrayList;

/**
 * Created by Louki on 20/9/2016.
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

        while (i>0)
        {
            if (i%10==0)
            {
                balloons.add(new Balloon(true,app.get_level()));
            }
            else
            {
                balloons.add(new Balloon(false,app.get_level()));
            }

            i-=1;
        }
    }

    public void update (float deltaTime)
    {
        handle_Input(deltaTime);
        move_objects(deltaTime);
    }

    private void handle_Input(float deltaTime)
    {
        if (app.is_paused()) {return;}
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
        if (Gdx.input.isKeyPressed(Keys.W)) app.get_Archer().move(-Constants.ARCHER_SPEED*deltaTime);
        if (Gdx.input.isKeyPressed(Keys.S))  app.get_Archer().move(Constants.ARCHER_SPEED*deltaTime);

        if (Gdx.input.isTouched())
        {
            if(app.is_paused())
            {
                app.resume();
            }
            else
            {
                if (app.get_Archer().get_pos() < Gdx.input.getY()) {
                    app.get_Archer().move(-Constants.ARCHER_SPEED * deltaTime);
                }
                else
                {
                    app.get_Archer().move(Constants.ARCHER_SPEED * deltaTime);
                }
                System.out.println(Gdx.input.getY());
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

    public int get_no_of_baloons()
    {
        return balloons.size();
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

}
