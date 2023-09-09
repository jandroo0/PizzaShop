package gui.config;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Utils {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 1000;

    //	colors
    private static final Color titleColor = new Color(47, 39, 31);
    private static final Color textColor = new Color(47, 39, 31);
    private static final Color backgroundColor = new Color(189, 174, 134);
    private static final Color buttonHoverColor = new Color(232, 191, 139);
    private static final Color buttonBackgroundColor = new Color(251, 251, 212);
    private static final Color navButtonSelectedColor = new Color(232, 191, 139);
    private static final Color borderColor = new Color(107, 116, 120);
    private static final Color textFieldColor = new Color(204, 222, 211);

    // border
    private static final Border buttonBorder = BorderFactory.createEmptyBorder(6, 9, 6, 9);


    //	fonts
    private static final String fontString = "Serif";
    private static final Font textFont = new Font("Serif", Font.BOLD, 16);

    public static String getFontString() {
        return fontString;
    }

    public static Color getTitleColor() {
        return titleColor;
    }

    public static Color getTextColor() {
        return textColor;
    }

    public static Color getBackgroundColor() {
        return backgroundColor;
    }

    public static Font getTextFont() {
        return textFont;
    }

    public static Font getTextFont(int size) {
        return new Font(textFont.getFontName(), textFont.getStyle(), size);
    }


    public static Color getButtonHoverColor() {
        return buttonHoverColor;
    }

    public static Color getButtonBackgroundColor() {
        return buttonBackgroundColor;
    }

    public static Color getNavButtonSelectedColor() {
        return navButtonSelectedColor;
    }

    public static Color getDefaultBorderColor() {
        return borderColor;
    }

    public static Color getTextFieldColor() {
        return textFieldColor;
    }

    public static Border getButtonBorder() {
        return buttonBorder;
    }


//    public static String getFileExtension(String name) {
//        int pointIndex = name.lastIndexOf(".");
//        if (pointIndex == -1) {
//            return null;
//        } else {
//            return pointIndex == name.length() - 1 ? null : name.substring(pointIndex + 1);
//        }
//    }

}
