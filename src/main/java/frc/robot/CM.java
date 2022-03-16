package frc.robot;

public class CM {
     
    //Axes
    int L_X_Axis = 0;
    int L_Y_Axis = 1;
    int L_Trigger = 2;
    int R_Trigger = 3;
    int R_X_Axis = 4;
    int R_Y_Axis = 5;
    
    //Buttons
    int A = 1;
    int B = 2;
    int X = 3;
    int Y = 4;
    int L_Bumper = 5;
    int R_Bumper = 6;
    int Back = 7;
    int Start = 8;
    int L_Joy_Down = 9;
    int R_Joy_Down = 10;

    //Drive Modes
    int Tank = 1;
    
    int DriveMode = Tank;

    public static int forwardR, forwardL, sidewaysR, sidewaysL,
        shootSpin, intakeSpin, intakePos,
        shootPaddle;

    //Assignments
    public CM() {
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

    }


}
