package com.el.balloonArcher.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.StringBuilder;
import com.el.balloonArcher.Archer;
import com.el.balloonArcher.BalloonArcher;
import com.el.balloonArcher.WorldController;
import com.el.balloonArcher.WorldRenderer;
import com.el.balloonArcher.util.Assets;
import com.el.balloonArcher.util.Constants;

/**
 * Created by Louki on 3/10/2016.
 */

public class GameScreen extends AbstractGameScreen
{

    private com.el.balloonArcher.Archer player;
    private static final String TAG = BalloonArcher.class.getName();
    private WorldController worldController;
    private WorldRenderer worldRenderer;
    private Constants.Game_State state;
    private int level=1;
    private int score=0;
    public static int GUI_WIDTH,GUI_HEIGHT;

    public GameScreen(Game game)
    {
        super(game);
    }

    @Override
    public void show()
    {
        Assets.instance.init(new AssetManager());
        GUI_WIDTH= Gdx.graphics.getWidth();
        GUI_HEIGHT= Gdx.graphics.getHeight();
        state= Constants.Game_State.LOADING;
        // Set Libgdx log level to DEBU
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        // Initialize controller and renderer
        player = new com.el.balloonArcher.Archer(level);
        worldController = new WorldController(this,game);
        worldRenderer = new com.el.balloonArcher.WorldRenderer(worldController);
        Gdx.input.setCatchBackKey(false);
        state= Constants.Game_State.ACTIVE;
    }

    public void render(float deltaTime)
    {
        // Update game world by the time that has passed since last rendered frame.

        if(!is_paused())
        {
            worldController.update(Gdx.graphics.getDeltaTime());
            //Gdx.gl.glClearColor(0.5f, 0.7f, 0.9f, 1);
            // Clears the screen
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            // Render game world to screen
            worldRenderer.render(Gdx.graphics.getDeltaTime());

            if(is_won())
            {
                worldRenderer.set_text_to_display(new StringBuilder("Starting Next Level..."));
                this.add_level();
                player.init_level(get_level());
                worldController.load_level();
                this.state=Constants.Game_State.ACTIVE;
                worldRenderer.clear_text_to_display();
            }
            else if(is_game_over())
            {
                worldRenderer.set_text_to_display(new StringBuilder("GAME OVER! Tap for New Game"), Color.RED);
            }

        }
    }

    @Override
    public void resize (int width, int height)
    {
        worldRenderer.resize(width, height);
    }

    @Override
    public void hide()
    {
        worldRenderer.dispose();
        Gdx.input.setCatchBackKey(false);
    }

    @Override
    public void pause ()
    {
        state= Constants.Game_State.PAUSED;
    }

    @Override
    public void resume ()
    {
        Assets.instance.init(new AssetManager());
        state= Constants.Game_State.ACTIVE;
    }

    public Archer get_Archer()
    {
        return player;
    }

    public boolean is_paused()
    {
        return state.equals(Constants.Game_State.PAUSED);
    }

    public boolean is_won()
    {
        return state.equals(Constants.Game_State.GAME_WINNER);
    }

    public boolean is_game_over()
    {
        return state.equals(Constants.Game_State.GAME_OVER);
    }


    public int get_level()
    {
        return level;
    }

    public void add_level()
    {
        level++;
    }

    public Constants.Game_State get_game_state()
    {
        return state;
    }

    public void  set_game_state(Constants.Game_State s)
    {
        state=s;
    }

    public int get_score()
    {
        return score;
    }

    public void add_to_score(int add)
    {
        score+=add;
    }

    public void set_score(int s)
    {
        score=s;
    }

    public void new_game()
    {
        score=0;
        level=1;

        //is used to determine if a new game has been requested after played games
        if(!state.equals(Constants.Game_State.LOADING))
        {
            state= Constants.Game_State.LOADING;
            player.init_level(level);
            worldController.load_level();
            worldRenderer.clear_text_to_display();
        }

        state= Constants.Game_State.ACTIVE;
    }

}
