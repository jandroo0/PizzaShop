package gui.tools;

import gui.config.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomMessageDialog extends JDialog {

    // panels
    JPanel titlePanel;
    JPanel messagePanel;
    JPanel buttonsPanel;

    // labels
    JLabel titleLabel;
    JLabel messageLabel;

    String titleString;
    String messageTitleString = "";
    String messageString = "";


    public CustomMessageDialog(Frame frame) {
        super(frame, "DANS SLICES", true);
        setSize(250, 150);
        setResizable(false);
        setLocationRelativeTo(frame);

        // panels
        titlePanel = new JPanel();
        messagePanel = new JPanel();
        buttonsPanel = new JPanel();

        // labels
        titleLabel = new JLabel(messageTitleString);
        messageLabel = new JLabel(messageString);

        titlePanel.add(titleLabel);
        messagePanel.add(messageLabel);

        styling();

        setLayout(new BorderLayout());

        add(titlePanel, BorderLayout.NORTH);
        add(messagePanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }


    public void errorDialog(String messageTitle, String message) {
        setTitle(messageTitle);
        titleLabel.setText(messageTitle);
        messageLabel.setText(message);

        Button okButton = new Button("OK", Utils.getTextFont(14), Utils.getTextColor(),
                Utils.getButtonBackgroundColor(), Utils.getButtonHoverColor(), BorderFactory.createEmptyBorder(5, 8, 5, 8));

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                buttonsPanel.remove(okButton);
            }
        });

        buttonsPanel.add(okButton);
        setVisible(true);
    }


    private void styling() {
        setBackground(Utils.getBackgroundColor());


        //title panel
        titlePanel.setBackground(Utils.getBackgroundColor());

        //title label
        titleLabel.setFont(Utils.getTextFont(24));
        titleLabel.setForeground(Utils.getTextColor());

        //message panel
        messagePanel.setBackground(Utils.getBackgroundColor());
        messageLabel.setFont(Utils.getTextFont(14));
        messageLabel.setForeground(Utils.getTextColor());

        // buttons panel
        buttonsPanel.setBackground(Utils.getBackgroundColor());


    }


}
