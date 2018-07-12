package model.element.motionless;

public class MotionlessFactory {

    private static final Background Background = new Background();  
    private static final Wall wall = new Wall();  
    private static final RedLightWall redwall = new RedLightWall();
    private static final BlueLightWall lightwallblue = new BlueLightWall();
    private static final Home home = new Home();
    
    private static MotionlessElement[] motionless  = {
            Background,
            wall,
            redwall,
            lightwallblue,
            home
            };

    public static MotionlessElement createWall() {
        return wall;
    }

    public static MotionlessElement createLightWallRed() {
        return redwall;
    }

    public static MotionlessElement createLightWallBLue() {
        return lightwallblue;
    }
    
    public static MotionlessElement createHome() {
        return home;
    }
    
    public static MotionlessElement getFromFileSymbol(final char fileSymbol) {
    	
        for (final MotionlessElement motionlessElement : motionless) {
            if (motionlessElement.getSprite().getConsoleImage() == fileSymbol) {
                return motionlessElement;
            }
        }
        return Background;
    }
}
