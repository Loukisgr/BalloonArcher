package com.el.balloonArcher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Rectangle;
import com.el.balloonArcher.screens.GameScreen;
import com.el.balloonArcher.util.Constants;

/**
 * Created by Loukis on 18/9/2016.
 */
public class Arrow
{

    private float speed;
    private float x = 100;
    private float y = 0;
    private boolean shot;
    private boolean remove=false;
    private Rectangle body;
    private ParticleEffect dustParticles;

    public Arrow(float speed)
    {
        this.speed=speed+ Constants.ARROW_STARTING_SPEED;
        this.body = new Rectangle();
        dustParticles = new ParticleEffect();
        shot=false;
        dustParticles.load(Gdx.files.internal("particles/dust.pfx"), Gdx.files.internal("particles"));
    }

    public void shoot(float y)
    {
        this.shot=true;
        this.y=y;
    }

    public boolean is_shot()
    {
        return this.shot;
    }

    public boolean to_remove()
    {
        return remove;
    }

    public void move(float deltaTime)
    {
        if (is_shot())
        {
            if(x> GameScreen.GUI_WIDTH)
            {
                remove=true;
                dustParticles.allowCompletion();
            }
            else
            {
                x=x+speed*deltaTime;
                body.set(x,y,Constants.ARROW_WIDTH,Constants.ARROW_HEIGHT);
                dustParticles.setPosition(x-Constants.ARROW_WIDTH,y);
                dustParticles.update(deltaTime);
            }
        }
    }

    public float get_x()
    {
        return x;
    }

    public float get_y()
    {
        return y;
    }

    public Rectangle get_body()
    {
        return body;
    }

    public ParticleEffect get_DustParticles()
    {
        return dustParticles;
    }
}
