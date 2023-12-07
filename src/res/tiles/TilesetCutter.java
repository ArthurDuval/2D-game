import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TilesetCutter {

    public static void main(String[] args) {
        // Chemin vers le tileset
        String tilesetPath = "/home/arthur/Pictures/tileset.png";

        // Taille d'un tile en pixels
        int tileSize = 32;

        try {
            // Charger le tileset
            BufferedImage tileset = ImageIO.read(new File(tilesetPath));

            // Dimensions du tileset
            int tilesetWidth = tileset.getWidth();
            int tilesetHeight = tileset.getHeight();
            int index = 0;

            // Découper le tileset en tiles de 32x32 pixels
            for (int y = 0; y < tilesetHeight; y += tileSize) {
                for (int x = 0; x < tilesetWidth; x += tileSize) {
                    // Créer un rectangle de sélection pour chaque tile
                    BufferedImage tile = tileset.getSubimage(x, y, tileSize, tileSize);

                    // Sauvegarder chaque tile dans un fichier séparé
                    ImageIO.write(tile, "png", new File(index + ".png"));
                    index++;
                }
            }

            System.out.println("Découpage du tileset terminé.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

