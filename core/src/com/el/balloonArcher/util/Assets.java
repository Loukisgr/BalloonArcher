package com.el.balloonArcher.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.audio.Sound;


/**
 * Created by Louki on 2/10/2016.
 */

public class Assets implements Disposable,AssetErrorListener
{

    public static final Assets instance = new Assets();
    private AssetManager assetManager;
    public static final String TAG = Assets.class.getName();
    public Asset_Archer asset_archer;
    public Asset_Game_Choice asset_game_choice;
    public Asset_Arrow asset_arrow;
    public Asset_Normal_Balloon asset_normal_balloon;
    public Asset_Bonus_Balloon asset_bonus_balloon;
    public Asset_Morning_Background asset_morning_background;
    public Asset_Sounds sounds;
    public Asset_Fonts fonts;


    private Assets(){}

    public void init (AssetManager assetManager)
    {
        this.assetManager = assetManager;
        // set asset manager error handler
        assetManager.setErrorListener(this);
        // load texture
        assetManager.load("images/game_choice.png", Texture.class);
        assetManager.load("images/archer.png", Texture.class);
        assetManager.load("images/items.png", Texture.class);
        assetManager.load("images/background.png", Texture.class);
        assetManager.load("sounds/Balloon_Popping.wav", Sound.class);
        // start loading assets and wait until finished
        assetManager.finishLoading();
        Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);

        for (String a : assetManager.getAssetNames())
        {
            Gdx.app.debug(TAG, "asset: " + a);
        }

        Array<Texture> textures = new Array<Texture>();
        assetManager.getAll(Texture.class, textures);

        for (Texture t : textures)
        {
            t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        }

