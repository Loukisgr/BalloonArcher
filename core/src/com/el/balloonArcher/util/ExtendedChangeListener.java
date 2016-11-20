package com.el.balloonArcher.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by Louki on 20/11/2016.
 */

public class ExtendedChangeListener extends ChangeListener
{

    private int numeric_parameter;

    public ExtendedChangeListener(int parameter)
    {
        super();
        numeric_parameter=parameter;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor)
    {

    }

    public int get_numeric_parameter()
    {
        return numeric_parameter;
    }

}
