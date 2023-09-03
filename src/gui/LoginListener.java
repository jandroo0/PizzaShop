package gui;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.EventListener;

public interface LoginListener extends EventListener {
    void loginEvent(LoginEvent e);

}
