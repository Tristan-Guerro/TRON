package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Observable;

import model.element.motion.Lightcycle;
import model.element.motionless.MotionlessFactory;

public class Level extends Observable implements ILevel {

    public int width = 60;
    public int height = 40;
    private IElement[][] onTheLevel;
    private IMobile redLightcycle;
    private IMobile blueLightcycle;

    Level(final int idlevel) throws IOException, SQLException {       
    	super();
        this.loadLevel(idlevel);
    }

	private void loadLevel(final int idlevel) throws IOException {
		
        this.onTheLevel = new IElement[this.getWidth()][this.getHeight()];

    	for (int n=0; n<height; n++)
    	{
    		for (int i=0; i < width;i++)
    		{
    			this.setOnTheLevelXY(MotionlessFactory.getFromFileSymbol(' '), i, n);
    		}
    	}

        final BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(( "level/" + Integer.toString(idlevel) + ".txt"))));
        String line;
        int y = 0;

        line = buffer.readLine();
        while (line != null) {
            for (int x = 0; x < line.toCharArray().length; x++) {
            	char temp = line.toCharArray()[x];
            	
            	switch (temp) {
            	case 'B':
            		setRedLightcycle(new Lightcycle(x, y, this, PlayerColor.RED));
            		break;
            	case 'A':
            		setBlueLightcycle(new Lightcycle(x, y, this, PlayerColor.BLUE));
                	break;
                default:
                	this.setOnTheLevelXY(MotionlessFactory.getFromFileSymbol(temp), x, y);
                break;
                }
            	
            }
            line = buffer.readLine();
            y++;
        }

        buffer.close();
    }
	
	public IElement createLightWall(int x, int y, PlayerColor color) {
			if(color == PlayerColor.RED) this.setOnTheLevelXY(MotionlessFactory.createLightWallRed(), x, y);
			else if(color == PlayerColor.BLUE) this.setOnTheLevelXY(MotionlessFactory.createLightWallBLue(), x, y);
		return this.getOnTheLevelXY(x, y);
	}
    
	@Override
    public final int getWidth() {
        return this.width;
    }

	@Override
    public final int getHeight() {
        return this.height;
    }

	@Override
    public final IElement getOnTheLevelXY(final int x, final int y) {
        return this.onTheLevel[x][y];
    }

    public void setOnTheLevelXY(final IElement element, final int x, final int y) {
        this.onTheLevel[x][y] = element;
    }

    @Override
    public final void setMobileHasChanged() {
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public Observable getObservable() {
        return this;
    }

    @Override
	public IMobile getRedLightcycle() {
		return redLightcycle;
	}

	public void setRedLightcycle(IMobile redLightcycle) {
		this.redLightcycle = redLightcycle;
	}

	@Override
	public IMobile getBlueLightcycle() {
		return blueLightcycle;
	}

	public void setBlueLightcycle(IMobile blueLightcycle) {
		this.blueLightcycle = blueLightcycle;
	}
}