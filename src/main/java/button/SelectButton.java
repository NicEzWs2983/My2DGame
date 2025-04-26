package button;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicBorders;

import panel.staticPanel.TitlePanel;
import setting.GameFrame;

public class SelectButton extends JComponent implements MouseListener {

    GameFrame gf;
    Graphics2D g2D;

    public boolean pressed = false;

    public String title = "button";
    public Font font;
    public Color fontColor = Color.WHITE;
    public Color boundColor = Color.WHITE;
    public BasicStroke basicStroke = new BasicStroke(1);
    public float size = 30;
    public int style = Font.PLAIN;
    public int arcWidth = 0;
    public int arcHeight = 0;

    public SelectButton(GameFrame gf) {
        this.setPreferredSize(new Dimension(150, 50));
        this.addMouseListener(this);
        this.setLayout(null);
    }

    public void setFont(Font font, int style, float size) {
        this.font = font;
        this.style = style;
        this.size = size;
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;
        this.g2D = g2D;

        g2D.setColor(boundColor);
        g2D.setStroke(basicStroke);
        g2D.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        g2D.setFont(font);
        g2D.setFont(g2D.getFont().deriveFont(style, size));
        g2D.setColor(fontColor);
        int textX = (getWidth() - (int) g2D.getFontMetrics().getStringBounds(title, g2D).getWidth()) / 2;
        int textY = (int) g2D.getFontMetrics().getStringBounds(title, g2D).getHeight() + getHeight() / 5;
        g2D.drawString(title, textX, textY);

        if (pressed) {
            g2D.setColor(new Color(250, 250, 250, 200));
            g2D.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
        basicStroke = new BasicStroke(3);
        // boundColor = Color.RED;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        basicStroke = new BasicStroke(1);
        // boundColor = Color.WHITE;
        repaint();
    }

}
