package objects;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject implements Interactable {

    public BufferedImage image;
    public String name;
    public int worldX;
    public int worldY;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public boolean collission;

    public void draw(Graphics2D g2, GamePanel gamePanel) {

        int screenX = worldX - gamePanel.getPlayer().worldX + gamePanel.getPlayer().screenX;
        int screenY = worldY - gamePanel.getPlayer().worldY + gamePanel.getPlayer().screenY;

        if (worldX + GamePanel.TITLE_SIZE > gamePanel.getPlayer().worldX - gamePanel.getPlayer().screenX
                && worldX - GamePanel.TITLE_SIZE < gamePanel.getPlayer().worldX + gamePanel.getPlayer().screenX
                && worldY + GamePanel.TITLE_SIZE > gamePanel.getPlayer().worldY - gamePanel.getPlayer().screenY
                && worldY - GamePanel.TITLE_SIZE < gamePanel.getPlayer().worldY + gamePanel.getPlayer().screenY) {

            g2.drawImage(image, screenX, screenY, GamePanel.TITLE_SIZE, GamePanel.TITLE_SIZE, null);

        }
    }

}
