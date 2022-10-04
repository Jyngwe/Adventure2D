package entity;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Entity {

    public int worldX;
    public int worldY;
    public int speed;

    @Getter
    @Setter
    private Rectangle solidArea;

    @Getter
    @Setter
    private int solidAreaDefaultX;

    @Getter
    @Setter
    private int solidAreaDefaultY;

    public boolean collisionOn = false;

    public Direction getDirection() {
        return null;
    }

    public Action getAction() {
        return null;
    }

}
