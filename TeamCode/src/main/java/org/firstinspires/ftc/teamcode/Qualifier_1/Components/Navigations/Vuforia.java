//package org.firstinspires.ftc.teamcode.Qualifier_1.Components.Navigations;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//
//import org.firstinspires.ftc.robotcore.external.ClassFactory;
//import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
//import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
//import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
//import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
//import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
//import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
//import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
//import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
//import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
//import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
//import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
//import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;
//
//public class Vuforia extends Thread{
//    private OpMode op;
//    private double xpos, ypos, angle;
//    private String trackable;
//
//    private boolean targetVisible = false;
//
//    private VuforiaTrackables targetsSkystone;
//    private List<VuforiaTrackable> allTrackables = new ArrayList<>();
//
//    private OpenGLMatrix lastLocation = null;
//
//    private final float mmPerInch = 25.4f;
//
//    private VuforiaLocalizer vuforia = null;
//
//    public Vuforia(OpMode opMode, VuforiaLocalizer.CameraDirection camera_direction) {
//        op = opMode;
//        VuforiaLocalizer.CameraDirection CAMERA_CHOICE = camera_direction;
//    }
//
//    public void init(){
//
//        // Vuforia License Key
//        final String VUFORIA_KEY = "ATUOrmn/////AAABmVLVlWBtWUpnh9+EekIwR4lmMDXtnMrh/37lRyh+1m4oZJv1ANDvpS7D/Es9GNQ0wAkJ4YOHVWFjjsE5ptAFY2NRCAAwEY4VtvXEvSr3j/a0WR54dNfoCHRsnEaL5oQu25MoyOo7VrmhkE3xb2J9cNbsJzeqNaZWdQQpHkrgzEotos4i2tf/z+IMQxQ5nwH7Daiar93yoFv6FKeTh9MfI3bxVKR0nF+vrMzmNPC6YLk3yjqAKLqSgAvV0t07MBz9BjT2r58njS6qCo2U1H3sQXBlUcMdeKi4iclQaM+Oac+mZrzrhMvSEW7gC9mDhoL8l3zf2yMLPV9oGtnirNWn7ov/mupDtDecOUI4MPDNi9dt";
//
//        // Initialize Variables
//        //OpenGLMatrix lastLocation = null;
//        boolean targetVisible = false;
//        //final float mmPerInch = 25.4f;
//        final float halfField = 72 * mmPerInch;
//        final float quarterField = 36 * mmPerInch;
//        final float mmTargetHeight = 5.75f * mmPerInch;
//
//        // Use back camera
//        final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
//
//        // Instantiate Vuforia engine
//        VuforiaLocalizer vuforia;
//
//        // Show camera view on RC phone screen
//        int cameraMonitorViewId = op.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", op.hardwareMap.appContext.getPackageName());
//        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
//
//        // Set parameters
//        parameters.vuforiaLicenseKey = VUFORIA_KEY;
//        parameters.cameraDirection = CAMERA_CHOICE;
//
//        // Start Vuforia
//        vuforia = ClassFactory.getInstance().createVuforia(parameters);
//
//        // Load the data sets for the trackable objects. These particular data
//        // sets are stored in the 'assets' part of our application.
//        VuforiaTrackables targetsUltimateGoal = this.vuforia.loadTrackablesFromAsset("UltimateGoal");
//        VuforiaTrackable blueTowerGoalTarget = targetsUltimateGoal.get(0);
//        blueTowerGoalTarget.setName("Blue Tower Goal Target");
//        VuforiaTrackable redTowerGoalTarget = targetsUltimateGoal.get(1);
//        redTowerGoalTarget.setName("Red Tower Goal Target");
//        VuforiaTrackable redAllianceTarget = targetsUltimateGoal.get(2);
//        redAllianceTarget.setName("Red Alliance Target");
//        VuforiaTrackable blueAllianceTarget = targetsUltimateGoal.get(3);
//        blueAllianceTarget.setName("Blue Alliance Target");
//        VuforiaTrackable frontWallTarget = targetsUltimateGoal.get(4);
//        frontWallTarget.setName("Front Wall Target");
//        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
//        allTrackables.addAll(targetsUltimateGoal);
//
//        // Set the position of the perimeter targets with relation to origin (center of field)
//        redAllianceTarget.setLocation(OpenGLMatrix
//                .translation(0, -halfField, mmTargetHeight)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 180)));
//
//        blueAllianceTarget.setLocation(OpenGLMatrix
//                .translation(0, halfField, mmTargetHeight)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 0)));
//        frontWallTarget.setLocation(OpenGLMatrix
//                .translation(-halfField, 0, mmTargetHeight)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0 , 90)));
//
//        // The tower goal targets are located a quarter field length from the ends of the back perimeter wall.
//        blueTowerGoalTarget.setLocation(OpenGLMatrix
//                .translation(halfField, quarterField, mmTargetHeight)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0 , -90)));
//        redTowerGoalTarget.setLocation(OpenGLMatrix
//                .translation(halfField, -quarterField, mmTargetHeight)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));
//
//        // Set Phone Location
//        final int CAMERA_FORWARD_DISPLACEMENT = 0;
//        final int CAMERA_VERTICAL_DISPLACEMENT = 0;
//        final int CAMERA_LEFT_DISPLACEMENT = 0;
//        OpenGLMatrix phoneLocationOnRobot = OpenGLMatrix.translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES, CAMERA_CHOICE == FRONT ? 90 : -90, 0, 0));
//
//        // Give Phone Location to Trackable Listeners
//        for (VuforiaTrackable trackable : allTrackables) {
//            ((VuforiaTrackableDefaultListener)trackable.getListener()).setPhoneInformation(phoneLocationOnRobot, parameters.cameraDirection);
//        }
//
//        // Activate Vuforia Tracking
//        targetsSkystone.activate();
//
//    }
//
//    @Override
//    public void run() {
//
//        // Run until Thread is Interrupted
//        while (!isInterrupted()) {
//            targetVisible = false;
//            // Look for Trackable, Update Robot Location if Possible
//            for (VuforiaTrackable trackable : allTrackables) {
//                if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
//                    op.telemetry.addData("Visible Target ", trackable.getName());
//                    targetVisible = true;
//
//                    OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
//                    if (robotLocationTransform != null) {lastLocation = robotLocationTransform;}
//                    break;
//                }
//            }
//
//            // Return Location Data (Last Known Location)
//            if (targetVisible) {
//                VectorF translation = lastLocation.getTranslation();
//                op.telemetry.addData("Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f",
//                        translation.get(0) / mmPerInch, translation.get(1) / mmPerInch, translation.get(2) / mmPerInch);
//                Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
//                xpos = translation.get(0) / mmPerInch;
//                ypos = translation.get(1) / mmPerInch;
//                angle = rotation.thirdAngle;
//            }
//            else {
//                op.telemetry.addData("Visible Target", "none");
//            }
//            op.telemetry.update();
//        }
//    }
//
//    public double getVuforiaX() {
//        return xpos;
//    }
//
//    public double getVuforiaY() {
//        return ypos;
//    }
//
//    public double getVuforiaAngle() {
//        return angle;
//    }
//
//    public String getVuforiaTrackable() {
//        return trackable;
//    }
//}