package entity;

import main.GamePanel;
import main.KeyHandler;
import objects.SuperObject;

import java.awt.*;
import java.io.IOException;

public class Player extends Entity implements LivingEntity {

    Inventory inventory;

    public final int screenX;
    public final int screenY;
    GamePanel gamePanel;
    KeyHandler keyHandler;
    EntityImage image = null;


    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.inventory = new Inventory();

        screenX = GamePanel.SCREEN_WIDTH/2 - (GamePanel.TITLE_SIZE/2);
        screenY = GamePanel.SCREEN_HEIGHT/2 - (GamePanel.TITLE_SIZE/2);

        setSolidArea(new Rectangle(8, 16, 32, 32));

        setDefaultValues();
        getImages();
    }

    @Override
    public void setDefaultValues() {
        worldX = GamePanel.TITLE_SIZE * 23;
        worldY = GamePanel.TITLE_SIZE * 21;
        speed = 4;
    }

    @Override
    public void getImages() {
        try {
            image = new EntityImage("/player/player_up_1.png", "/player/player_up_2.png", "/player/player_down_1.png", "/player/player_down_2.png", "/player/player_left_1.png", "/player/player_left_2.png", "/player/player_right_1.png", "/player/player_right_2.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        collisionOn = false;
        gamePanel.collisionChecker.checkTitle(this);
        int objectIndex = gamePanel.collisionChecker.checkObject(this, true);

        if (!collisionOn) {
            switch (keyHandler.getDirection()) {
                case UP -> worldY -= speed;
                case DOWN -> worldY += speed;
                case LEFT -> worldX -= speed;
                case RIGHT -> worldX += speed;
            }
        }
        if (getAction() == Action.PICKUP) {
            pickUpObject(objectIndex);
        }
    }

    public void draw(Graphics2D g) {

        if (image.timeToUpdateSprite()) {
            image.updateSprite(keyHandler.getDirection());
        }

        if (keyHandler.isDirectionChanged()) {
            image.updateDirection(keyHandler.getDirection());
        }

        g.drawImage(image.getCurrentImage(), screenX, screenY, GamePanel.TITLE_SIZE, GamePanel.TITLE_SIZE, null);


        if (inventory.getLeftHand() != null) {
            g.drawImage(inventory.getLeftHand().image, screenX - 20, screenY, GamePanel.TITLE_SIZE, GamePanel.TITLE_SIZE, null);
        }

        if (inventory.getRightHand() != null) {
            g.drawImage(inventory.getRightHand().image, screenX + 20, screenY + 15, GamePanel.TITLE_SIZE, GamePanel.TITLE_SIZE, null);
        }
    }

    @Override
    public Direction getDirection() {
        return keyHandler.getDirection();
    }
    @Override
    public Action getAction() {
        return keyHandler.getAction();
    }

    public void pickUpObject(int index) {
        if (index != -1) {
            final SuperObject object = gamePanel.getObjects().get(index);

            if (inventory.getLeftHand() == null) {
                inventory.setLeftHand(object);
            } else if (inventory.getRightHand() == null) {
                inventory.setRightHand(object);
            } else {
                inventory.setLeftHand(object);
            }

            gamePanel.getObjects().remove(object);
        }
    }

}
