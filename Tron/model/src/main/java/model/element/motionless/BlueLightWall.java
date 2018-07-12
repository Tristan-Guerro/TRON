package model.element.motionless;

import java.io.IOException;

import model.ISprite;
import model.Permeability;
import model.element.Sprite;

public class BlueLightWall extends MotionlessElement{
	
    private static final ISprite SPRITE = new Sprite('2', "WallBlue.png");

    BlueLightWall() {
    	super(SPRITE, Permeability.BLOCKED);
    	try {
			SPRITE.loadImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}

