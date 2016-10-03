package com.el.balloonArcher;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.StringBuilder;
import com.el.balloonArcher.util.Assets;
import com.el.balloonArcher.util.Constants;

public class BalloonArcher extends ApplicationAdapter
{
	private com.el.balloonArcher.Archer player;
	private static final String TAG = BalloonArcher.class.getName();
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	//private boolean paused;
	private Constants.Game_State state;
	private int level=1;
	private int score=0;
	public static int GUI_WIDTH,GUI_HEIGHT;

	@Override
	public void create ()
	{
        Assets.instance.init(new AssetManager());
		GUI_WIDTH= Gdx.graphics.getWidth();
		GUI_HEIGHT= Gdx.graphics.getHeight();
		state= Constants.Game_State.LOADING;
		// Set Libgdx log level to DEBU
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		// Initialize controller and renderer
		player = new com.el.balloonArcher.Archer(level);
		worldController = new WorldController(this);
		worldRenderer = new com.el.balloonArcher.WorldRenderer(worldController);
		//paused = false;
		state= Constants.Game_State.ACTIVE;
	}

	@Override
	public void render()
	{
		// Update game world by the time that has passed since last rendered frame.

		if(!is_paused())
		{
			worldController.update(Gdx.graphics.getDeltaTime());
			Gdx.gl.glClearColor(1, 1, 1, 1);
			// Clears the screen
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			// Render game world to screen
			worldRenderer.render(Gdx.graphics.getDeltaTime());

			if(is_won())
			{
				//worldRenderer.set_text_to_display(new StringBuilder("WON!!!"));
                worldRenderer.set_text_to_display(new StringBuilder("Starting Next Level..."));
                this.add_level();
                player.init_level(get_level());
                worldController.load_level();
                this.state=Constants.Game_State.ACTIVE;
                worldRenderer.clear_text_to_display();
			}
			else if(is_game_over())
			{
				worldRenderer.set_text_to_display(new StringBuilder("GAME OVER! Tap for New Game"),Color.RED);
			}

		}
	}

	@Override
	public void resize (int width, int height)
	{
		worldRenderer.resize(width, height);
	}

	@Override
	public void dispose()
	{
		worldRenderer.dispose();
        Assets.instance.dispose();
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
		if (state.equals(Constants.Game_State.PAUSED))
		{
			return true;
		}
		return false;
	}

	public boolean is_won()
	{
		if (state.equals(Constants.Game_State.GAME_WINNER))
		{
			return true;
		}
		return false;
	}

	public boolean is_game_over()
	{
		if (state.equals(Constants.Game_State.GAME_OVER))
		{
			return true;
		}
		return false;
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
