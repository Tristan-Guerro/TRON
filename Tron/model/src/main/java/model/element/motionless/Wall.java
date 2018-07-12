package model.element.motionless;

import java.io.IOException;

import model.ISprite;
import model.Permeability;
import model.element.Sprite;

public class Wall extends MotionlessElement{

	private static final ISprite SPRITE = new Sprite('w', "Wall.jpg");

	Wall() {
	    super(SPRITE, Permeability.BLOCKED);
    	try {
			SPRITE.loadImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
