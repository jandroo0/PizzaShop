package gui;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.text.Document;

@SuppressWarnings("serial")

public class PlaceholderTextField extends JTextField {

    private String placeholder;

    private String placeHolderText;

    public PlaceholderTextField() {

    }

    public PlaceholderTextField(
            final Document pDoc,
            final String pText,
            final int pColumns)
    {
        super(pDoc, pText, pColumns);
    }

    public PlaceholderTextField(final int pColumns) {
        super(pColumns);
    }

    public PlaceholderTextField(final String pText) {
        super(pText);
        setPreferredSize(new Dimension(90, 24));
        setBorder(BorderFactory.createEmptyBorder());

        this.placeHolderText = pText;

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { // if textField is selected, and the current text still = the placeholderText, set empty
                if(getText().equals(pText)) {
                    setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) { // if textField is empty, when user clicks off, set to placeholderText
                if(getText().equals("")) {
                    setText(pText);
                }

            }
        });


    }

    public PlaceholderTextField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public String getPlaceHolderText() {
        return placeHolderText;
    }

    public void setPlaceHolderText(String placeHolderText) {
        this.placeHolderText = placeHolderText;
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
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
                .getMaxAscent() + getInsets().top);
    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }

}