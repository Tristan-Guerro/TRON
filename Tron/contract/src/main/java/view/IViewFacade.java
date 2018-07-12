package view;

import controller.IOrderPerformer;

public interface IViewFacade {

	    void displayMessage(String message);
		void setOrderPerformer(IOrderPerformer orderPeformer);
		void LightwallPaint(int x, int y);

}
