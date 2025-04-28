package button;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class MineButton extends JComponent implements MouseListener, ActionListener {

    public Graphics2D g2D;

    public boolean pressed = false;

    public String text = "button";
    public Font font;
    public Color fontColor = Color.WHITE;
    public Color boundColor = Color.WHITE;
    public Color backgroundColor = null;

    public BasicStroke enteredStroke = new BasicStroke(3);
    public BasicStroke exitedStroke = new BasicStroke(2);
    public BasicStroke basicStroke = exitedStroke;

    public float size = 30;
    public int style = Font.PLAIN;
    public int arcWidth = 0;
    public int arcHeight = 0;
    public int textX = 99999, textY = 99999;

    private OnClickListener onClickListener;

    public MineButton() {
        setDefault();
    }

    public void setOnClick(OnClickListener listener) {
        // 把 onClickListener 替換成有功能的
        this.onClickListener = listener;
    }

    public void setDefault() {
        this.setPreferredSize(new Dimension(150, 50));
        this.addMouseListener(this);
        this.setLayout(null);
    }

    public void setText(Color fontColor, int style, float size, int arcWidth, int arcHeight) {
        this.fontColor = fontColor;
        this.style = style;
        this.size = size;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLanguage(String[][] text, Font font) {
        this.text = text[0][0];
        this.font = font;
        this.textX = 99999;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        this.g2D = g2D;

        if (backgroundColor != null) {
            g2D.setColor(backgroundColor);
            g2D.fillRoundRect(3, 3, getWidth() - 6, getHeight() - 6, arcWidth, arcHeight);
        }

        g2D.setColor(boundColor);
        g2D.setStroke(basicStroke);
        g2D.drawRoundRect(3, 3, getWidth() - 6, getHeight() - 6, arcWidth, arcHeight);

        g2D.setFont(font);

        g2D.setFont(g2D.getFont().deriveFont(style, size));
        g2D.setColor(fontColor);

        if (textX == 99999) {
            textX = (getWidth() - (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth()) / 2;
        }
        if (textY == 99999) {
            textY = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getHeight() + getHeight() / 5;
        }

        g2D.drawString(text, textX, textY);

        if (pressed) {
            g2D.setColor(new Color(250, 250, 250, 200));
            g2D.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (onClickListener != null) {
            onClickListener.OnClick();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        basicStroke = enteredStroke;
        // boundColor = Color.RED;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        basicStroke = exitedStroke;
        // boundColor = Color.WHITE;
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @FunctionalInterface
    public interface OnClickListener {
        void OnClick();
    }
}
