package entity;

import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

@Getter
public class EntityImage {

    @Setter
    public int spriteCounter = 0;
    @Setter
    public int spriteNr = 1;

    public BufferedImage currentImage;

    public List<BufferedImage> up = new ArrayList<>();
    public List<BufferedImage> down = new ArrayList<>();
    public List<BufferedImage> left = new ArrayList<>();
    public List<BufferedImage> right = new ArrayList<>();


    public EntityImage(String up1Path, String up2Path, String down1Path, String down2Path, String left1Path, String left2Path, String right1Path, String right2Path) throws IOException {
        up.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(up1Path))));
        up.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(up2Path))));

        down.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(down1Path))));
        down.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(down2Path))));

        left.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(left1Path))));
        left.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(left2Path))));

        right.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(right1Path))));
        right.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(right2Path))));
    }

    public boolean timeToUpdateSprite() {
        return spriteCounter++ > 12;
    }

    public void updateSprite(Direction direction) {
        switch (direction) {
            case UP:
                Collections.swap(up, 0, up.size() - 1);
                break;
            case DOWN:
                Collections.swap(down, 0, down.size() - 1);
                break;
            case LEFT:
                Collections.swap(left, 0, left.size() - 1);
                break;
            case RIGHT:
                Collections.swap(right, 0, right.size() - 1);
                break;
            case NONE:
                if (currentImage == null) {
                    currentImage = down.get(0);
                }
                break;
        }

        spriteCounter = 0;
    }

    public void updateDirection(Direction direction) {
        switch (direction) {
            case UP -> currentImage = up.get(0);
            case DOWN -> currentImage = down.get(0);
            case LEFT -> currentImage = left.get(0);
            case RIGHT -> currentImage = right.get(0);
            case NONE -> {
                if (currentImage == null) {
                    currentImage = down.get(0);
                }
            }
        }
    }
}
