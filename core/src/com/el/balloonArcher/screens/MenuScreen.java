package com.el.balloonArcher.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.el.balloonArcher.screens.transitions.ScreenTransition;
import com.el.balloonArcher.screens.transitions.ScreenTransitionFade;
import com.el.balloonArcher.util.Assets;
import com.el.balloonArcher.util.CharacterSkin;
import com.el.balloonArcher.util.Constants;
import com.el.balloonArcher.util.GamePreferences;

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
    private Skin skinLibgdx;
    private CheckBox chkUseMonoChromeShader;

    // debug
    private final float DEBUG_REBUILD_INTERVAL = 5.0f;
    private boolean debugEnabled = false;
    private float debugRebuildStage;

    public MenuScreen(DirectedGame game)
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
        rebuildStage();
    }

    @Override public void hide ()
    {
        stage.dispose();
        skinBalloonArcher.dispose();
        skinLibgdx.dispose();
    }

    @Override public void pause () { }

    @Override
    public InputProcessor getInputProcessor ()
    {
        return stage;
    }

    private void rebuildStage ()
    {

      /*  skinBalloonArcher = new Skin(
                Gdx.files.internal(Constants.SKIN_BALLOONARCHER_UI),
                new TextureAtlas(Constants.TEXTURE_ATLAS_UI));*/

        skinBalloonArcher = new Skin();

        Texture menu_texture=new Texture("images/main_screen_background.png");
        menu_texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        skinBalloonArcher.add("logo",new TextureRegion(menu_texture,518,0,200,94));
        skinBalloonArcher.add("balloons",new TextureRegion(menu_texture,376,142,270,122));
        skinBalloonArcher.add("archer",new TextureRegion(menu_texture,0,0,296,406));
        skinBalloonArcher.add("info",new TextureRegion(menu_texture,788,966,236,57));
        skinBalloonArcher.add("background",new TextureRegion(menu_texture,500,0,1,1));
        skinBalloonArcher.add("play",new TextureRegion(menu_texture,926,786,98,57));
        skinBalloonArcher.add("options",new TextureRegion(menu_texture,926,904,98,57));

        skinLibgdx = new Skin(Gdx.files.internal(Constants.SKIN_LIBGDX_UI),
                        new TextureAtlas(Constants.TEXTURE_ATLAS_LIBGDX_UI));

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
        imgBackground = new Image(skinBalloonArcher.getRegion("background"));
        layer.left().bottom();
        imgBackground.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        imgBackground.scaleBy(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        layer.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        layer.add(imgBackground );
        return layer;
    }

    private Table buildObjectsLayer ()
    {
        Table layer = new Table();
        imgBalloons = new Image(skinBalloonArcher.getRegion("balloons"));
        layer.addActor(imgBalloons);
        imgBalloons.scaleBy(-0.2f);
        imgBalloons.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/8 * 5);
        //imgBalloons.setPosition(135, 80);


        imgArcher = new Image(skinBalloonArcher.getRegion("archer"));
        layer.addActor(imgArcher);
        //imgArcher.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/3);
        imgArcher.scaleBy(-0.3f);
        imgArcher.setPosition(0,Gdx.graphics.getHeight()/2);
        //imgArcher.setPosition(355, 40);
        return layer;
    }

    private Table buildLogosLayer ()
    {
        Table layer = new Table();
        layer.left().top();
        imgLogo = new Image(skinBalloonArcher.getRegion("logo"));
        layer.add(imgLogo);
        //imgLogo.setSize(Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/10);
       // imgLogo.setPosition(0,Gdx.graphics.getHeight()/20 *18);
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
        layer.right().bottom();
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

        layer.add(btnMenuOptions).bottom();
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
        winOptions = new Window("Options", skinLibgdx);
// + Audio Settings: Sound/Music CheckBox and Volume Slider
        winOptions.add(buildOptWinAudioSettings()).row();
// + Character Skin: Selection Box (White, Gray, Brown)
        winOptions.add(buildOptWinSkinSelection()).row();
// + Debug: Show FPS Counter
        winOptions.add(buildOptWinDebug()).row();
// + Separator and Buttons (Save, Cancel)
        winOptions.add(buildOptWinButtons()).pad(10, 0, 10, 0);
// Make options window slightly transparent
        winOptions.setColor(1, 1, 1, 0.8f);
// Hide options window by default
        winOptions.setVisible(false);
        if (debugEnabled) winOptions.debug();
// Let TableLayout recalculate widget sizes and positions
        winOptions.pack();
// Move options window to bottom right corner
        winOptions.setPosition(Constants.VIEWPORT_GUI_WIDTH - winOptions.getWidth() - 50, 50);
        return winOptions;
    }

    private void onPlayClicked ()
    {
        ScreenTransition transition = ScreenTransitionFade.init(0.75f);
        game.set_screen(new GameScreen(game),transition);
    }

    private void onOptionsClicked ()
    {
        loadSettings();
        btnMenuPlay.setVisible(false);
        btnMenuOptions.setVisible(false);
        winOptions.setVisible(true);
    }

    private void loadSettings()
    {
        GamePreferences prefs = GamePreferences.instance;
        prefs.load();
        chkSound.setChecked(prefs.sound);
        sldSound.setValue(prefs.volSound);
        //chkMusic.setChecked(prefs.music);
        //sldMusic.setValue(prefs.volMusic);
        selCharSkin.setSelectedIndex(prefs.charSkin);
        onCharSkinSelected(prefs.charSkin);
        chkShowFpsCounter.setChecked(prefs.showFpsCounter);
        chkUseMonoChromeShader.setChecked(prefs.useMonochromeShader);
    }

    private void saveSettings()
    {
        GamePreferences prefs = GamePreferences.instance;
        prefs.sound = chkSound.isChecked();
        prefs.volSound = sldSound.getValue();
        //prefs.music = chkMusic.isChecked();
        //prefs.volMusic = sldMusic.getValue();
        prefs.charSkin = selCharSkin.getSelectedIndex();
        prefs.showFpsCounter = chkShowFpsCounter.isChecked();
        prefs.useMonochromeShader = chkUseMonoChromeShader.isChecked();
        prefs.save();
    }

    private void onCharSkinSelected(int index)
    {
        CharacterSkin skin = CharacterSkin.values()[index];
        imgCharSkin.setColor(skin.getColor());
    }

    private void onSaveClicked()
    {
        saveSettings();
        onCancelClicked();
    }

    private void onCancelClicked()
    {
        btnMenuPlay.setVisible(true);
        btnMenuOptions.setVisible(true);
        winOptions.setVisible(false);
    }

    private Table buildOptWinAudioSettings ()
    {
        Table tbl = new Table();
// + Title: "Audio"
        tbl.pad(10, 10, 0, 10);
        tbl.add(new Label("Audio", skinLibgdx, "default-font", Color.ORANGE)).colspan(3);
        tbl.row();
        tbl.columnDefaults(0).padRight(10);
        tbl.columnDefaults(1).padRight(10);
// + Checkbox, "Sound" label, sound volume slider
        chkSound = new CheckBox("", skinLibgdx);
        tbl.add(chkSound);
        tbl.add(new Label("Sound", skinLibgdx));
        sldSound = new Slider(0.0f, 1.0f, 0.1f, false, skinLibgdx);
        tbl.add(sldSound);
        tbl.row();
// + Checkbox, "Music" label, music volume slider
        chkMusic = new CheckBox("", skinLibgdx);
        tbl.add(chkMusic);
        tbl.add(new Label("Music", skinLibgdx));
        sldMusic = new Slider(0.0f, 1.0f, 0.1f, false, skinLibgdx);
        tbl.add(sldMusic);
        tbl.row();
        return tbl;
    }

    private Table buildOptWinSkinSelection ()
    {
        Table tbl = new Table();
// + Title: "Character Skin"
        tbl.pad(10, 10, 0, 10);
        tbl.add(new Label("Character Skin", skinLibgdx, "default-font", Color.ORANGE)).colspan(2);
        tbl.row();
// + Drop down box filled with skin items
        selCharSkin = new SelectBox<CharacterSkin>(skinLibgdx);
        selCharSkin.setItems(CharacterSkin.values());
        selCharSkin.addListener(new ChangeListener()
        {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                onCharSkinSelected(((SelectBox<CharacterSkin>) actor).getSelectedIndex());
            }
        });
        tbl.add(selCharSkin).width(120).padRight(20);
