package main;

import entity.Entity;
import objects.SuperObject;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTitle(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.getSolidArea().x;
        int entityRightWorldX = entity.worldX + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.worldY + entity.getSolidArea().y;
        int entityBottomWorldY = entity.worldY + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / GamePanel.TITLE_SIZE;
        int entityRightCol = entityRightWorldX / GamePanel.TITLE_SIZE;
        int entityTopRow = entityTopWorldY / GamePanel.TITLE_SIZE;
        int entityBottomRow = entityBottomWorldY / GamePanel.TITLE_SIZE;

        int tileNum1;
        int tileNum2;

        switch (entity.getDirection()) {
            case NONE -> {
                // Do nothing
            }
            case UP -> {
                entityTopRow = (entityTopWorldY - entity.speed) / GamePanel.TITLE_SIZE;
                tileNum1 = gamePanel.titleManager.mapTitleNumber[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.titleManager.mapTitleNumber[entityRightCol][entityTopRow];
                if (gamePanel.titleManager.titles.get(tileNum1).isCollision() || gamePanel.titleManager.titles.get(tileNum2).isCollision()) {
                    entity.collisionOn = true;
                }
            }
            case DOWN -> {
                entityBottomRow = (entityBottomWorldY - entity.speed) / GamePanel.TITLE_SIZE;
                tileNum1 = gamePanel.titleManager.mapTitleNumber[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.titleManager.mapTitleNumber[entityRightCol][entityBottomRow];
                if (gamePanel.titleManager.titles.get(tileNum1).isCollision() || gamePanel.titleManager.titles.get(tileNum2).isCollision()) {
                    entity.collisionOn = true;
                }
            }
            case LEFT -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / GamePanel.TITLE_SIZE;
                tileNum1 = gamePanel.titleManager.mapTitleNumber[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.titleManager.mapTitleNumber[entityLeftCol][entityBottomRow];
                if (gamePanel.titleManager.titles.get(tileNum1).isCollision() || gamePanel.titleManager.titles.get(tileNum2).isCollision()) {
                    entity.collisionOn = true;
                }
            }
            case RIGHT -> {
                entityRightCol = (entityRightWorldX - entity.speed) / GamePanel.TITLE_SIZE;
                tileNum1 = gamePanel.titleManager.mapTitleNumber[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.titleManager.mapTitleNumber[entityRightCol][entityBottomRow];
                if (gamePanel.titleManager.titles.get(tileNum1).isCollision() || gamePanel.titleManager.titles.get(tileNum2).isCollision()) {
                    entity.collisionOn = true;
                }
            }
        }

    }

    public int checkObject(Entity entity, boolean player) {
        int index = -1;

        for (final SuperObject object : gamePanel.getObjects()) {
            entity.getSolidArea().x = entity.worldX + entity.getSolidArea().x;
            entity.getSolidArea().y = entity.worldY + entity.getSolidArea().y;

            object.getSolidArea().x = object.getWorldX() + object.getSolidArea().x;
            object.getSolidArea().y = object.getWorldY() + object.getSolidArea().y;

            switch (entity.getDirection()) {
                case NONE -> {
                }
                case UP -> {
                    entity.getSolidArea().y -= entity.speed;
                    if (entity.getSolidArea().intersects(object.getSolidArea())) {
                        if (object.collission) {
                            entity.collisionOn = true;
                        }
                        if (player) {
                            index = gamePanel.getObjects().indexOf(object);
                        }
                    }
                }
                case DOWN -> {
                    entity.getSolidArea().y += entity.speed;
                    if (entity.getSolidArea().intersects(object.getSolidArea())) {
                        if (entity.getSolidArea().intersects(object.getSolidArea())) {
                            if (object.collission) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = gamePanel.getObjects().indexOf(object);
                            }
                        }
                    }
                }
                case LEFT -> {
                    entity.getSolidArea().x -= entity.speed;
                    if (entity.getSolidArea().intersects(object.getSolidArea())) {
                        if (entity.getSolidArea().intersects(object.getSolidArea())) {
                            if (object.collission) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = gamePanel.getObjects().indexOf(object);
                            }
                        }
                    }
                }
                case RIGHT -> {
                    entity.getSolidArea().x += entity.speed;
                    if (entity.getSolidArea().intersects(object.getSolidArea())) {
                        if (object.collission) {
                            entity.collisionOn = true;
                        }
                        if (player) {
                            index = gamePanel.getObjects().indexOf(object);
                        }
                    }
                }
            }
            entity.getSolidArea().x = entity.getSolidAreaDefaultX();
            entity.getSolidArea().y = entity.getSolidAreaDefaultY();
            object.getSolidArea().x = object.getSolidAreaDefaultX();
            object.getSolidArea().y = object.getSolidAreaDefaultY();

        }

        return index;
    }
}
