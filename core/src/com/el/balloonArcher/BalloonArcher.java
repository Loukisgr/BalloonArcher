package com.el.balloonArcher;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class BalloonArcher extends ApplicationAdapter {
	private com.el.balloonArcher.Archer player;
	private static final String TAG = BalloonArcher.class.getName();
	private WorldController worldController;
	private com.el.balloonArcher.WorldRenderer worldRenderer;
	private boolean paused;
	private int level=1;
	private int score=0;
	
	@Override
	public void create ()
	{
		// Set Libgdx log level to DEBU
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		// Initialize controller and renderer
		player = new com.el.balloonArcher.Archer(level);
		worldController = new WorldController(this);
		worldRenderer = new com.el.balloonArcher.WorldRenderer(worldController);
		paused = false;
	}

	@Override
	public void render()
	{
		// Update game world by the time that has passed since last rendered frame.

		if(!paused)
		{
			worldController.update(Gdx.graphics.getDeltaTime());
			Gdx.gl.glClearColor(0, 0, 0, 1);
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
		paused = true;
	}

	@Override
	public void resume ()
	{
		paused = false;
	}

	public Archer get_Archer()
	{
		return player;
	}

	public boolean is_paused()
	{
		return paused;
	}

	public int get_level()
	{
		return level;
	}

}
