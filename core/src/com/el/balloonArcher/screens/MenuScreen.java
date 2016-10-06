package com.el.balloonArcher.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.el.balloonArcher.util.Constants;

/**
 * Created by Louki on 3/10/2016.
 */

public class MenuScreen extends AbstractGameScreen
{

    private Stage stage;
    private Skin skinBalloonArcher;
    // menu
    private Image imgBackground;
    private Image imgLogo;
    private Image imgInfo;
    private Image imgBalloons;
    private Image imgArcher;
    private Button btnMenuPlay;
    private Button btnMenuOptions;
// options

    private Window winOptions;
    private TextButton btnWinOptSave;
    private TextButton btnWinOptCancel;
    private CheckBox chkSound;
    private Slider sldSound;
    private CheckBox chkMusic;
    private Slider sldMusic;
    private SelectBox<CharacterSkin> selCharSkin;
    private Image imgCharSkin;
    private CheckBox chkShowFpsCounter;
    // debug
    private final float DEBUG_REBUILD_INTERVAL = 5.0f;
    private boolean debugEnabled = false;
    private float debugRebuildStage;

    public MenuScreen(Game game)
    {
        super(game);
    }

    @Override
    public void render (float deltaTime)
    {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isTouched())
        {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override public void resize (int width, int height) { }
    @Override public void show () { }
    @Override public void hide () { }
    @Override public void pause () { }

    private void rebuildStage ()
    {

        skinBalloonArcher = new Skin(
                Gdx.files.internal(Constants.SKIN_BALLOONARCHER_UI),
                new TextureAtlas(Constants.TEXTURE_ATLAS_UI));
// build all layers
        Table layerBackground = buildBackgroundLayer();
        Table layerObjects = buildObjectsLayer();
        Table layerLogos = buildLogosLayer();
        Table layerControls = buildControlsLayer();
        Table layerOptionsWindow = buildOptionsWindowLayer();
// assemble stage for menu screen
        stage.clear();
        Stack stack = new Stack();
        stage.addActor(stack);
        stack.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stack.add(layerBackground);
        stack.add(layerObjects);
        stack.add(layerLogos);
        stack.add(layerControls);
        stage.addActor(layerOptionsWindow);
    }

    private Table buildBackgroundLayer ()
    {
        Table layer = new Table();
        return layer;
    }

    private Table buildObjectsLayer ()
    {
        Table layer = new Table();
        return layer;
    }

    private Table buildLogosLayer ()
    {
        Table layer = new Table();
        return layer;
    }

    private Table buildControlsLayer ()
    {
        Table layer = new Table();
        return layer;
    }

    private Table buildOptionsWindowLayer ()
    {
        Table layer = new Table();
        return layer;
    }
}
