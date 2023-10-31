package org.hinoob.two2d;

public class XYBoundingBox {

    public int minX, minY;
    public int maxX, maxY;

    public XYBoundingBox(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public boolean intersects(XYBoundingBox other) {
        return other.maxX > this.minX && other.minX < this.maxX && other.maxY > this.minY && other.minY < this.maxY;
    }

    public String toString() {
        return "[minX=" + minX + ",maxX=" + maxX + ",minY=" + minY + ",maxY=" + maxY + "]";
    }
}
