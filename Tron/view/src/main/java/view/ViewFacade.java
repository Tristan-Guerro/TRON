package view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import controller.IOrderPerformer;
import controller.UserOrder;
import model.ILevel;
import showboard.BoardFrame;


public class ViewFacade implements IViewFacade, KeyListener, Runnable {

    private static final int squareNumberWidth =60;
    private static final int squareNumberHeight = 40;   
    private static final int squareSize = 20;
    private Rectangle closeView;   
    private BoardFrame boardFrame;    
    private ILevel level;
    private IOrderPerformer  orderPerformer;
    
    public ViewFacade(final ILevel level) throws IOException {
        boardFrame = new BoardFrame("Tron ", false);
    	this.setLevel(level); 
        this.setCloseView(new Rectangle(0, 0, squareNumberWidth, squareNumberHeight));
        SwingUtilities.invokeLater(this); 
    }

    @Override
    public final void displayMessage(final String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public final void run() { 

        boardFrame.setDimension(new Dimension(squareNumberWidth, squareNumberHeight));
        boardFrame.setDisplayFrame(this.getCloseView()); 
        boardFrame.setSize(squareNumberWidth * squareSize, squareNumberHeight * squareSize);  
        boardFrame.addKeyListener(this); 

        for (int x = 0; x < squareNumberWidth; x++) { 
            for (int y = 0; y < squareNumberHeight; y++) {
                boardFrame.addSquare(this.level.getOnTheLevelXY(x, y), x, y);
            }
        }
        
        boardFrame.addPawn(getLevel().getRedLightcycle());
        boardFrame.addPawn(getLevel().getBlueLightcycle()); 
        
        this.getLevel().getObservable().addObserver(boardFrame.getObserver()); 
        boardFrame.setVisible(true);
    }

    @Override
	public void LightwallPaint(int x, int y) {
		boardFrame.addSquare(this.level.getOnTheLevelXY(x, y), x, y);
	}
	
	  static UserOrder keyCodeToUserOrder(final int keyCode) {
	        UserOrder userOrder;
	                
	        switch (keyCode) {
	            case KeyEvent.VK_RIGHT:
	            	userOrder = UserOrder.P1RIGHT;
	                break;
	            case KeyEvent.VK_LEFT:
	                userOrder = UserOrder.P1LEFT;
	                break;
	            case KeyEvent.VK_Q:
	            	userOrder = UserOrder.P2RIGHT;
	                break;
	            case KeyEvent.VK_D:
	                userOrder = UserOrder.P2LEFT;
	                break;
	            default:
	                userOrder = UserOrder.NOP;
	                break;
	        }
	        return userOrder;
	    }

    @Override
	public void keyTyped(final KeyEvent keyEvent) {
	    }

    @Override
	public final void keyPressed(final KeyEvent keyEvent) {
	        try { 
	            this.getOrderPerformer().orderPerform(keyCodeToUserOrder(keyEvent.getKeyCode()));
	        } catch (final IOException exception) {
	            exception.printStackTrace();
	        }
	    }

    @Override
	public void keyReleased(final KeyEvent keyEvent) {
	    }
	
    public ILevel getLevel() {
		return level;
	}

	public void setLevel(ILevel level) throws IOException {
		this.level = level;
        for (int x = 0; x < ViewFacade.squareNumberWidth; x++) { 
            for (int y = 0; y < ViewFacade.squareNumberHeight; y++) {
                this.getLevel().getOnTheLevelXY(x, y).getSprite().loadImage();
            }
        }
	}

	public Rectangle getCloseView() {
		return closeView;
	}

	public void setCloseView(Rectangle closeView) {
		this.closeView = closeView;
	}

	public IOrderPerformer getOrderPerformer() {
		return orderPerformer;
	}

	public void setOrderPerformer(IOrderPerformer orderPerformer) {
		this.orderPerformer = orderPerformer;
	}

}