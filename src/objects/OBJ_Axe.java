package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Axe extends SuperObject{

    public OBJ_Axe(int x, int y) {

        setName("Axe");
        setWorldX(x);
        setWorldY(y);

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/objects/axe.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void interact() {

    }
}
