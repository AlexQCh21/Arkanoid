package game;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //new GamePanel();

        JFrame jframe = new JFrame("Panel Settings");
        jframe.setSize(GamePanel.widthSize,GamePanel.heightSize);
        jframe.setUndecorated(true);
        jframe.setVisible(true);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.add(new GameIntroPanel());
    }
}