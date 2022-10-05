package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Sword extends  SuperObject{

    public OBJ_Sword(int x, int y) {

        setName("Sword");
        setWorldX(x);
        setWorldY(y);

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/objects/sword.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void interact() {

    }
}
