package Lab_2;

import java.util.Objects;

public class Point3d extends Point2d{
    private double zCoord;
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

    public double[] getXYZ(){
        return new double[]{getX(), getY(), getZ()};
    }

    public static boolean equalityCheck(Point3d[] arrayPointsCd){
        for (int i = 0; i < 3; i++) {
            if (arrayPointsCd[i].equals(arrayPointsCd[i + 1])) return true;
        }
        return false;
    }

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
