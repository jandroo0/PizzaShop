package gui;

import java.io.IOException;
import java.util.EventListener;

public interface CreateAccountListener extends EventListener {

    void createAccount(CreateAccountEvent e) throws IOException;
}
