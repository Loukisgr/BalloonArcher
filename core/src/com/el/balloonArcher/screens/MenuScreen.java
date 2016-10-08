package com.el.balloonArcher.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
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
   // private SelectBox<CharacterSkin> selCharSkin;
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

  /*      if(Gdx.input.isTouched())
        {
            game.setScreen(new GameScreen(game));
        }
*/
        if (debugEnabled)
        {
            debugRebuildStage -= deltaTime;
            if (debugRebuildStage <= 0)
            {
                debugRebuildStage = DEBUG_REBUILD_INTERVAL;
                rebuildStage();
            }
        }

        stage.act(deltaTime);
        stage.draw();
        //Table.drawDebug(stage);
    }

    @Override public void resize (int width, int height)
    {
        stage.getViewport().update(width, height, true);
    }

    @Override public void show ()
    {
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
        rebuildStage();
    }

    @Override public void hide ()
    {
        stage.dispose();
        skinBalloonArcher.dispose();
    }

    @Override public void pause () { }

    private void rebuildStage ()
    {

      /*  skinBalloonArcher = new Skin(
                Gdx.files.internal(Constants.SKIN_BALLOONARCHER_UI),
                new TextureAtlas(Constants.TEXTURE_ATLAS_UI));*/

        skinBalloonArcher = new Skin();
        skinBalloonArcher.add("logo",new TextureRegion(new Texture("images/main_screen_background.png"),258,0,100,47));
        skinBalloonArcher.add("balloons",new TextureRegion(new Texture("images/main_screen_background.png"),188,71,135,61));
        skinBalloonArcher.add("archer",new TextureRegion(new Texture("images/main_screen_background.png"),0,0,148,203));
        skinBalloonArcher.add("info",new TextureRegion(new Texture("images/main_screen_background.png"),394,483,118,29));
        skinBalloonArcher.add("background",new TextureRegion(new Texture("images/main_screen_background.png"),500,0,12,12));
        skinBalloonArcher.add("play",new TextureRegion(new Texture("images/main_screen_background.png"),463,393,49,28));
        skinBalloonArcher.add("options",new TextureRegion(new Texture("images/main_screen_background.png"),463,500,49,28));

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
        this.imgBackground = new Image(skinBalloonArcher.getRegion("background"));
        return layer;
    }

    private Table buildObjectsLayer ()
    {
        Table layer = new Table();
        imgBalloons = new Image(skinBalloonArcher.getRegion("balloons"));
        layer.addActor(imgBalloons);
        imgBalloons.setSize(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/10);
        imgBalloons.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/8 * 5);
        //imgBalloons.setPosition(135, 80);


        imgArcher = new Image(skinBalloonArcher.getRegion("archer"));
        layer.addActor(imgArcher);
        imgArcher.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/3);
        imgArcher.setPosition(0,Gdx.graphics.getHeight()/2);
        //imgArcher.setPosition(355, 40);
        return layer;
    }

    private Table buildLogosLayer ()
    {
        Table layer = new Table();
        imgLogo = new Image(skinBalloonArcher.getRegion("logo"));
        layer.addActor(imgLogo);
        imgLogo.setSize(Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/10);
        imgLogo.setPosition(0,Gdx.graphics.getHeight()/20 *18);
        layer.row().expandY();

        imgInfo = new Image(skinBalloonArcher.getRegion("info"));
        layer.add(imgInfo).bottom();

        if (debugEnabled)
        {
            layer.debug();
        }

        return layer;
    }

    private Table buildControlsLayer ()
    {
        Table layer = new Table();
        // + Play Button
        Image imgMenuPlay = new Image(skinBalloonArcher.getRegion("play"));
        btnMenuPlay = new Button(imgMenuPlay.getDrawable());

        layer.add(btnMenuPlay);

        btnMenuPlay.addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                onPlayClicked();
            }
        });

        layer.row();

// + Options Button
        Image imgMenuoptions = new Image(skinBalloonArcher.getRegion("options"));
        btnMenuOptions = new Button(imgMenuoptions.getDrawable());

        layer.add(btnMenuOptions);
        btnMenuOptions.addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {
                onOptionsClicked();
            }
        });

        if (debugEnabled) layer.debug();

        return layer;
    }

    private Table buildOptionsWindowLayer ()
    {
        Table layer = new Table();
        layer.right().bottom();
        return layer;
    }

    private void onPlayClicked ()
    {
        game.setScreen(new GameScreen(game));
    }

    private void onOptionsClicked () { }
}
