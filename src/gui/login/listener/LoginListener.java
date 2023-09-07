package gui.login.listener;

import gui.login.event.LoginEvent;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.EventListener;

public interface LoginListener extends EventListener {
    void loginEvent(LoginEvent e) throws ParseException, IOException;


}
