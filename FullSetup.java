// Use this class to modify any code- then copy / paste over to
// FullSetup.java for actual gametime running. 
// TELEOPERATOR CLASS

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Full Setup") // USE TESTING2023 CONFIGURATION
public class FullSetup extends LinearOpMode {
    private DcMotor rFrontMotor;
    private DcMotor lFrontMotor;
    private DcMotor rBackMotor;
    private DcMotor lBackMotor;

    private Servo servo;
    private DcMotor lowHexMotor;
    double left_power; 
    double right_power;
    double servoPower;
    double lowerLiftPower;
    double upperLiftPower;
    double powerDivisorL;
    double powerDivisorR;

    public void initialize() {
        rFrontMotor = hardwareMap.get(DcMotor.class, "rFrontMotor");
        lFrontMotor = hardwareMap.get(DcMotor.class, "lFrontMotor");
        rBackMotor = hardwareMap.get(DcMotor.class, "rBackMotor");
        lBackMotor = hardwareMap.get(DcMotor.class, "lBackMotor");

        servo = hardwareMap.get(Servo.class, "servo");
        lowHexMotor = hardwareMap.get(DcMotor.class, "lowHexMotor");

        rFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rBackMotor.setDirection(DcMotor.Direction.REVERSE);
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }
    // Main code for first human driver to control driving
    public void operate_driver() {
        //powerDivisorL = 3.5;
        //powerDivisorR = 3.5;
        left_power = gamepad1.left_stick_y;
        right_power = gamepad1.right_stick_y;
        
        telemetry.addData("LPower", left_power);
        telemetry.addData("RPower", right_power);
    
        /*if (gamepad1.right_bumper || gamepad1.left_bumper) {
            powerDivisorL = 1.5;
            powerDivisorR = 1.5;
        }*/
        //powerDivisorL += gamepad1.right_trigger*3;
        //powerDivisorR += gamepad1.left_trigger*3;
        // allows for micro-precision in movement, as the triggers are 
        // analog and their effect is multiplicative
        powerDrive();
    }
    // Reduces redundant code
    // Drives the bot at a certain motor power level (powerDivisor)
    public void powerDrive() {
        lFrontMotor.setPower(left_power/2.5);
        lBackMotor.setPower(left_power/2.5);
        rFrontMotor.setPower(right_power/2.5);
        rBackMotor.setPower(right_power/2.5);
        
        if(gamepad1.x) {
            strafeRight();
        }
        if(gamepad1.b) {
            strafeLeft();
        }
        /*if (gamepad1.dpad_up) {
            lBackMotor.setPower(-(3.0) / powerDivisorL);
            lFrontMotor.setPower(-(3.0) / powerDivisorL);
            rFrontMotor.setPower((3.0) / powerDivisorR);
            rBackMotor.setPower((3.0) / powerDivisorR);
        }
        if (gamepad1.dpad_down) {
            lBackMotor.setPower((3.0) / powerDivisorL);
            lFrontMotor.setPower((3.0) / powerDivisorL);
            rFrontMotor.setPower(-(3.0) / powerDivisorR);
            rBackMotor.setPower(-(3.0) / powerDivisorR);
        }*/
    }
    
    private void powerDrive2(double rFrontPower, double lFrontPower, double rBackPower, double lBackPower) {
        rFrontMotor.setPower(rFrontPower);
        lFrontMotor.setPower(lFrontPower);
        rBackMotor.setPower(rBackPower);
        lBackMotor.setPower(lBackPower);
    }
    
    public void strafeLeft() {
        powerDrive2(0.75,-0.75,-0.75,0.75);
        sleep(250);
        /*lBackMotor.setPower((1));
        lFrontMotor.setPower(-(1));
        rFrontMotor.setPower((1));
        rBackMotor.setPower(-(1));*/
        /*lBackMotor.setPower(-(1.0+0.45) / powerDivisorL);
        lFrontMotor.setPower((1.0+0.50) / powerDivisorL);
        rFrontMotor.setPower(-(1.0+0.30) / powerDivisorR);
        rBackMotor.setPower((1.0+0.20) / powerDivisorR);*/
    }
    public void strafeRight() {
        powerDrive2(-0.5,0.5,0.5,-0.5);
        
        sleep(500);
        
        
    }
    

    // Main code for second human driver to control two-stage lift
    public void operate_lift() {
        lowerLiftPower = -gamepad2.left_stick_y;
        upperLiftPower = gamepad2.right_stick_y;
        
        if(gamepad2.left_trigger > 0) {
            servo.setPosition(1);    
        }
        else if(gamepad2.right_trigger > 0) {
            servo.setPosition(0);
        }
        else {
            servo.setPosition(0.5);
        }
        
        lowHexMotor.setPower(-lowerLiftPower);
        //highHexMotor.setPower(upperLiftPower);
    }
    @Override
    public void runOpMode() {
        initialize();
        waitForStart();
        while (opModeIsActive()) {
            operate_driver();
            operate_lift();
            telemetry.update();
        }
    }
    
    
    /* Original strafe code
    public void originalStrafeLeft() {
        lBackMotor.setPower(-1.0 / powerDivisor);
        lFrontMotor.setPower(1.0 / powerDivisor);
        rFrontMotor.setPower(-1.0 / powerDivisor);
        rBackMotor.setPower(1.0 / powerDivisor);
    }
    public void originalStrafeRight() {
        lBackMotor.setPower(1.0 / powerDivisor);
        lFrontMotor.setPower(-1.0 / powerDivisor);
        rBackMotor.setPower(-1.0 / powerDivisor);
        rFrontMotor.setPower(1.0 / powerDivisor);
    }
    */
}
