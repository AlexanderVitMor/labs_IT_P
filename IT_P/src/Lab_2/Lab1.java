package Lab_2;

import java.util.Arrays;
import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args){
        double[] myArray = new double[9];
        for (int i = 0; i < 9; i++){
            Scanner in = new Scanner(System.in);
            double coordinates = in.nextDouble();
            myArray[i] = coordinates;
        }

        Point3d firstPoint = new Point3d(myArray[0], myArray[1], myArray[2]);
        Point3d secondPoint = new Point3d(myArray[3], myArray[4], myArray[5]);
        Point3d thirdPoint = new Point3d(myArray[6], myArray[7], myArray[8]);

        // cd - Closed - замкнутый массив эмитация кольца
        Point3d[] arrayPointsCd = {firstPoint, secondPoint, thirdPoint, firstPoint};

        if (Point3d.equalityCheck(arrayPointsCd) && equalityCheckMax(arrayPointsCd)) System.out.println("Данный треугольник не существует");
        else System.out.println("Площадь треугольника равна: " + computerArea(arrayPointsCd));

    }

    /**
     * ываываыва
     * @param firstPoint ргриигрти
     * @param secondPoint вапвапвап
     * @return вапвапв
     */
    public static double distanceTo(Point3d firstPoint, Point3d secondPoint)
    {

        double[] firstCoordinates = firstPoint.getXYZ();
        double[] secondCoordinates = secondPoint.getXYZ();
        double sumPow = 0;

        for (int i = 0; i < 3; i++) {
            sumPow += Math.pow((secondCoordinates[i] - firstCoordinates[i]), 2);
        }
        return (double) Math.round(Math.sqrt(sumPow) * 100) / 100;
    }

    public static double computerArea(Point3d[] arrayPointsCd){

        double semiPerimeter = 0;

        double[] arrayDistance = new double[3];
        for (int i = 0; i < 3; i++) {
            arrayDistance[i] = distanceTo(arrayPointsCd[i], arrayPointsCd[i + 1]);
            semiPerimeter += arrayDistance[i] / 2;
        }

        double triangleArea = Math.sqrt(semiPerimeter);

        for (int i = 0; i < 3; i++) {
            triangleArea *= Math.sqrt(semiPerimeter - arrayDistance[i]);
        }

        return triangleArea;
    }

   public static boolean equalityCheckMax(Point3d[] arrayPointsCd){
       double[][] arrayDistance = new double[2][3];
       int maxId = arrayDistance[0].length - 1;
       for (int i = 0; i < 2; i++) {
           for (int j = 0; j < 3; j++) {
               if (i == 0) arrayDistance[i][j] = distanceTo(arrayPointsCd[j], arrayPointsCd[j + 1]);
               else {
                   if (j < 1) arrayDistance[1][2] = arrayDistance[0][j] + arrayDistance[0][j + 1];
                   else if (j < maxId) arrayDistance[1][0] = arrayDistance[0][j] + arrayDistance[0][j + 1];
                   else arrayDistance[1][1] = arrayDistance[0][j] + arrayDistance[0][0];
               }
           }
       }

       for (int i = 0; i < 3; i++) {
           if (arrayDistance[0][i] >= arrayDistance[1][i]) return true;
       }
       return false;
    }
}
