package main;

import entity.Entity;

public class ActionChecker {

    GamePanel gamePanel;

    public ActionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTitle(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

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


}
