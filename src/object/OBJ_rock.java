package object;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class OBJ_rock extends SuperObject {
    public OBJ_rock() {
        name = "rock";
        try {
            image = ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/res/objects/itemRockSmash.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
