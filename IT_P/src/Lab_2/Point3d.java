package Lab_2;

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

}
