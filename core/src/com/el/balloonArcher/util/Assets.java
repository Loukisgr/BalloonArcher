package com.el.balloonArcher.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
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
    public Asset_Arrow asset_arrow;
    public Asset_Normal_Balloon asset_normal_balloon;
    public Asset_Bonus_Balloon asset_bonus_balloon;
    public Asset_Sounds sounds;


    private Assets(){};

    public void init (AssetManager assetManager)
    {
        this.assetManager = assetManager;
// set asset manager error handler
        assetManager.setErrorListener(this);
        // load texture
        assetManager.load("images/archer.jpg", Texture.class);
        assetManager.load("images/items.bmp", Texture.class);
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
        asset_archer = new Asset_Archer(textures.get(1));
        asset_arrow = new Asset_Arrow(textures.get(0));
        asset_normal_balloon = new Asset_Normal_Balloon(textures.get(0));
        asset_bonus_balloon = new Asset_Bonus_Balloon(textures.get(0));
        sounds = new Asset_Sounds(assetManager);
    }

    public class Asset_Archer
    {
        public final Texture archer_img;
        public final TextureRegion[] archer_texture;

        public Asset_Archer(Texture  t)
        {
            archer_img = t;
            archer_texture = new TextureRegion[Constants.ANIMATION_SPLITS];
            archer_texture[0] = new TextureRegion(t, 0, 0, 162, 255);
            archer_texture[1] = new TextureRegion(t, 186, 0, 162, 255);
            archer_texture[2] = new TextureRegion(t, 0, 256, 162, 255);
            archer_texture[3] = new TextureRegion(t, 186, 256, 162, 255);
        }
    }

    public class Asset_Normal_Balloon
    {
        public final Texture normal_balloon_img;
        public final TextureRegion balloon_texture;

        public Asset_Normal_Balloon(Texture  t)
        {
            normal_balloon_img=t;
            balloon_texture = new TextureRegion(normal_balloon_img, 65, 1, 25, 27);
        }
    }

    public class Asset_Bonus_Balloon
    {
        public final Texture bonus_balloon_img;
        public final TextureRegion balloon_texture;

        public Asset_Bonus_Balloon(Texture  t)
        {
            bonus_balloon_img=t;
            balloon_texture = new TextureRegion(bonus_balloon_img, 92, 1, 25, 27);
        }
    }

    public class Asset_Arrow
    {
        public final Texture arrow_img;
        public final TextureRegion arrow_texture;

        public Asset_Arrow(Texture  t)
        {
            arrow_img=t;
            arrow_texture = new TextureRegion(arrow_img, 4, 7, 54, 13);
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


    @Override
    public void dispose()
    {
        assetManager.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable)
    {
        Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", (Exception)throwable);
    }

    public void error (String filename, Class type, Throwable throwable)
    {
        Gdx.app.error(TAG, "Couldn't load asset '" + filename + "'", (Exception)throwable);
    }
}
