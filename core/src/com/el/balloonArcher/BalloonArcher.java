package com.el.balloonArcher;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.el.balloonArcher.screens.MenuScreen;
import com.el.balloonArcher.util.Assets;
import com.el.balloonArcher.util.Constants;

public class BalloonArcher extends Game
{
	private static final String TAG = BalloonArcher.class.getName();

	@Override
	public void create ()
	{
        Assets.instance.init(new AssetManager());
        // Start game at menu screen
        setScreen(new MenuScreen(this));
	}
}
