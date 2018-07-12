package model;

import java.awt.Point;

import controller.Direction;
import showboard.IPawn;

public interface IMobile extends IElement, IPawn{

		void moveUp();
	    void moveLeft();
	    void moveDown();
	    void moveRight();	    
		void doNothing();
	    int getX();
	    int getY();
	    Boolean isAlive();
		void die();
		void alive();
		Boolean isKilled();
	    Point getPosition();
		void setDirection(Direction down);	
		Direction getDirection();	
		PlayerColor getLightcycleColor();
		
}
