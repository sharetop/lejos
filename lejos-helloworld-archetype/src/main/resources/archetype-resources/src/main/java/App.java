#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        EV3 ev3 = (EV3) BrickFinder.getLocal();
        ev3.getTextLCD().drawString("Hello EV3", 4, 4);
        
        ev3.getKeys().waitForAnyPress();
    }
}