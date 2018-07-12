package controller;

import java.io.IOException;
import model.IModel;
import model.IMobile;
import view.IViewFacade;


public class ControllerFacade implements IController, IOrderPerformer {

    private static final int speed = 120;
    private IViewFacade view;
    private IModel model;
    private UserOrder stackOrder;
    private IMobile redLightcycle;
    private IMobile blueLightcycle;
	

	public ControllerFacade(final IViewFacade view, final IModel model) {
		this.setView(view);
	    this.setModel(model);
	    this.clearStackOrder();
		redLightcycle = getModel().getLevel().getRedLightcycle();
		redLightcycle.alive();
		redLightcycle.setDirection(Direction.UP);
		blueLightcycle = getModel().getLevel().getBlueLightcycle();
		blueLightcycle.alive();
		blueLightcycle.setDirection(Direction.DOWN);
	}


	public void play() throws InterruptedException, IOException {
		
		while (redLightcycle.isAlive() && blueLightcycle.isAlive()) { 
            
			Thread.sleep(speed); 
			
			switch (this.getStackOrder()) { 
                case P1RIGHT:
                	rightMovePerformer(redLightcycle);
                    break;
                case P1LEFT:
                	leftMovePerformer(redLightcycle);
                    break;
                case P2RIGHT:
                	rightMovePerformer(blueLightcycle);
                    break;
                case P2LEFT:
                	leftMovePerformer(blueLightcycle);
                    break;
                case NOP:
                default:
                	noMovePerformer(redLightcycle);
                	noMovePerformer(blueLightcycle);
                	break;
			}
			spawnLightWall(blueLightcycle);
			spawnLightWall(redLightcycle);

			checkLightcycleCollision(redLightcycle, blueLightcycle);
			
		
			if(redLightcycle.isKilled()) killerPerformer(redLightcycle);
			if(blueLightcycle.isKilled()) killerPerformer(blueLightcycle);
			
			
            this.clearStackOrder(); 

        }
		if (!redLightcycle.isAlive() && blueLightcycle.isAlive()) this.getView().displayMessage("Winner = Blue Player");
		else if (!blueLightcycle.isAlive() && redLightcycle.isAlive()) this.getView().displayMessage("Winner = Red Player");
		else this.getView().displayMessage(" Equality ");
	}
	
	private void spawnLightWall(IMobile lightcycle) throws IOException {
		int x = lightcycle.getX();
		int y = lightcycle.getY();
		
		switch(lightcycle.getDirection()) {
		case DOWN:
			y = y-1;
			break;
		case UP:
			y = y+1;
			break;
		case RIGHT:
			x = x-1;
			break;
		case LEFT:
			x = x+1;
			break;
		case NOTHING:
		default:
			break;
		}
		getModel().getLevel().createLightWall(x, y, lightcycle.getLightcycleColor());
		getView().LightwallPaint(x, y);
}
	
	
	private void checkLightcycleCollision(IMobile lightcycle,IMobile lightcycle2) {
		if(lightcycle.getX()==lightcycle2.getX() && lightcycle.getY()==lightcycle2.getY()) {
			lightcycle.die();
			lightcycle2.die();
		}
	}
	
	private void killerPerformer(IMobile lightcycle) {
		switch(lightcycle.getDirection()) {
			case DOWN:
				lightcycle.moveUp();
				lightcycle.die();
				break;
			case UP:
				lightcycle.moveDown();
				lightcycle.die();
				break;
			case RIGHT:
				lightcycle.moveLeft();
				lightcycle.die();
				break;
			case LEFT:
				lightcycle.moveRight();
				lightcycle.die();
				break;
			case NOTHING:
			default:
				lightcycle.doNothing();
				break;	
		}
	}
	
	private void noMovePerformer(IMobile lightcycle) {
		switch(lightcycle.getDirection()) {
			case UP:
				lightcycle.moveUp();
				break;
			case DOWN:
				lightcycle.moveDown();
				break;
			case LEFT:
				lightcycle.moveLeft();
				break;
			case RIGHT:
				lightcycle.moveRight();
				break;
			case NOTHING:
			default:
				lightcycle.doNothing();
				break;	
		}
	}
	
	private void rightMovePerformer(IMobile lightcycle) {
		switch(lightcycle.getDirection()) {
			case UP:
				lightcycle.moveRight();
				lightcycle.setDirection(Direction.RIGHT);
				break;
			case DOWN:
				lightcycle.moveLeft();
				lightcycle.setDirection(Direction.LEFT);
				break;
			case LEFT:
				lightcycle.moveUp();
				lightcycle.setDirection(Direction.UP);
				break;
			case RIGHT:
				lightcycle.moveDown();
				lightcycle.setDirection(Direction.DOWN);
				break;
			case NOTHING:
			default:
				lightcycle.doNothing();
				break;	
		}
	}
	
	private void leftMovePerformer(IMobile lightcycle) {
		switch(lightcycle.getDirection()) {
			case UP:
				lightcycle.moveLeft();
				lightcycle.setDirection(Direction.LEFT);
				break;
			case DOWN:
				lightcycle.moveRight();
				lightcycle.setDirection(Direction.RIGHT);
				break;
			case LEFT:
				lightcycle.moveDown();
				lightcycle.setDirection(Direction.DOWN);
				break;
			case RIGHT:
				lightcycle.moveUp();
				lightcycle.setDirection(Direction.UP);
				break;
			case NOTHING:
			default:
				lightcycle.doNothing();
				break;	
		}
	}

	public void orderPerform(UserOrder userOrder) throws IOException {
		this.setStackOrder(userOrder);
	}

    private IViewFacade getView() {
        return this.view;
    }
    
    private void setView(final IViewFacade view) {
        this.view = view;
    }
    
    private IModel getModel() {
        return this.model;
    }
    
    private void setModel(final IModel model) {
        this.model = model;
    }
    
    private UserOrder getStackOrder() {
        return this.stackOrder;
    }

    private void setStackOrder(final UserOrder stackOrder) {
        this.stackOrder = stackOrder;
    }

    private void clearStackOrder() {
        this.stackOrder = UserOrder.NOP;
    }

    @Override
    public IOrderPerformer getOrderPeformer() {
        return this;
    }

}
