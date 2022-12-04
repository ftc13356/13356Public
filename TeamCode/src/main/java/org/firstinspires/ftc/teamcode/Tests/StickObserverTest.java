package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Components.CV.CVMaster;

@Autonomous(name = "StickObserverTest")

public class StickObserverTest extends LinearOpMode {

    @Override
    public void runOpMode() {
//        initialize camera and pipeline
        CVMaster cv = new CVMaster(this);
//      call the function to startStreaming
        cv.observeStick();
        waitForStart();
        while (opModeIsActive()) {
        }
//        stopStreaming
        cv.stopCamera();
    }
}


