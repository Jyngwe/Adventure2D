package entity;

import java.awt.*;

public class Entity {

    public int worldX;
    public int worldY;
    public int speed;

    public Rectangle solidArea;
    public boolean collisionOn = false;

    public Direction getDirection() {
        return null;
    }

    public Action getAction() {
        return null;
    }

}
