package Lab_2;

import java.util.Objects;

public class Point2d {

    private double xCoord;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2d point2d = (Point2d) o;
        return Double.compare(point2d.xCoord, xCoord) == 0 && Double.compare(point2d.yCoord, yCoord) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoord, yCoord);
    }

    private double yCoord;

    public Point2d(double x, double y){
        xCoord = x;
        yCoord = y;
    }
    public Point2d(){
        this(0, 0);
    }
    public double getX(){
        return xCoord;
    }

    public double getY(){
        return yCoord;
    }

    public void setX(double value){
        xCoord = value;
    }
    public void setY(double value){
        yCoord = value;
    }


}