// + Skin preview image
        imgCharSkin = new Image(Assets.instance.asset_archer.archer_texture[0]);
        tbl.add(imgCharSkin).width(50).height(50);
        return tbl;
    }

    private Table buildOptWinDebug ()
    {
        Table tbl = new Table();
        // + Title: "Debug"
        tbl.pad(10, 10, 0, 10);
        tbl.add(new Label("Debug", skinLibgdx, "default-font", Color.RED)).colspan(3);
        tbl.row();
        tbl.columnDefaults(0).padRight(10);
        tbl.columnDefaults(1).padRight(10);

        // + Checkbox, "Show FPS Counter" label
        chkShowFpsCounter = new CheckBox("", skinLibgdx);
        tbl.add(new Label("Show FPS Counter", skinLibgdx));
        tbl.add(chkShowFpsCounter);
        tbl.row();

        // + Checkbox, "Use Monochrome Shader" label
        chkUseMonoChromeShader = new CheckBox("", skinLibgdx);
        tbl.add(new Label("Use Monochrome Shader", skinLibgdx));
        tbl.add(chkUseMonoChromeShader);
        tbl.row();
        return tbl;
    }

    private Table buildOptWinButtons ()
    {
        Table tbl = new Table();
// + Separator
        Label lbl = null;
        lbl = new Label("", skinLibgdx);
        lbl.setColor(0.75f, 0.75f, 0.75f, 1);
        lbl.setStyle(new LabelStyle(lbl.getStyle()));
        lbl.getStyle().background = skinLibgdx.newDrawable("white");
        tbl.add(lbl).colspan(2).height(1).width(220).pad(0, 0, 0, 1);
        tbl.row();
        lbl = new Label("", skinLibgdx);
        lbl.setColor(0.5f, 0.5f, 0.5f, 1);
        lbl.setStyle(new LabelStyle(lbl.getStyle()));
        lbl.getStyle().background = skinLibgdx.newDrawable("white");
        tbl.add(lbl).colspan(2).height(1).width(220).pad(0, 1, 5, 0);
        tbl.row();
// + Save Button with event handler
        btnWinOptSave = new TextButton("Save", skinLibgdx);
        tbl.add(btnWinOptSave).padRight(30);
        btnWinOptSave.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                onSaveClicked();
            }
        });
// + Cancel Button with event handler
        btnWinOptCancel = new TextButton("Cancel", skinLibgdx);
        tbl.add(btnWinOptCancel);
        btnWinOptCancel.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                onCancelClicked();
            }
        });


        return tbl;
    }

}
