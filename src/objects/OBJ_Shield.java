package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Shield extends SuperObject{

    public OBJ_Shield(int x, int y) {

        setName("Shield");
        setWorldX(x);;
        setWorldY(y);;

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/objects/shield.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void interact() {

    }
}
