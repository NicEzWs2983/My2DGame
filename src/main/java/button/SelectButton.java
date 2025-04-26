package button;

import java.awt.Color;
import java.awt.event.MouseEvent;

import panel.OriginalPanel;

public class SelectButton extends MineButton {
    public OriginalPanel og;

    @Override
    public void mouseClicked(MouseEvent e) {
        og.gameState = og.optionUIState;
    }
}
