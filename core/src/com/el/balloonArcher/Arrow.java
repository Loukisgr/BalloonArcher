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
    private ParticleEffect down_dust_particle;
    private ParticleEffect up_dust_particle;

    public Arrow(float speed)
    {
        this.speed=speed+ Constants.ARROW_STARTING_SPEED;
        this.body = new Rectangle();
        down_dust_particle = new ParticleEffect();
        up_dust_particle = new ParticleEffect();
        shot=false;
        down_dust_particle.load(Gdx.files.internal("particles/dust.pfx"), Gdx.files.internal("particles"));
        up_dust_particle.load(Gdx.files.internal("particles/dust.pfx"), Gdx.files.internal("particles"));
    }

    public void shoot(float y)
    {
        this.shot=true;
        this.y=y;
        down_dust_particle.start();
        up_dust_particle.start();
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
                down_dust_particle.allowCompletion();
                up_dust_particle.allowCompletion();
            }
            else
            {
                x=x+speed*deltaTime;
                body.set(x+Constants.ARROW_WIDTH,y,Constants.ARROW_WIDTH/4,Constants.ARROW_HEIGHT);
                down_dust_particle.setPosition(x,y);
                down_dust_particle.update(deltaTime);
                up_dust_particle.setPosition(x,y+Constants.ARROW_HEIGHT);
                up_dust_particle.update(deltaTime);
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

    public ParticleEffect get_down_dust_particle()
    {
        return down_dust_particle;
    }

    public ParticleEffect get_up_dust_particle()
    {
        return up_dust_particle;
    }

}