        // create game resource objects
        fonts = new Asset_Fonts();
        asset_archer = new Asset_Archer(textures.get(3));
        asset_arrow = new Asset_Arrow(textures.get(1));
        asset_normal_balloon = new Asset_Normal_Balloon(textures.get(1));
        asset_bonus_balloon = new Asset_Bonus_Balloon(textures.get(1));
        asset_morning_background = new Asset_Morning_Background(textures.get(0));
        asset_game_choice = new Asset_Game_Choice(textures.get(2));
        sounds = new Asset_Sounds(assetManager);
    }

    public class Asset_Archer
    {
        public final Texture archer_img;
        public final TextureRegion[] archer_texture;

        public Asset_Archer(Texture  t)
        {
            archer_img = t;
            archer_texture = new TextureRegion[Constants.ARCHER_ANIMATION_SPLITS];
            archer_texture[0] = new TextureRegion(t, 702,   0, 332, 495);
            archer_texture[1] = new TextureRegion(t, 0,   654, 324, 492);
            archer_texture[2] = new TextureRegion(t, 348, 652, 323, 492);
            archer_texture[3] = new TextureRegion(t, 695, 653, 343, 492);
            archer_texture[4] = new TextureRegion(t, 6,   5,   344, 495);
            archer_texture[5] = new TextureRegion(t, 358, 0,   328, 495);
        }
    }

    public class Asset_Game_Choice
    {
        public final Texture asset_game_choice_img;
        public final TextureRegion[] game_choice_texture;
        public final TextureRegion[] game_locked_choice_texture;
        public final TextureRegion[] game_passed_choice_texture;

        public Asset_Game_Choice(Texture  t)
        {
            asset_game_choice_img = t;
            game_choice_texture = new TextureRegion[Constants.NO_OF_GAMES];
            game_choice_texture[0] = new TextureRegion(t, 0,   0, 132, 117);
            game_choice_texture[1] = new TextureRegion(t, 134, 1, 132, 117);
            game_choice_texture[2] = new TextureRegion(t, 270, 1, 132, 117);
            game_choice_texture[3] = new TextureRegion(t, 407, 1, 132, 117);
            game_choice_texture[4] = new TextureRegion(t, 543, 1, 132, 117);
            game_choice_texture[5] = new TextureRegion(t, 681, 1, 132, 117);

            game_locked_choice_texture = new TextureRegion[Constants.NO_OF_GAMES];
            game_locked_choice_texture[0] = new TextureRegion(t, 0,   0, 132, 117);
            game_locked_choice_texture[1] = new TextureRegion(t, 134, 130, 132, 117);
            game_locked_choice_texture[2] = new TextureRegion(t, 270, 130, 132, 117);
            game_locked_choice_texture[3] = new TextureRegion(t, 407, 130, 132, 117);
            game_locked_choice_texture[4] = new TextureRegion(t, 543, 130, 132, 117);
            game_locked_choice_texture[5] = new TextureRegion(t, 681, 130, 132, 117);

            game_passed_choice_texture = new TextureRegion[Constants.NO_OF_GAMES];
            game_passed_choice_texture[0] = new TextureRegion(t, 0,   256, 132, 117);
            game_passed_choice_texture[1] = new TextureRegion(t, 134, 256, 132, 117);
            game_passed_choice_texture[2] = new TextureRegion(t, 270, 256, 132, 117);
            game_passed_choice_texture[3] = new TextureRegion(t, 407, 256, 132, 117);
            game_passed_choice_texture[4] = new TextureRegion(t, 543, 256, 132, 117);
            game_passed_choice_texture[5] = new TextureRegion(t, 681, 1, 132, 117);
        }
    }

    public class Asset_Normal_Balloon
    {
        public final Texture normal_balloon_img;
        public final TextureRegion[] balloon_texture;

        public Asset_Normal_Balloon(Texture  t)
        {
            normal_balloon_img=t;
            balloon_texture = new TextureRegion[Constants.BALLOON_ANIMATION_SPLITS];
            balloon_texture[0] = new TextureRegion(normal_balloon_img, 100, 0, 37, 41);
            balloon_texture[1] = new TextureRegion(normal_balloon_img, 182, 2, 39, 32);
            balloon_texture[2] = new TextureRegion(normal_balloon_img, 220, 3, 36, 39);
            balloon_texture[3] = new TextureRegion(normal_balloon_img, 262, 2, 36, 42);
        }
    }

    public class Asset_Bonus_Balloon
    {
        public final Texture bonus_balloon_img;
        public final TextureRegion balloon_texture;

        public Asset_Bonus_Balloon(Texture  t)
        {
            bonus_balloon_img=t;
            balloon_texture = new TextureRegion(bonus_balloon_img, 139, 1, 37, 42);
        }
    }

    public class Asset_Arrow
    {
        public final Texture arrow_img;
        public final TextureRegion arrow_texture;

        public Asset_Arrow(Texture  t)
        {
            arrow_img=t;
            arrow_texture = new TextureRegion(arrow_img, 0, 5, 84, 34);
        }
    }

    public class Asset_Sounds
    {
        public final Sound pop;

        public Asset_Sounds(AssetManager am)
        {
            pop = am.get("sounds/Balloon_Popping.wav",Sound.class);
        }
    }

    public class Asset_Morning_Background
    {
        public final Texture background_img;
        public final TextureRegion background_texture;

        public Asset_Morning_Background(Texture  t)
        {
            background_img=t;
            background_texture = new TextureRegion(background_img, 0, 0, 672, 1120);
        }
    }


    public class Asset_Fonts
    {
        public final BitmapFont defaultSmall;
        public final BitmapFont defaultNormal;
        public final BitmapFont defaultBig;

        public Asset_Fonts ()
        {
            // create three fonts using Libgdx's 15px bitmap font
            defaultSmall = new BitmapFont(Gdx.files.internal("fonts/arial-15.fnt"), false);
            defaultNormal = new BitmapFont(Gdx.files.internal("fonts/arial-15.fnt"), false);
            defaultBig = new BitmapFont(Gdx.files.internal("fonts/arial-15.fnt"), false);

            // set font sizes
            defaultSmall.getData().setScale(0.75f);
            defaultNormal.getData().setScale(1.0f);
            defaultBig.getData().setScale(2.0f);

            // enable linear texture filtering for smooth fonts
            defaultSmall.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
            defaultNormal.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
            defaultBig.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        }
    }


    @Override
    public void dispose()
    {
        assetManager.dispose();
        fonts.defaultSmall.dispose();
        fonts.defaultNormal.dispose();
        fonts.defaultBig.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable)
    {
        Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", throwable);
    }

    public void error (String filename, Class type, Throwable throwable)
    {
        Gdx.app.error(TAG, "Couldn't load asset '" + filename + "'", throwable);
    }
}
