package object.UIimage;

import object.SuperObject;
import setting.GameFrame;

public class WatchingSign extends SuperObject {

    public WatchingSign(GameFrame gf) {
        super(gf);
        name = "WatchingSign";
        imagePath = "/objects/UIimage/WatchingSigns.png";
        width = tileSize * 12;
        height = 32 * 12;
        objectX = (lv0.screenWidth - width) / 2;
        objectY = 2 * tileSize;
        loadImage();
    }

}
