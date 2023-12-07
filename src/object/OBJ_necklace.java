package object;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class OBJ_necklace extends SuperObject {
    public OBJ_necklace() {
        name = "necklace";
        try {
            image = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/objects/necklace.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}