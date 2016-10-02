package com.el.balloonArcher;

/**
 * Created by Louki on 20/9/2016.
 */
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.StringBuilder;
import com.el.balloonArcher.util.Assets;
import com.el.balloonArcher.util.Constants;

public class WorldRenderer implements Disposable
{
    private SpriteBatch batch;
    //private Texture archer_img,items_img;
   // private TextureRegion[] archer_texture,balloon_texture;
   // private TextureRegion arrow_texture;
    private WorldController worldController;
    private float current_animation_time;
    private int current_animation;
    private StringBuilder text;
    private StringBuilder info_text;
    private BitmapFont bitmap_font;
    private Color info_color;

    public WorldRenderer (WorldController worldController)
    {
        this.worldController=worldController;
    }

    private void init ()
    {
        text = new StringBuilder();
        info_text = new StringBuilder();
        bitmap_font = new BitmapFont();
        batch = new SpriteBatch();
        info_color= new Color(Color.BLACK);
/*
        archer_img = new Texture("images/archer.jpg");
        archer_texture = new TextureRegion[Constants.ANIMATION_SPLITS];
        current_animation_time=0;
        current_animation=0;
        archer_texture[0] = new TextureRegion(archer_img, 0, 0, 162, 255);
        archer_texture[1] = new TextureRegion(archer_img, 186, 0, 162, 255);
        archer_texture[2] = new TextureRegion(archer_img, 0, 256, 162, 255);
        archer_texture[3] = new TextureRegion(archer_img, 186, 256, 162, 255);

        items_img = new Texture("images/items.bmp");
        balloon_texture = new TextureRegion[2];

        balloon_texture[0] = new TextureRegion(items_img, 65, 1, 25, 27);
        balloon_texture[1] = new TextureRegion(items_img, 92, 1, 25, 27);

        arrow_texture= new TextureRegion(items_img, 4, 7, 54, 13);
*/

    }

    public void render(float deltaTime)
    {
        if(batch== null)
        {
            init();
        }
        batch.begin();
        print_text();
        paint_archer(deltaTime);
        paint_arrows();
        paint_balloons();
        batch.end();
    }

    private void paint_archer(float deltaTime)
    {
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

        //batch.draw(archer_texture[current_animation], 0, worldController.get_Archer_pos(),Constants.ARCHER_WIDTH,Constants.ARCHER_HEIGHT);
        batch.draw(Assets.instance.asset_archer.archer_texture[current_animation], 0, worldController.get_Archer_pos(),Constants.ARCHER_WIDTH,Constants.ARCHER_HEIGHT);
    }

    private void paint_arrows()
    {

        for (Arrow arrow: worldController.get_arrows())
        {
            if (arrow.is_shot() && !arrow.to_remove())
            {
                batch.draw(Assets.instance.asset_arrow.arrow_texture,arrow.get_x(),arrow.get_y(),Constants.ARROW_WIDTH,Constants.ARROW_HEIGHT);
            }
        }

    }

    private void paint_balloons()
    {

        for (Balloon b: worldController.get_baloons())
        {
            if (!b.is_hit())
            {
                if(b.get_has_gift())
                {
                    batch.draw(Assets.instance.asset_bonus_balloon.balloon_texture, b.get_x(),b.get_y(),Constants.BALLOON_WIDTH,Constants.BALLOON_HEIGHT);
                }
                else
                {
                    batch.draw(Assets.instance.asset_normal_balloon.balloon_texture, b.get_x(),b.get_y(),Constants.BALLOON_WIDTH,Constants.BALLOON_HEIGHT);
                }
            }
        }

    }

    public void resize (int width, int height) { }

    private void print_text()
    {
        text.delete(0,text.length());
        text.insert(0,"Level:"+worldController.get_level()+" Score: "+worldController.get_score());
        bitmap_font.setColor(0, 0, 0, 1.0f);
        bitmap_font.draw(batch, text, Constants.SCORE_TEXT_X, Constants.SCORE_TEXT_Y);


        text.delete(0,text.length());
        text.insert(0,"Arrows Left: "+worldController.get_no_of_left_arrows());
        bitmap_font.draw(batch, text, Constants.ARROW_TEXT_X, Constants.ARROW_TEXT_Y);

        if(info_text.length() >0)
        {
            bitmap_font.setColor(info_color);
            bitmap_font.draw(batch, info_text, Constants.INFO_TEXT_X, Constants.INFO_TEXT_Y);
        }

    }

    public void set_text_to_display(StringBuilder s)
    {
        info_color=Color.BLACK;
        info_text.delete(0,info_text.length());
        info_text.insert(0,s);
    }

    public void set_text_to_display(StringBuilder s,Color c)
    {
        info_color=c;
        info_text.delete(0,info_text.length());
        info_text.insert(0,s);
    }

    public void clear_text_to_display()
    {
        info_text.delete(0,info_text.length());
    }

    @Override
    public void dispose()
    {
        batch.dispose();
        //archer_img.dispose();
        //items_img.dispose();
        bitmap_font.dispose();
    }
}
