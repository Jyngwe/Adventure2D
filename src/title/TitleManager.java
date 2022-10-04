package title;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class TitleManager {

    GamePanel gamePanel;
    public List<Title> titles = new ArrayList<>();
    public int mapTitleNumber[][];

    public TitleManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        mapTitleNumber = new int[GamePanel.MAX_WORLD_COL][GamePanel.MAX_WORLD_ROW];

        getTitleImage();
        loadMap("/maps/world01.txt");
    }

    public void getTitleImage() {
        try {
            titles.add(new Title(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/titles/grass1.png")))));
            titles.add(new Title(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/titles/grass2.png")))));
            titles.add(new Title(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/titles/grass3.png")))));
            titles.add(new Title(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/titles/water1.png"))), true));
            titles.add(new Title(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/titles/water2.png"))), true));
            titles.add(new Title(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/titles/water3.png"))), true));
            titles.add(new Title(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/titles/dirt1.png")))));
            titles.add(new Title(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/titles/tree1.png"))), true));
            titles.add(new Title(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/titles/rock1.png"))), true));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < GamePanel.MAX_WORLD_COL && row < GamePanel.MAX_WORLD_ROW) {

                String line = br.readLine();

                while (col < GamePanel.MAX_WORLD_COL) {

                    String[] numbers = line.split(" ");

                    String title = numbers[col];

                    int num = getTitle(title);

                    mapTitleNumber[col][row] = num;
                    col++;
                }
                if (col == GamePanel.MAX_WORLD_COL) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getTitle(String s) {
        return switch (s) {
            case "w" -> {
                Random random = new Random();
                yield 3 + random.nextInt(3);
            }
            case "g" -> {
                Random random = new Random();
                yield random.nextInt(3);
            }
            case "t" -> 7;
            case "r" -> 8;
            case "d" -> 6;
            default -> 0;

        };
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < GamePanel.MAX_WORLD_COL && worldRow < GamePanel.MAX_WORLD_ROW) {

            int tileNum = mapTitleNumber[worldCol][worldRow];

            int worldX = worldCol * GamePanel.TITLE_SIZE;
            int worldY = worldRow * GamePanel.TITLE_SIZE;
            int screenX = worldX - gamePanel.getPlayer().worldX + gamePanel.getPlayer().screenX;
            int screenY = worldY - gamePanel.getPlayer().worldY + gamePanel.getPlayer().screenY;

            if (worldX + GamePanel.TITLE_SIZE > gamePanel.getPlayer().worldX - gamePanel.getPlayer().screenX
                    && worldX - GamePanel.TITLE_SIZE < gamePanel.getPlayer().worldX + gamePanel.getPlayer().screenX
                    && worldY + GamePanel.TITLE_SIZE > gamePanel.getPlayer().worldY - gamePanel.getPlayer().screenY
                    && worldY - GamePanel.TITLE_SIZE < gamePanel.getPlayer().worldY + gamePanel.getPlayer().screenY) {

                g2.drawImage(titles.get(tileNum).getImage(), screenX, screenY, GamePanel.TITLE_SIZE, GamePanel.TITLE_SIZE, null);

            }
            worldCol++;

            if (worldCol == GamePanel.MAX_WORLD_COL) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

}
