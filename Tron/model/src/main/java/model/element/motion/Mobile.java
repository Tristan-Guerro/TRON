package model.element.motion;

import java.awt.Point;

import model.ILevel;
import model.IMobile;
import model.ISprite;
import model.Permeability;
import model.element.Element;
import showboard.IBoard;

public abstract class Mobile extends Element implements IMobile {
    
    private Point position;
    protected Boolean alive;
    private ILevel level;
    private IBoard board;

    Mobile(final ISprite sprite, final ILevel level, final Permeability permeability) {
        super(sprite, permeability);
        this.setLevel(level);
        this.position = new Point();
    }

    Mobile(final int x, final int y, final ISprite sprite, final ILevel level, final Permeability permeability) {
        this(sprite, level, permeability);
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void moveUp() {
    	this.setY(this.getY() - 1);
        this.setHasMoved();
    }

    @Override
    public void moveLeft() {
    	this.setX(this.getX() - 1);
        this.setHasMoved();
    }

    @Override
    public void moveDown() {
    	this.setY(this.getY() + 1);
        this.setHasMoved();
    }

    @Override
    public void moveRight() {
    	this.setX(this.getX() + 1);
        this.setHasMoved();
    }

    @Override
    public void doNothing() {
        this.setHasMoved();
    }

    protected void setHasMoved() {
        this.getLevel().setMobileHasChanged();
    }

    @Override
    public final int getX() {
        return this.getPosition().x;
    }

    public final void setX(final int x) {
        this.getPosition().x = x;
    }

    @Override
    public final int getY() {
        return this.getPosition().y;
    }

    public final void setY(final int y) {
        this.getPosition().y = y;
    }

    public ILevel getLevel() {
        return this.level;
    }

    private void setLevel(final ILevel level) {
        this.level = level;
    }

    public Boolean isAlive() {
        return this.alive;
    }

    public void alive() {
    	this.alive = true;
    	this.setHasMoved();
    }
    
    public void die() {
        this.alive = false;
        this.setHasMoved();
    }
   
    @Override
    public Boolean isKilled() {
    return this.getLevel().getOnTheLevelXY(this.getX(), this.getY()).getPermeability() == Permeability.BLOCKED;
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    public void setPosition(final Point position) {
        this.position = position;
    }

    protected IBoard getBoard() {
        return this.board;
    }

}