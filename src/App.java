import gui.MainFrame;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.IOException;

public class App {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MainFrame();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}