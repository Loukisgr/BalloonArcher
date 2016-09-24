package com.el.baloonarcher.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.el.baloonarcher.BaloonArcher;
import com.el.baloonarcher.util.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width= (int)Constants.VIEWPORT_GUI_WIDTH;
		config.height=(int)Constants.VIEWPORT_GUI_HEIGHT;
		new LwjglApplication(new BaloonArcher(), config);
	}
}
