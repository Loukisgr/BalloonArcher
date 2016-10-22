package com.el.balloonArcher;


import com.badlogic.gdx.assets.AssetManager;
import com.el.balloonArcher.screens.DirectedGame;
import com.el.balloonArcher.screens.LogoScreen;
import com.el.balloonArcher.util.Assets;

public class BalloonArcher extends DirectedGame
{
	private static final String TAG = BalloonArcher.class.getName();

	@Override
	public void create ()
	{
        Assets.instance.init(new AssetManager());
        // Start game at menu screen
        set_screen(new LogoScreen(this));
	}
}
