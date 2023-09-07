package gui.dialog.employee.management;


import gui.MainFrame;
import gui.tools.TextPaneUtil;
import gui.config.Utils;
import model.Employee;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;
import java.awt.*;
import java.util.LinkedList;

public class EmployeesTextPanel extends JPanel {

    private final JTextPane textPane;
    private final JLabel titleLabel;
    private final StyledDocument doc;
    private MainFrame frame;
    private LinkedList<Employee> employees;
    private Style style;

    public EmployeesTextPanel() {

        setPreferredSize(new Dimension(240, 180));
        setBackground(Utils.getBackgroundColor());
        setLayout(new BorderLayout());

//		text area
        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setFocusable(false);
        textPane.setBorder(null);
        textPane.setBackground(Utils.getBackgroundColor());

        titleLabel = new JLabel("Employees");


//		styling

        //center text

//		SimpleAttributeSet center = new SimpleAttributeSet();
//		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
//		doc.setParagraphAttributes(0, doc.getLength(), center, true);

        // from: http://www.java2s.com/Tutorials/Java/Swing_How_to/JTextPane/Create_JEditorPane_vertical_alignment.htm
        textPane.setEditorKit(new MyEditorKit());
        doc = textPane.getStyledDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_CENTER);
        StyledDocument doc = (StyledDocument) textPane.getDocument();
        doc.setParagraphAttributes(0, doc.getLength() - 1, attrs, false);

//		font
        setJTextPaneFont(textPane, Utils.getTextFont(), Utils.getTextColor(), Utils.getBackgroundColor());
        titleLabel.setFont(Utils.getTextFont());

//		border
        Border outerBorder = BorderFactory.createEmptyBorder(0, 30, 0, 30);
        setBorder(outerBorder);

        Border titleBorder = BorderFactory.createEmptyBorder(0, 0, 10, 0);
        titleLabel.setBorder(titleBorder);

        titleLabel.setHorizontalAlignment(JLabel.CENTER);


        add(new JScrollPane(textPane), BorderLayout.CENTER);
        add(titleLabel, BorderLayout.NORTH);
    }

    // from http://javatechniques.com/blog/setting-jtextpane-font-and-color/
    public static void setJTextPaneFont(JTextPane jtp, Font font, Color c, Color bg) {
        // Start with the current input attributes for the JTextPane. This
        // should ensure that we do not wipe out any existing attributes
        // (such as alignment or other paragraph attributes) currently
        // set on the text area.
        MutableAttributeSet attrs = jtp.getInputAttributes();

        // Set the font family, size, and style, based on properties of
        // the Font object. Note that JTextPane supports a number of
        // character attributes beyond those supported by the Font class.
        // For example, underline, strike-through, super- and sub-script.
        StyleConstants.setFontFamily(attrs, font.getFamily());
        StyleConstants.setFontSize(attrs, font.getSize());
        StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);

        // Set the font color
        StyleConstants.setForeground(attrs, c);

        // set background
        StyleConstants.setBackground(attrs, bg);

        // Retrieve the pane's document object
        StyledDocument doc = jtp.getStyledDocument();

        // Replace the style for the entire document. We exceed the length
        // of the document by 1 so that text entered at the end of the
        // document uses the attributes.
        doc.setCharacterAttributes(0, doc.getLength() + 1, attrs, false);
    }

    public void displayEmployees(LinkedList<Employee> employees) {
        StringBuilder employeeList = new StringBuilder();

        for (Employee employee : employees) {
            employeeList.append(employee.getID() + " - " + employee.getFirstName());
            if (employee.isAdmin()) {
                employeeList.append("*");
            }
            employeeList.append("\n");
        }
        textPane.setText(new String(employeeList));
    }

    public int getLineCount() {
        return TextPaneUtil.getLines(textPane);
    }


}


class MyEditorKit extends StyledEditorKit {

    public ViewFactory getViewFactory() {
        return new StyledViewFactory();
    }

    static class StyledViewFactory implements ViewFactory {

        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {

                    return new LabelView(elem);
                } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new ParagraphView(elem);
                } else if (kind.equals(AbstractDocument.SectionElementName)) {

                    return new CenteredBoxView(elem, View.Y_AXIS);
                } else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem);
                } else if (kind.equals(StyleConstants.IconElementName)) {

                    return new IconView(elem);
                }
            }

            return new LabelView(elem);
        }

    }
}

class CenteredBoxView extends BoxView {
    public CenteredBoxView(Element elem, int axis) {

        super(elem, axis);
    }

    protected void layoutMajorAxis(int targetSpan, int axis, int[] offsets,
                                   int[] spans) {

        super.layoutMajorAxis(targetSpan, axis, offsets, spans);
        int textBlockHeight = 0;
        int offset = 0;

        for (int i = 0; i < spans.length; i++) {
            textBlockHeight += spans[i];
        }
        offset = (targetSpan - textBlockHeight) / 2;
        for (int i = 0; i < offsets.length; i++) {
            offsets[i] += offset;
        }

    }
}

