package com;

import com.GUI.Menu;
import com.LOGIC.ActionBase;
import java.io.IOException;


public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        ActionBase actions = new ActionBase();
        Menu.startMenu(actions);
    }
}
