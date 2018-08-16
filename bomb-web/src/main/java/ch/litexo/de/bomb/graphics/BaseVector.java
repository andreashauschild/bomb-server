package ch.litexo.de.bomb.graphics;

import static java.lang.Math.*;

/**
 * @author Andreas Hauschild
 */
public class BaseVector {
    private double x;
    private double y;

    public BaseVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public BaseVector plus(BaseVector vector){
        this.x = this.x + vector.getX();
        this.y = this.y + vector.getY();
        return this;
    }

    public BaseVector minus(BaseVector vector){
        this.x = this.x - vector.getX();
        this.y = this.y - vector.getY();
        return this;
    }

    public BaseVector multiply(BaseVector vector){
        this.x = this.x * vector.getX();
        this.y = this.y * vector.getY();
        return this;
    }

    public BaseVector multiply(double val){
        return new BaseVector(this.x * val,this.y * val);
    }

    public BaseVector devide(BaseVector vector){
        this.x = this.x / vector.getX();
        this.y = this.y / vector.getY();
        return this;
    }

    public double getX() {
        return x;
    }

    public BaseVector setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public BaseVector setY(double y) {
        this.y = y;
        return this;
    }

    /**
     * Retruns the norm ("Betrag - Länge") of the vector
     * @return
     */
    public double norm() {
        return Math.sqrt(pow(getX(),2)+ pow(getY(),2));
    }

    public double scalar(BaseVector b){
        return getX()*b.getX() + getY()*b.getY();
    }

    public double angle(BaseVector b){
        // Info java erwartet das Bogenmass
        // Hilfe https://www.matheretter.de/rechner/trigonometrie
        double val = scalar(b) / Math.abs(norm()) * Math.abs(b.norm());
        return Math.toDegrees(Math.acos(val));
    }

    public BaseVector rotate(double degree){
        double rad = Math.toRadians(degree);
        double oldX = getX();
        double oldY = getY();
        setX(oldX*cos(rad)-oldY*sin(rad));
        setY(oldX*sin(rad)+oldY*cos(rad));
        return this;
    }

}
