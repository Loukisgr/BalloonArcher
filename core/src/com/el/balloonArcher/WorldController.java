package com.el.balloonArcher;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.el.balloonArcher.util.Constants;

/**
 * Created by Louki on 20/9/2016.
 */
public class WorldController extends InputAdapter
{
    private static final String TAG = WorldController.class.getName();
    private BalloonArcher app;

    public WorldController(BalloonArcher app)
    {
        Gdx.input.setInputProcessor(this);
        this.app = app;
        init();
    }
    private void init () { }

    public void update (float deltaTime)
    {
        handle_Input(deltaTime);
    }

    private void handle_Input(float deltaTime)
    {
        //if (Gdx.app.getType() != Application.ApplicationType.Desktop) return;
        // Selected Sprite Controls
        if (Gdx.input.isKeyPressed(Keys.W)) app.get_Archer().move(-Constants.ARCHER_SPEED*deltaTime);
        if (Gdx.input.isKeyPressed(Keys.S))  app.get_Archer().move(Constants.ARCHER_SPEED*deltaTime);
        if (Gdx.input.isTouched())
        {
           if(app.get_Archer().get_pos()< Gdx.input.getY()){app.get_Archer().move(-Constants.ARCHER_SPEED*deltaTime);}
           else
           {
               app.get_Archer().move(Constants.ARCHER_SPEED*deltaTime);
           }
            System.out.println(Gdx.input.getY());
        }
    }

    public float get_Archer_pos()
    {
        return app.get_Archer().get_pos();
    }

}
