package com.el.balloonArcher;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.el.balloonArcher.util.Constants;

public class BalloonArcher extends ApplicationAdapter {
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
	}

	@Override
	public void pause ()
	{
		state= Constants.Game_State.PAUSED;
	}

	@Override
	public void resume ()
	{
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

	public int get_level()
	{
		return level;
	}

	public Constants.Game_State get_game_state()
	{
		return state;
	}

	public void  set_game_state(Constants.Game_State s)
	{
		state=s;
	}

}
