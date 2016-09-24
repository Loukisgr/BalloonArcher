package com.el.balloonArcher;

/**
 * Created by Louki on 20/9/2016.
 */
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.el.balloonArcher.util.Constants;

public class WorldRenderer implements Disposable
{
    private SpriteBatch batch;
    private Texture img;
    private TextureRegion[] archer_texture;
    private WorldController worldController;
    private float current_animation_time;
    private int current_animation;
    public WorldRenderer (WorldController worldController) { }

    private void init ()
    {
        batch = new SpriteBatch();
        img = new Texture("archer.jpg");
        archer_texture = new TextureRegion[Constants.ANIMATION_SPLITS];
        current_animation_time=0;
        current_animation=0;
        archer_texture[0] = new TextureRegion(img, 0, 0, 162, 255);
        archer_texture[1] = new TextureRegion(img, 186, 0f, 162, 255);
        archer_texture[2] = new TextureRegion(img, 0, 256, 162, 255);
        archer_texture[3] = new TextureRegion(img, 186, 256, 162, 255);
    }

    public void render(float deltaTime)
    {
        if(batch== null)
        {
            init();
        }
        batch.begin();

        if(current_animation_time < Constants.ANIMATION_TIMER)
        {
            current_animation_time+= deltaTime;

           if((int)(current_animation_time /((Constants.ANIMATION_TIMER )/(Constants.ANIMATION_SPLITS))) != current_animation)
           {
               current_animation=current_animation+1;

               if (current_animation >=Constants.ANIMATION_SPLITS)
               {
                   current_animation=0;
               }
           }

        }
        else
        {
            current_animation_time=current_animation_time-Constants.ANIMATION_TIMER+deltaTime;
            current_animation=0;
        }

        batch.draw(archer_texture[current_animation], 0, 0);
        batch.end();
    }

    public void resize (int width, int height) { }

    @Override
    public void dispose()
    {
        batch.dispose();
        img.dispose();
    }
}
