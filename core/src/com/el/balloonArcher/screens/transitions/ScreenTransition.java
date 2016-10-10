package com.el.balloonArcher.screens.transitions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by liaras on 10/10/2016.
 */

public interface ScreenTransition
{
    public float get_duration();
    public void render (SpriteBatch batch, Texture currScreen, Texture nextScreen, float alpha);
}
