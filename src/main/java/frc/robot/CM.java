package frc.robot;

public class CM {
     
    //Axes
    static int L_X_Axis = 0;
    static int L_Y_Axis = 1;
    static int L_Trigger = 2;
    static int R_Trigger = 3;
    static int R_X_Axis = 4;
    static int R_Y_Axis = 5;
    
    //Buttons
    static int A = 1;
    static int B = 2;
    static int X = 3;
    static int Y = 4;
    static int L_Bumper = 5;
    static int R_Bumper = 6;
    static int Back = 7;
    static int Start = 8;
    static int L_Joy_Down = 9;
    static int R_Joy_Down = 10;

    //Drive Modes
    int Tank = 1;
    
    int DriveMode = Tank;

    public static int 
        forwardR = R_Y_Axis, 
        forwardL = L_Y_Axis, 
        sidewaysR = R_X_Axis, 
        sidewaysL = L_X_Axis,
        shootSpin = R_Trigger, 
        intakeSpin = L_Trigger, 
        intakePos = L_Bumper, 
        shootPaddle = R_Bumper;

    //Assignments
    /*public CM() {
        if (DriveMode == Tank) {
            forwardR = R_Y_Axis;
            forwardL = L_Y_Axis;
            sidewaysR = R_X_Axis;
            sidewaysL = L_X_Axis;
            shootSpin = R_Trigger;
            shootPaddle = R_Bumper;
            intakeSpin = L_Trigger;
            intakePos = L_Bumper;
        }

    }*/


}
