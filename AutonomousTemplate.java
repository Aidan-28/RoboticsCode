// Use this class to modify any autonomous code- then copy
// and paste over to Sonny.java for actual gametime running. 
// AUTONOMOUS CLASS

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Template")
public class AutonomousTemplate extends LinearOpMode {

    private DcMotor rFrontMotor;
    private DcMotor lFrontMotor;
    private DcMotor rBackMotor;
    private DcMotor lBackMotor;

    private Servo servo;
    private DcMotor lowHexMotor;
    private DcMotor highHexMotor;

    public void initialize() {
        /*How to add more motors/servos/sensors/other stuff
        1. Go to the app and connect to the robot
        2. Click the 3 dots in the corner
        3. Click 'Configure Robot'
        4. Choose a configuration (The one we have rn is called 'Configuration + stuff'
            there may be more, just pick one) and hit edit
        5. Click the Control Hub Portal
        6. Choose between the Control
        */
        rFrontMotor = hardwareMap.get(DcMotor.class, "rFrontMotor");
        lFrontMotor = hardwareMap.get(DcMotor.class, "lFrontMotor");
        rBackMotor = hardwareMap.get(DcMotor.class, "rBackMotor");
        lBackMotor = hardwareMap.get(DcMotor.class, "lBackMotor");

        servo = null;//hardwareMap.get(Servo.class, "servo");
        lowHexMotor = null;//hardwareMap.get(DcMotor.class, "lowHexMotor");
        highHexMotor = null;//hardwareMap.get(DcMotor.class, "highHexMotor");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }
    private void drive(double rFrontPower, double lFrontPower, double rBackPower, double lBackPower) {
        //Makes the robot drive, I know, shocking
        rFrontMotor.setPower(rFrontPower);
        lFrontMotor.setPower(-lFrontPower);
        rBackMotor.setPower(rBackPower);
        lBackMotor.setPower(-lBackPower);
    }
    
    private void turnLeft(){
        //You can figure this one out, feel free to change stuff
        drive(0.5,0.5,0.5,0.5);
        sleep(475);
        drive(0,0,0,0);
        sleep(200);
    }
    private void turnRight(){
        //You can figure this one out too, feel free to change stuff
        drive(-0.5,-0.5,-0.5,-0.5);
        sleep(475);
        drive(0,0,0,0);
        sleep(200);
    }
   
    private void strafe(int time, double speed){
        //Really buggy as of rn, will try and fix I swear
        //Or does it work? I don't remember I'll check later
        //If you're reading this I never checked
        //Robot weight is cringe and we should just make our robot a wheel
        drive(speed,-speed,-speed,speed);
        sleep(time);
        drive(0,0,0,0);
    }
    public void autoFull(int delay, int fix){
        //All code for the auto mode is stored in here
        /*Delay is used to delay the beginning of the mode if your teammate's 
        robot needs to do something before your robot begins*/
        //Fix is used to change the distance driven as the robot's weight changes 
    }
    
    @Override
    public void runOpMode() {
        //Runs the autonomous mode
        initialize();//Runs after you hit 'Init', motors + servos declared here
        waitForStart();//I think this just waits for you to hit the play button
        autoFull(0,0);//Should contain all your code for the auto mode
        while (opModeIsActive()) {
            //I have no idea what this does
            //I think it just lets the robot run and stuff
            telemetry.update();
        }
    }
}


/*private void powerLift(double lowPower, double highPower, double servoPower) {
        lowHexMotor.setPower(lowPower);
        highHexMotor.setPower(highPower);
        servo.setPosition(servoPower);
    }*/
