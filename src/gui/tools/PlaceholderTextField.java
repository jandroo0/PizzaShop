package gui.tools;

import gui.config.Utils;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

@SuppressWarnings("serial")

public class PlaceholderTextField extends JTextField {

    private String placeholder;
    private int fontSize;



    // textField with set width, height, and fontSize ---- DEFAULT
    public PlaceholderTextField(int width, int height, int fontSize) {

        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createLineBorder(Utils.getTextColor()));

        setHorizontalAlignment(JTextField.CENTER);
        setForeground(Utils.getTextColor());
        setBackground(Utils.getTextFieldColor());
        setFont(Utils.getTextFont(fontSize)); // set font with size
        setDocument(new JTextFieldLimit(14));

        this.fontSize = fontSize;

    }

    // placeholder
    public PlaceholderTextField(final String pText, int width, int height, int fontSize) {
        this(width, height, fontSize);

        this.placeholder = pText;
    }

    // text limit
    public PlaceholderTextField(int width, int height, int fontSize, int limit) {
        this(width, height, fontSize);
        setDocument(new JTextFieldLimit(limit));

    }

    // placeholder and limit
    public PlaceholderTextField(final String pText, int width, int height, int fontSize, int limit) {
        this(width, height, fontSize);
        setDocument(new JTextFieldLimit(limit));

        this.placeholder = pText;
    }

//    int filter and limit
    public PlaceholderTextField(int width, int height, int fontSize, boolean intFilter, int limit) {
        this(width, height, fontSize);
        if(intFilter) {
            PlainDocument doc = (PlainDocument) getDocument();
            doc.setDocumentFilter(new MyIntFilter(limit));
        }

    }

    // placeholder, int filter, and limit
    public PlaceholderTextField(final String pText, int width, int height, int fontSize,boolean intFilter, int limit ) {
        this(width, height, fontSize);
        if(intFilter) {
            PlainDocument doc = (PlainDocument) getDocument();
            doc.setDocumentFilter(new MyIntFilter(limit));
        }

        this.placeholder = pText;

    }




    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(final String s) {
        placeholder = s;
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

    /* Limits text to only INT and to a set limit of characters*/
    public static class MyIntFilter extends DocumentFilter {

        private final int limit;

        public MyIntFilter(int limit) {
            super();
            this.limit = limit;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string,
                                 AttributeSet attr) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, string);


            if (test(sb.toString())) {
                super.insertString(fb, offset, string, attr);
            } else {
                // warn the user and don't allow the insert
            }
        }

        private boolean test(String text) {

            try {
                if (text.length() <= limit) {
                    Long.parseLong(text);
                    return true;
                }
                return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text,
                            AttributeSet attrs) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);

            if (test(sb.toString())) {
                super.replace(fb, offset, length, text, attrs);
            } else {
                // warn the user and don't allow the insert
            }

        }

        @Override
        public void remove(FilterBypass fb, int offset, int length)
                throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.delete(offset, offset + length);

            if (sb.toString().length() == 0) {
                super.replace(fb, offset, length, "", null);
            } else {
                if (test(sb.toString())) {
                    super.remove(fb, offset, length);
                } else {                // warn the user and don't allow the insert 		    }      }

                }
            }
        }

    }

    // class to set limit for idField from: https://stackoverflow.com/questions/3519151/how-to-limit-the-number-of-characters-in-jtextfield
    static class JTextFieldLimit extends PlainDocument {
        private final int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
}


