package gui;

import java.awt.*;

public class Utils {
	
//	colors
	private static Color titleColor = new Color(47, 39, 31);
	private static Color textColor = new Color(47, 39, 31);
	private static Color backgroundColor = new Color(189, 174, 134);
	private static Color buttonHoverColor = new Color(232,191,139);
	private static Color buttonBackgroundColor = new Color(251,251,212);
	private static Color borderColor = new Color(107, 116, 120);
	private static Color textFieldColor = new Color(204, 222, 211);
	private static Color textFieldHighlight = new Color(180, 213, 254);
	
//	fonts
	private static String fontString = "Serif";
	private static Font textFont = new Font("Serif", Font.PLAIN, 16);
	private static Font titleFont = new Font("Serif", Font.BOLD, 30); // main application title
	private static Font loginFont = new Font("Serif", Font.BOLD, 24); // login screen
	private static Font dialogFont;
	
//	font sizes
	private static int textSize = 12;
	private static int titleSize = 25;
	private static int dialogSize = 11;
	
	public static void setDefaults(Color backgroundColor, Font selectedFont) {
		Utils.backgroundColor = backgroundColor;
		
		Utils.textFont = new Font(selectedFont.getFontName(), Font.BOLD, textSize);
		Utils.titleFont = new Font(selectedFont.getFontName(), Font.BOLD, titleSize);
		Utils.dialogFont = new Font(selectedFont.getFontName(), Font.PLAIN, dialogSize);
		
		// add more when options are added...
	}
	
	public static void changeFont(String selectedFont) {
		Utils.textFont = new Font(selectedFont, Font.BOLD, textSize);
		Utils.titleFont = new Font(selectedFont, Font.BOLD, titleSize);
		Utils.dialogFont = new Font(selectedFont, Font.PLAIN, dialogSize);
	}
	
	
	// setters
	public static void setTextFieldHighlightColor(Color color) {
		Utils.textFieldHighlight = color;
	}
	public static void setTextColor(Color textColor) {
		Utils.textColor = textColor;
	}
	public static void setBackgroundColor(Color backgroundColor) {
		Utils.backgroundColor = backgroundColor;
	}
	public static void setButtonHoverColor(Color buttonHoverColor) {
		Utils.buttonHoverColor = buttonHoverColor;
	}
	public static void setDefaultBorderColor(Color defaultBorderColor) {
		Utils.borderColor = defaultBorderColor;
	}
	public static void setDefaultTextFieldColor(Color defaultTextFieldColor) {
		Utils.textFieldColor = defaultTextFieldColor;
	}
	public static void setDefaultFont(Font defaultFont) {
		Utils.textFont = defaultFont;
	}
	public static void setDefaultTitleFont(Font defaultTitleFont) {
		Utils.titleFont = defaultTitleFont;
	}
	public static void setDialogFont(Font dialogFont) {
		Utils.dialogFont = dialogFont;
	}
	
	
	// getters

	public static String getFontString() {
		return fontString;
	}
	public static Color getTextFieldHighlight() {
		return textFieldHighlight;
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
	
	public static Font getDefaultFont() {
		return textFont;
	}
	
	public static Font getDialogFont() {
		return dialogFont;
	}
	public static Font getTitleFont() {
		return titleFont;
	}
	public static Font getLoginFont() {
		return loginFont;
	}

	public static Color getButtonHoverColor() {
		return buttonHoverColor;
	}
	public static Color getButtonBackgroundColor() {
		return buttonBackgroundColor;
	}

	public static Color getDefaultBorderColor() {
		return borderColor;
	}
	
	public static Font getDefaultTitleFont() {
		return titleFont;
	}
	
	public static Color getDefaultTextFieldColor() {
		return textFieldColor;
	}

	public static String getFileExtension(String name) {
		int pointIndex = name.lastIndexOf(".");
		if (pointIndex == -1) {
			return null;
		} else {
			return pointIndex == name.length() - 1 ? null : name.substring(pointIndex + 1, name.length());
		}
	}
	
}
