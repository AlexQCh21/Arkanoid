package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameIntroPanel extends JPanel {

    protected final String[] menuText = {"Iniciar","Puntaje","Salir"};
    protected int selectedItem = 0;
    private Mediator mediator;
    public GameIntroPanel(JFrame mediator){
        this.mediator=(Mediator)mediator;
        this.setVisible(true);
        this.setPreferredSize(new Dimension(GamePanel.widthSize,GamePanel.heightSize));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new MyKey());

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Font f = new Font("Times New Roman",Font.BOLD,30);
        FontMetrics fm = g.getFontMetrics();
        g.setFont(f);
        g.setColor(Color.white);
        for(int i = 0; i<menuText.length;i++){
            int x = (GamePanel.widthSize - fm.stringWidth(menuText[i]))/2-20;
            int y = fm.getHeight()+150+(i*(fm.getHeight()+30));
            g.drawString(menuText[i],x,y);
            if(selectedItem == i){
                drawCircle(x-60,y-30,g);
            }
        }

    }

    public void drawCircle(int x, int y, Graphics g){
        Graphics2D gd = (Graphics2D)g;
        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gd.fillOval(x,y,30,30);
    }

    public class MyKey extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent evt){
            switch (evt.getKeyCode()) {
                case KeyEvent.VK_UP -> {
                    decrementItem();
                    repaint();
                }
                case KeyEvent.VK_DOWN -> {
                    incrementItem();
                    repaint();
                }
                case KeyEvent.VK_ENTER ->{
                    if(selectedItem==0) {
                            mediator.initGame();
                        }else if(selectedItem==1){

                        }else {
                            System.exit(0);
                        }
                }
            }
        }
    }

    public void incrementItem(){
        int numItem = menuText.length-1;
        if(selectedItem < numItem){
            selectedItem++;
        }else{
            selectedItem = 0;
        }
    }

    public void decrementItem(){
        int numItem = menuText.length-1;
        if(selectedItem > 0){
            selectedItem--;
        }else{
            selectedItem = numItem;
        }
    }

}
