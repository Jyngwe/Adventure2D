package main;

import objects.OBJ_Axe;
import objects.OBJ_Shield;
import objects.OBJ_Sword;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {

        gamePanel.addObject(new OBJ_Axe( 23 * GamePanel.TITLE_SIZE, 7 * GamePanel.TITLE_SIZE));
        gamePanel.addObject(new OBJ_Shield(21 * GamePanel.TITLE_SIZE, 18 * GamePanel.TITLE_SIZE));
        gamePanel.addObject(new OBJ_Sword( 21 * GamePanel.TITLE_SIZE, 17 * GamePanel.TITLE_SIZE));
    }
}
