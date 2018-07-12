package main;

import java.io.IOException;
import java.sql.SQLException;

import controller.ControllerFacade;
import controller.IController;
import model.IModel;
import model.ModelFacade;
import view.IViewFacade;
import view.ViewFacade;


public abstract class Main {

	public static void main(final String[] args) throws IOException,  SQLException, InterruptedException {
		
		final IModel model = new ModelFacade(2); 									
		final IViewFacade view = new ViewFacade(model.getLevel()); 						
        final IController controller = new ControllerFacade(view, model); 		
        view.setOrderPerformer(controller.getOrderPeformer()); 	
        

			controller.play();
																
	}
}