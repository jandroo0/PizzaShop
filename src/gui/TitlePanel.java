package gui;

import javax.swing.*;

import java.awt.BorderLayout;

public class TitlePanel extends JPanel { // JPanel containing the label for application title

    private JLabel titleLabel; // declare JLabel for application name

    public TitlePanel() {
        titleLabel = new JLabel("DANS SLICES"); // initialize JLabel

        // center JLabel, container panel is already at the top of the frame so no need to center vertically
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Utils.getTitleColor()); // set font color, retrieved from gui.Utils class
        titleLabel.setFont(Utils.getTitleFont());

        setLayout(new BorderLayout()); // set layout of this panel to a BorderLayout
        setBackground(Utils.getBackgroundColor());

        setBorder(BorderFactory.createEmptyBorder(40,0,0,0)); // set empty border

        add(titleLabel, BorderLayout.CENTER); // center label

    }
}
