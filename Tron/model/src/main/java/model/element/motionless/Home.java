package model.element.motionless;

import java.io.IOException;

import model.ISprite;
import model.Permeability;
import model.element.Sprite;

public class Home extends MotionlessElement{

    private static final ISprite SPRITE = new Sprite(' ', "Home.png");

    Home() {
    	super(SPRITE, Permeability.PENETRABLE);
    	try {
			SPRITE.loadImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}