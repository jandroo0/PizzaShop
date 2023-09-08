package gui.title;

import gui.config.Utils;
import gui.tools.Label;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel { // JPanel containing the label for application title

    private final gui.tools.Label titleLabel; // declare JLabel for application name

    public TitlePanel() {
        titleLabel = new Label("DANS SLICES", Utils.getTitleColor(), 50); // initialize title label

        // center JLabel, container panel is already at the top of the frame so no need to center vertically
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        setLayout(new BorderLayout()); // set layout of this panel to a BorderLayout
        setBackground(Utils.getBackgroundColor());

        setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // set empty border

        add(titleLabel, BorderLayout.CENTER); // center label

    }
}
