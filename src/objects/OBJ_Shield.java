package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Shield extends SuperObject{

    public OBJ_Shield(int x, int y) {

        name = "Shield";
        worldX = x;
        worldY = y;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/shield.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void interact() {

    }
}
