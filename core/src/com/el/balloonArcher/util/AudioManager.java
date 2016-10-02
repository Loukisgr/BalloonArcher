package com.el.balloonArcher.util;

import com.badlogic.gdx.audio.Sound;

/**
 * Created by Louki on 2/10/2016.
 */

public class AudioManager
{
    public static final AudioManager instance = new AudioManager();

    private AudioManager(){}

    public void play (Sound sound)
    {
        play(sound,1);
    }

    public void play (Sound sound, float volume)
    {
        play(sound, volume, 1);
    }
    public void play (Sound sound, float volume, float pitch)
    {
        play(sound, volume, pitch, 0);
    }
    public void play (Sound sound, float volume, float pitch, float pan)
    {
  //      if (!GamePreferences.instance.sound) return;
  //      sound.play(GamePreferences.instance.volSound * volume, pitch, pan);
        sound.play(volume, pitch, pan);

    }

}
