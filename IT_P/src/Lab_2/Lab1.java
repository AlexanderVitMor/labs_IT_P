package Lab_2;

import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args){
        double[] myArray = new double[6];
        for (int i = 0; i < 9; i++){
            Scanner in = new Scanner(System.in);
            double coordinates = in.nextDouble();
            myArray[i] = coordinates;
        }

        Point3d FirstPoint = new Point3d(myArray[0], myArray[1], myArray[2]);
        Point3d SecondPoint = new Point3d(myArray[3], myArray[4], myArray[5]);
        Point3d ThirdPoint = new Point3d(myArray[3], myArray[4], myArray[5]);
        System.out.println(distanceTo(FirstPoint, SecondPoint, ThirdPoint));

    }
    public static double distanceTo(Point3d firstPoint, Point3d secondPoint, Point3d thirdPoint)
    {
        double x1, x2, x3, y1, y2, y3, z1 ,z2 ,z3;
        x1 = firstPoint.getX();
        y1 = firstPoint.getY();
        z1 = firstPoint.getZ();
        x2 = secondPoint.getX();
        y2 = secondPoint.getY();
        z1 = secondPoint.getZ();
        x3 = thirdPoint.getX();
        y3 = thirdPoint.getY();
        z3 = thirdPoint.getZ();

        double distance = Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));
        return distance;
    }
}
