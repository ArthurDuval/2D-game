package object;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class OBJ_mask extends SuperObject {
    public OBJ_mask() {
        name = "mask";
        try {
            image = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/objects/mask.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}