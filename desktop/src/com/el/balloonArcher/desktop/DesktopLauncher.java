package com.el.balloonArcher.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.el.balloonArcher.BalloonArcher;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width= (int) com.el.balloonArcher.util.Constants.VIEWPORT_GUI_WIDTH;
		config.height=(int) com.el.balloonArcher.util.Constants.VIEWPORT_GUI_HEIGHT;
		new LwjglApplication(new BalloonArcher(), config);
	}
}
