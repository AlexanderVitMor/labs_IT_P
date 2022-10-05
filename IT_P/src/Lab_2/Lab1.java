package Lab_2;

import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args){
        double[] myArray = new double[4];
        for (int i = 0; i < 4; i++){
            Scanner in = new Scanner(System.in);
            double coordinates = in.nextDouble();
            myArray[i] = coordinates;
        }

        Point2d FirstPoint = new Point2d(myArray[0], myArray[1]);
        Point2d SecondPoint = new Point2d(myArray[2], myArray[3]);
        System.out.println(distanceTo(FirstPoint, SecondPoint));

    }
    public static double distanceTo(Point2d firstPoint, Point2d secondPoint)
    {
        double x1, x2, y1, y2;
        x1 = firstPoint.getX();
        y1 = firstPoint.getY();
        x2 = secondPoint.getX();
        y2 = secondPoint.getY();
        double distance = Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));
        return distance;
    }
}
