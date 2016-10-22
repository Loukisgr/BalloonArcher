package com.el.balloonArcher.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.el.balloonArcher.screens.transitions.ScreenTransition;
import com.el.balloonArcher.screens.transitions.ScreenTransitionFade;
import com.el.balloonArcher.util.Constants;

/**
 * Created by liaras on 20/10/2016.
 */

public class LogoScreen extends AbstractGameScreen
{

    private Stage stage;
    private Skin skinLogo;
    private Image imgLogo;
    private float waiting_time=2;
    private ScreenTransition transition;

    public LogoScreen(DirectedGame game)
    {
        super(game);
    }

    @Override public void show ()
    {
        stage = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH,Constants.VIEWPORT_GUI_HEIGHT));
        rebuildStage();
    }

    @Override public void hide ()
    {
        stage.dispose();
        skinLogo.dispose();
    }

    @Override
    public InputProcessor getInputProcessor()
    {
        return null;
    }

    private void rebuildStage ()
    {
        skinLogo = new Skin();

        Texture menu_texture=new Texture("images/main_screen_background.png");
        menu_texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        skinLogo.add("logo",new TextureRegion(menu_texture,518,0,200,94));

        // build all layers
        Table layerLogos = buildLogosLayer();

        // assemble stage for menu screen
        stage.clear();
        Stack stack = new Stack();
        stage.addActor(stack);
        stack.setSize(Constants.VIEWPORT_GUI_WIDTH,Constants.VIEWPORT_GUI_HEIGHT);
        stack.add(layerLogos);
    }

    private Table buildLogosLayer ()
    {
        Table layer = new Table();
        layer.center().center();
        imgLogo = new Image(skinLogo.getRegion("logo"));
        layer.add(imgLogo);
        layer.row().expandY();

        return layer;
    }

    @Override
    public void render (float deltaTime)
    {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(deltaTime);
        stage.draw();

        waiting_time-=deltaTime;

        if((waiting_time < 0) && (transition== null))
        {
            transition = ScreenTransitionFade.init(0.75f);
            game.set_screen(new MenuScreen(game), transition);
        }
        //Table.drawDebug(stage);
    }

}

