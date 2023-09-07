package gui;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

@SuppressWarnings("serial")

public class PlaceholderTextField extends JTextField {

    private String placeholder;
    private int fontSize;


    public PlaceholderTextField(int width, int height, int fontSize) {

        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createLineBorder(Utils.getTextColor()));

        setHorizontalAlignment(JTextField.CENTER);
        setForeground(Utils.getTextColor());
        setBackground(Utils.getDefaultTextFieldColor());
        setFont(Utils.getTextFont(fontSize)); // set font with size
        setDocument(new JTextFieldLimit(14));

        this.fontSize = fontSize;


    }

    public PlaceholderTextField(final Document pDoc, final String pText, final int pColumns) {
        super(pDoc, pText, pColumns);
    }

    public PlaceholderTextField(final int pColumns) {
        super(pColumns);
    }

    public PlaceholderTextField(final String pText, int width, int height, int fontSize) {
        this(width, height, fontSize);

        this.placeholder = pText;
        }

    public PlaceholderTextField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    public String getPlaceholder() {
        return placeholder;
    }


    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Utils.getTextColor());
        g.setFont(Utils.getTextFont(fontSize));
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
                .getMaxAscent() + getInsets().top);
    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }

}