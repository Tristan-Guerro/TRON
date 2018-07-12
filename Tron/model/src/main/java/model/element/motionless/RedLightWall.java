package model.element.motionless;

import java.io.IOException;

import model.ISprite;
import model.Permeability;
import model.element.Sprite;

public class RedLightWall extends MotionlessElement{

    private static final ISprite SPRITE = new Sprite('1', "WallRed.png");

    RedLightWall() {
    	super(SPRITE, Permeability.BLOCKED);
    	try {
			SPRITE.loadImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
