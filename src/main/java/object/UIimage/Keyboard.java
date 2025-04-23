package object.UIimage;

import object.SuperObject;
import setting.GameFrame;

public class Keyboard extends SuperObject {

    public Keyboard(GameFrame gf) {
        super(gf);
        name = "Keyboard";
        imagePath = "/objects/UIimage/keyboard.png";
        loadImage();
    }
}
