package title;

import lombok.Getter;

import java.awt.image.BufferedImage;

public class Title {

    @Getter
    private BufferedImage image;

    @Getter
    private boolean collision;

    public Title(BufferedImage image, boolean collision) {
        this.image = image;
        this.collision = collision;
    }

    public Title(BufferedImage image) {
        this.image = image;
        this.collision = false;
    }



}
