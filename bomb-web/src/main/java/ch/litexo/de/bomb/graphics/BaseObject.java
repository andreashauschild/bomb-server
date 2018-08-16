package ch.litexo.de.bomb.graphics;

/**
 * @author Andreas Hauschild
 */
public class BaseObject {
    private double x=0;
    private double y=0;
    private double rotation=0;
    private BaseVector vector;



    public BaseObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public BaseObject(double x, double y, double rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }
}
