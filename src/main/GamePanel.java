package main;

import entity.Player;
import lombok.Getter;
import objects.SuperObject;
import title.TitleManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    static final int ORIGINAL_TITLE_SIZE = 16; // Standard title size
    static final int SCALE = 3; // Also standard value

    public static final int TITLE_SIZE = ORIGINAL_TITLE_SIZE * SCALE; // 48x48 title
    public static final int MAX_SCREEN_COLUMN = 16;
    public static final int MAX_SCREEN_ROW = 12;

    public static final int SCREEN_WIDTH = TITLE_SIZE * MAX_SCREEN_COLUMN; // 768 px
    public static final int SCREEN_HEIGHT = TITLE_SIZE * MAX_SCREEN_ROW; // 576 px

    transient Thread gameThread;
    transient KeyHandler keyHandler = new KeyHandler();

    @Getter
    private Player player = new Player(this, keyHandler);
    @Getter
    private ArrayList<SuperObject> objects = new ArrayList<>();

    public TitleManager titleManager = new TitleManager(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public ActionChecker actionChecker = new ActionChecker(this);

    int FPS = 60;

    // World settings
    public static final int MAX_WORLD_COL = 50;
    public static final int MAX_WORLD_ROW = 50;
    public final int WORLD_WIDTH = TITLE_SIZE + MAX_WORLD_COL;
    public final int WORLD_HEIGHT = TITLE_SIZE + MAX_WORLD_ROW;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        runWithDeltaMethod();
    }

    private void runWithDeltaMethod() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void addObject(SuperObject object) {
        objects.add(object);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g2 = (Graphics2D) graphics;

        titleManager.draw(g2);

        for (SuperObject object : objects) {
            object.draw(g2, this);
        }

        player.draw(g2);
        g2.dispose();
    }

}
