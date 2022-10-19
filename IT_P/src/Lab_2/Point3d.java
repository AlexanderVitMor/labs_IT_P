package Lab_2;

import java.util.Objects;

public class Point3d extends Point2d{
    private double zCoord;

    /**Описание нового объекта в классе
     * @param x устанавливается через метод setX()
     * @param y устанавливается через метод setX()
     * @param z устанавливается через метод setX()
     *          Первые два значения созданные в родительском классе приватные,
     *          Что бы не нарушать политику переменных используем данные методы.
     */
    public Point3d(double x, double y, double z){
        setX(x);
        setY(y);
        setZ(z);
    }

    public Point3d(){
        this(0, 0, 0);
    }

    public double getZ() {
        return zCoord;
    }

    public void setZ(double zCoord) {
        this.zCoord = zCoord;
    }

    /**Дополнительный метод
     * @return возвращает массив координат точек.
     */
    public double[] getXYZ(){
        return new double[]{getX(), getY(), getZ()};
    }

    /**Метод проверки объектов (Point3d) на одинаковые координаты
     * @param arrayPointsCd передается замкнутый массив с точками
     * @return если точки одинаковые возвращает true, иначе false.
     */
    public static boolean equalityCheck(Point3d[] arrayPointsCd){
        for (int i = 0; i < 3; i++) {
            if (arrayPointsCd[i].equals(arrayPointsCd[i + 1])) return true;
        }
        return false;
    }

    /**Создание equals для нового класса
     * @param o в параметры поступает объект для сравнения
     * @return Что бы предотвратить дубликации кода, переменные созданные
     * в родительском классе передаются через super.equals по факту
     * дергают метод описанный уже в родительском классе.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3d point3d = (Point3d) o;
        return Double.compare(point3d.zCoord, zCoord) == 0 && super.equals(point3d);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zCoord);
    }
}
