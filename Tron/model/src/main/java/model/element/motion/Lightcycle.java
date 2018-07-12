package model.element.motion;

import java.io.IOException;

import controller.Direction;
import model.ILevel;
import model.ISprite;
import model.Permeability;
import model.PlayerColor;
import model.element.Sprite;

public class Lightcycle extends Mobile implements ISprite{

    private static final Sprite sprite = new Sprite('A', "Wall.jpg");
    private static final Sprite spriteDieRed = new Sprite('A', "DeathRed.gif");
    private static final Sprite spriteMoveLeftRed = new Sprite('A', "BikeRedLeftB.png");
    private static final Sprite spriteMoveRightRed = new Sprite('A', "BikeRedRightB.png");
    private static final Sprite spriteMoveUpRed = new Sprite('A', "BikeRedUpB.png");
    private static final Sprite spriteMoveDownRed = new Sprite('A', "BikeRedDownB.png");
    private static final Sprite spriteDieBlue = new Sprite('B', "DeathBlue.gif");
    private static final Sprite spriteMoveLeftBlue = new Sprite('B', "BikeBlueLeftB.png");
    private static final Sprite spriteMoveRightBlue = new Sprite('B', "BikeBlueRightB.png");
    private static final Sprite spriteMoveUpBlue = new Sprite('B', "BikeBlueUpB.png");
    private static final Sprite spriteMoveDownBlue = new Sprite('B', "BikeBlueDownB.png");
    
    private PlayerColor LightcycleColor;
    private Direction Direction;

    public Lightcycle(final int x, final int y, final ILevel level, PlayerColor color) throws IOException {
        super(x, y, sprite, level, Permeability.BLOCKED);
        setLightcycleColor(color);
        sprite.loadImage();
        if ( getLightcycleColor() == PlayerColor.RED) {
	        spriteDieRed.loadImage();
	        spriteMoveLeftRed.loadImage();
	        spriteMoveRightRed.loadImage();
	        spriteMoveUpRed.loadImage();
	        spriteMoveDownRed.loadImage();
        }

	    else if ( getLightcycleColor() == PlayerColor.BLUE) {
	        spriteDieBlue.loadImage();
	        spriteMoveLeftBlue.loadImage();
	        spriteMoveRightBlue.loadImage();
	        spriteMoveUpBlue.loadImage();
	        spriteMoveDownBlue.loadImage();
	    }
    }
    
    @Override
    public final void moveLeft() {
        super.moveLeft();
        
        if ( getLightcycleColor() == PlayerColor.RED) {
	        this.setSprite(spriteMoveLeftRed);
        }

	    else if ( getLightcycleColor() == PlayerColor.BLUE) {
	    	this.setSprite(spriteMoveLeftBlue);
	    }
    }

    @Override
    public final void moveRight() {
        super.moveRight();
        
        if ( getLightcycleColor() == PlayerColor.RED) {
        	this.setSprite(spriteMoveRightRed);
        }

	    else if ( getLightcycleColor() == PlayerColor.BLUE) {
	    	this.setSprite(spriteMoveRightBlue);
	    }
    }
   
    @Override
    public final void moveUp() {
        super.moveUp();
        
        if ( getLightcycleColor() == PlayerColor.RED) {
        	this.setSprite(spriteMoveUpRed);
        }

	    else if ( getLightcycleColor() == PlayerColor.BLUE) {
	    	this.setSprite(spriteMoveUpBlue);
	    }
    }

    @Override
    public final void moveDown() {
        super.moveDown();
       
        if ( getLightcycleColor() == PlayerColor.RED) {
        	this.setSprite(spriteMoveDownRed);
        }

	    else if ( getLightcycleColor() == PlayerColor.BLUE) {
	    	this.setSprite(spriteMoveDownBlue);
	    }
    }

    @Override
    public final void doNothing() {
        super.doNothing();
       
        if ( getLightcycleColor() == PlayerColor.RED) {
        	this.setSprite(spriteDieRed);
        }

	    else if ( getLightcycleColor() == PlayerColor.BLUE) {
	    	this.setSprite(spriteDieBlue);
	    }
    }

    @Override
	public final void die() {
        this.setDirection(Direction.NOTHING);
        super.die();
        
        if ( getLightcycleColor() == PlayerColor.RED) {
        	this.setSprite(spriteDieRed);
        }

	    else if ( getLightcycleColor() == PlayerColor.BLUE) {
	    	this.setSprite(spriteDieBlue);
	    }
    }

    @Override
	public void loadImage() throws IOException {
		
	}

    @Override
	public char getConsoleImage() {
		return 0;
	}	
	

    public PlayerColor getLightcycleColor() {
		return LightcycleColor;
	}

	public void setLightcycleColor(PlayerColor lightcycleColor) {
		LightcycleColor = lightcycleColor;
	}

	public Direction getDirection() {
		return Direction;
	}

	public void setDirection(Direction direction) {
		Direction = direction;
	}

}
