package com.el.balloonArcher;

/**
 * Created by Louki on 20/9/2016.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StringBuilder;
import com.el.balloonArcher.screens.GameScreen;
import com.el.balloonArcher.util.Assets;
import com.el.balloonArcher.util.CharacterSkin;
import com.el.balloonArcher.util.Constants;
import com.el.balloonArcher.util.GamePreferences;

public class WorldRenderer implements Disposable
{
    private SpriteBatch batch;
    private WorldController worldController;
    private StringBuilder text;
    private StringBuilder info_text;
    private Color info_color;
    private Color batchColor;
    private ShaderProgram shader_monochrome;

    public WorldRenderer (WorldController worldController)
    {
        this.worldController=worldController;
    }

    private void init ()
    {
        text = new StringBuilder();
        info_text = new StringBuilder();
        batch = new SpriteBatch();
        info_color= new Color(Color.BLACK);
        batchColor = batch.getColor();

        shader_monochrome= new ShaderProgram(Gdx.files.internal(Constants.shaderMonochromeVertex),
                Gdx.files.internal(Constants.shaderMonochromeFragment));

        if (!shader_monochrome.isCompiled())
        {
            String msg = "Could not compile shader program: "
                    + shader_monochrome.getLog();
            throw new GdxRuntimeException(msg);
        }
    }

    public void render(float deltaTime)
    {
        if(batch== null)
        {
            init();
        }
        batch.begin();

        if (GamePreferences.instance.useMonochromeShader)
        {
            batch.setShader(shader_monochrome);
            shader_monochrome.setUniformf("u_amount", 1.0f);
        }

        paint_game_background();
        print_text();
        paint_archer();
        paint_arrows();
        paint_balloons();
        if (GamePreferences.instance.showFpsCounter) {paint_gui_fps_counter();}
        batch.setShader(null);
        batch.end();
    }


    private void paint_archer()
    {


        //batch.draw(archer_texture[current_animation], 0, worldController.get_Archer_pos(),Constants.ARCHER_WIDTH,Constants.ARCHER_HEIGHT);
        //Color c = batch.getColor();
        batch.setColor(CharacterSkin.values()[GamePreferences.instance.charSkin].getColor());
        batch.draw(Assets.instance.asset_archer.archer_texture[worldController.get_archer_frame()], 0, worldController.get_Archer_pos(),Constants.ARCHER_WIDTH,Constants.ARCHER_HEIGHT);
        batch.setColor(batchColor);
    }

    private void paint_arrows()
    {

        for (Arrow arrow: worldController.get_arrows())
        {
            if (arrow.is_shot() && !arrow.to_remove())
            {
                arrow.get_down_dust_particle().draw(batch);
                arrow.get_up_dust_particle().draw(batch);
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

    private void paint_game_background()
    {
        batch.draw(Assets.instance.asset_morning_background.background_texture,0,0, GameScreen.GUI_WIDTH,GameScreen.GUI_HEIGHT);
    }

    private void paint_gui_fps_counter()
    {
        int fps = Gdx.graphics.getFramesPerSecond();

        if(fps<30)
        {
            Assets.instance.fonts.defaultNormal.setColor(180, 0, 0, 1.0f);
        }
        else if (fps < 45)
        {
            Assets.instance.fonts.defaultNormal.setColor(255, 255, 0, 1.0f);
        }
        else
        {
            Assets.instance.fonts.defaultNormal.setColor(0, 200, 0, 1.0f);
        }

        Assets.instance.fonts.defaultNormal.draw(batch,"FPS: "+fps,Constants.FPS_TEXT_X,Constants.FPS_TEXT_Y);
    }

    public void resize (int width, int height) { }

    private void print_text()
    {
        //level
        text.delete(0,text.length());
        text.insert(0,"Level:"+worldController.get_level());
        Assets.instance.fonts.defaultNormal.setColor(180, 0, 0, 1.0f);
        Assets.instance.fonts.defaultNormal.draw(batch, text, Constants.LEVEL_TEXT_X, Constants.LEVEL_TEXT_Y);

        text.delete(0,text.length());
        text.insert(0,worldController.get_no_of_left_arrows());

        //score
        text.delete(0,text.length());
        text.insert(0,"Score: "+worldController.get_score());
        Assets.instance.fonts.defaultNormal.setColor(0, 0, 0, 1.0f);
        Assets.instance.fonts.defaultNormal.draw(batch, text, Constants.SCORE_TEXT_X, Constants.SCORE_TEXT_Y);

        text.delete(0,text.length());
        text.insert(0,worldController.get_no_of_left_arrows());

        //arrow
        batch.draw(Assets.instance.asset_arrow.arrow_texture,Constants.ARROW_TEXT_X,Constants.ARROW_TEXT_Y-(Constants.ARROW_HEIGHT*1.5f),Constants.ARROW_WIDTH*2,Constants.ARROW_HEIGHT*1.5f);
        Assets.instance.fonts.defaultNormal.draw(batch, text, Constants.ARROW_TEXT_X*1.6f, Constants.ARROW_TEXT_Y);

        if(info_text.length() >0)
        {
            Assets.instance.fonts.defaultBig.setColor(info_color);
            Assets.instance.fonts.defaultBig.draw(batch, info_text, Constants.INFO_TEXT_X, Constants.INFO_TEXT_Y,(int)(GameScreen.GUI_WIDTH/2),1,true);
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
        shader_monochrome.dispose();
    }
}
