package Lab_2;

import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args){

    // Для удобства использования данных, создаем массив.
        double[] myArray = new double[9];

    // Данный массив служит начальным пакетом хранения координат, которые пользователь вводит с клавиатуры
        for (int i = 0; i < 9; i++){
            Scanner in = new Scanner(System.in);
            double coordinates = in.nextDouble();
            myArray[i] = coordinates;
        }
     // Создание новых экземпляров точек выделение памяти
        Point3d firstPoint = new Point3d(myArray[0], myArray[1], myArray[2]);
        Point3d secondPoint = new Point3d(myArray[3], myArray[4], myArray[5]);
        Point3d thirdPoint = new Point3d(myArray[6], myArray[7], myArray[8]);

    // cd - Closed - замкнутый массив эмитация кольца
    // Сюда помещаются все ранее созданные точки
        Point3d[] arrayPointsCd = {firstPoint, secondPoint, thirdPoint, firstPoint};

    // Завершение программы путем вывода нужного результата
        if (Point3d.equalityCheck(arrayPointsCd) || equalityCheckMax(arrayPointsCd)) System.out.println("Данный треугольник не существует");
        else System.out.println("Площадь треугольника равна: " + computerArea(arrayPointsCd));

    }

    /**
     * Метод для нахождения расстояния между любыми двумя точками
     * @param firstPoint Начальная точка
     * @param secondPoint Конечная точка
     * @return Возвращение расстояния от точки до точки с 2 знаками после запятой.
     */
    public static double distanceTo(Point3d firstPoint, Point3d secondPoint)
    {

        // Массивы с координатами
        double[] firstCoordinates = firstPoint.getXYZ();
        double[] secondCoordinates = secondPoint.getXYZ();
        double sumPow = 0;

        for (int i = 0; i < 3; i++) {
            sumPow += Math.pow((secondCoordinates[i] - firstCoordinates[i]), 2);
        }
        return (double) Math.round(Math.sqrt(sumPow) * 100) / 100;
    }

    /**Метод для нахождения площади треугольника
     * @param arrayPointsCd передается замкнутый массив с точками
     * @return площадь треугольника
     *
     */
    public static double computerArea(Point3d[] arrayPointsCd){

        double semiPerimeter = 0;

        // Массив с расстояниями между точками (стороны треугольника)
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

    /**Дополнительный метод проверки существования треугольника по сумме сторон
     * @param arrayPointsCd передается замкнутый массив с точками
     * @return если выполнилось условия не сущемтвование треуголника возвращается true, иначе false.
     * Метод проверки: создается двумерный массив 2 строки на 3 столбца,
     * в первой строке запмсываются поочередные длины сторон AB -> ВС -> CA,
     * во второй записывается сумма сторон, так что бы под 1 строкой, были разные стороны
     * Пример:    AB |
     *         AC + CB
     * Это сделано для упрощения сравнения в дальнейшем.
     */
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

   // Сравниваются только элементы строк в каждом столбце.
       for (int i = 0; i < 3; i++) {
           if (arrayDistance[0][i] >= arrayDistance[1][i]) return true;
       }
       return false;
    }
}
