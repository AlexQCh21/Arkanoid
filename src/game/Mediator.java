package game;

import java.awt.*;

public class Mediator extends javax.swing.JFrame{
    private GameIntroPanel gameIntroPanel = new GameIntroPanel(this);
    private GamePanel gpanel = new GamePanel(this);
    private boolean flag=true;

    public Mediator(){
        this.setLayout(new CardLayout());
        this.setUndecorated(true);
        this.add(gameIntroPanel);
        this.pack();
        this.setSize(GamePanel.widthSize,GamePanel.heightSize);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(gpanel);

    }

    public void initGame(){
        gameIntroPanel.setVisible(false);
        gpanel.setVisible(true);
        if(flag) {
            gpanel.start();
            flag=false;
        }
    }

    public void goPanelSettings(){
        gameIntroPanel.setVisible(true);
        gpanel.setVisible(false);
        gpanel.initPoints();
        gpanel.initValues();
    }


}
