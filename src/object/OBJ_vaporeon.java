package object;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class OBJ_vaporeon extends SuperObject {
    public OBJ_vaporeon() {
        name = "vaporeon";
        try {
            image = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/objects/vaporeon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
