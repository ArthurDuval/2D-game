package object;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class OBJ_cup extends SuperObject {
    public OBJ_cup() {
        name = "cup";
        try {
            image = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/objects/cup.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}