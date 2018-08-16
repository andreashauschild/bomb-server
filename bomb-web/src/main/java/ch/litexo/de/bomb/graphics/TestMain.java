package ch.litexo.de.bomb.graphics;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Andreas Hauschild
 */
public class TestMain {

    private static double dt;

    public static void main(String[] args) {

        paint();
    }

    private static void paint() {
        try {
            int width = 640, height = 480;

            // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
            // into integer pixels
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = bi.createGraphics();

            BaseObject obj = new BaseObject(50,50);
            BaseVector vector = new BaseVector(20, 20);
            BaseVector velocity = new BaseVector(1, 15);
            BaseVector gravity = new BaseVector(0, -3);
            dt = 0.1;
            for(int i = 0; i<120;i++){
//                drawVector(vector,50,50,g);
                vector.plus(velocity);

                velocity.plus(gravity.multiply(dt));
                g.fillOval((int)obj.getX(),(int)obj.getY(),10,10);
                obj.setX(obj.getX()+vector.getX()*dt);
                obj.setY(obj.getY()+vector.getY()*dt);
                System.out.println(obj.getX() + ", " +obj.getY());
//                                System.out.println(velocity.getX() + ", " +velocity.getY());
            }




            ImageIO.write(bi, "JPEG", new File("C:\\Temp\\yourImageName.JPG"));


        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    private static void  drawVector(BaseVector v ,int x, int y, Graphics2D g){

        BaseLine baseLine = new BaseLine(v, x, y);
        g.setPaint(Color.RED);
        g.drawLine((int)baseLine.getX1(),(int)baseLine.getY1(),(int)baseLine.getX2(),(int)baseLine.getY2());
        g.fillOval((int)baseLine.getX2(),(int)baseLine.getY2(),5,5);

    }
}
