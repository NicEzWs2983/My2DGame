package setting;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2D = scaledImage.createGraphics();
        g2D.drawImage(original, 0, 0, width, height, null);
        g2D.dispose();
        return scaledImage;
    }
}
