/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DrumKitTestDrive;

/**
 *
 * @author Elly
 */
class DrumKit {
    // creates a drumset
    // playing the drums
    boolean topHat=true;
    int x=1;
    boolean snare=true;
    void playsnare () {
        System.out.println ("bang bang ba-bang");
         }
    void playTophat () {
        System.out.println ("ding ding da-ding");
    }
}
class DrumKitTestDrive {
    //test class for the Drum Kit
    public static void main (String[] args){
        DrumKit d= new DrumKit ();
        d.playsnare ();
        d.snare=false;
        if (d.snare==true){
            d.playsnare();
        }
        d.playTophat ();
    }
    
}
