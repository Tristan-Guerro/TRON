package model.element.motionless;

import model.ISprite;
import model.Permeability;
import model.element.Element;

public abstract class MotionlessElement extends Element {
	
	   MotionlessElement(final ISprite sprite, final Permeability permeability) {
	        super(sprite, permeability);
	    }
}
