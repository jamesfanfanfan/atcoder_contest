package DataStructure;

public class Function {

    /*
        convert you descart coordinates to the angle you want
     */
    Double ConvertCorDinatisToAngle(int x, int y){
        double theta = Math.atan2(y,x);
        return (theta>=0?theta:2*Math.PI+theta)*180/Math.PI;
    }
}
