package controller;

import java.io.IOException;


public interface IController {
	
    void play() throws InterruptedException, IOException;
    IOrderPerformer getOrderPeformer();
    
}
