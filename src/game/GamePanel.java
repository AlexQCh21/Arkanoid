package game;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

public class GamePanel extends java.awt.Canvas implements Runnable{
    protected MyKeyEvent keys = new MyKeyEvent();
    public final int sizeBall = 10;
    protected Canvas canvas;
    public static final int widthSize = 600;
    public static final int heightSize = 500;
    protected boolean runner = false, runner1 = false;
    private Thread hilo;
    public int x = (widthSize - sizeBall)/2;
    public int y = 350;
    protected final int sizeR = 90;
    protected int xr = (widthSize - sizeR)/2;
    protected final int yr = 480;
    protected final Fisica fs = new Fisica();
    protected final int FPS = 60;
    protected double TARGETTIME = 1000000000/FPS;
    protected double delta = 0;
    protected int AVGFPS = FPS;
    protected int xm, ym;
    protected byte[][] rectPoints = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
    protected Point[][] points = new Point[15][28];
    protected int initPX = 20;
    protected int initPY = 40;
    protected final int sizeRectW = 20, sizeRectH = 20;
    private Mediator mediator;
    public GamePanel(javax.swing.JFrame frame){
        mediator = (Mediator)frame;
        /*
        this.setUndecorated(true);
        this.pack();
        setSize(widthSize,heightSize);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        */
        //canvas = new Canvas();
        Sound.playMusic("src/sound/music.wav");
        setPreferredSize(new Dimension(widthSize,heightSize));
        setMaximumSize(new Dimension(widthSize,heightSize));
        setMinimumSize(new Dimension(widthSize,heightSize));
        setFocusable(true);
        //this.add(canvas);
        addKeyListener(keys);
        addMouseMotionListener(new MyMouseMotionEvent());
        setVisible(true);
        //start();
        requestFocus();
        initPoints();
    }


    @Override
    public void run() {

        long now  = 0;
        long lastTime = System.nanoTime();

        while(runner){
            now = System.nanoTime();
            delta+= (now-lastTime)/TARGETTIME;
            lastTime = now;

            if(delta>=1){
                update();
                render();
                delta--;
            }

        }

        try {
            stop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(){
        if(runner1) {
            fs.collision(this);
        }
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        g2d.clearRect(0,0,widthSize,heightSize);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,widthSize,heightSize);
        if(runner1) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.white);
            g2d.fillOval(x, y, sizeBall, sizeBall);
        }
        g2d.setColor(Color.blue);
        g2d.fillRect(xr, yr, sizeR, 10);
        drawRectG(g2d);
        g2d.dispose();
        bs.show();

    }

    public void initPoints(){
        for(int i = 0; i < rectPoints.length;i++){
            for(int j = 0; j < rectPoints[0].length;j++){
                points[i][j]=new Point(initPX,initPY);
                initPX+=sizeRectW;
            }
            initPX = 20;
            initPY +=sizeRectH;
        }
        initPX = 20;
        initPY = 40;

    }

    public void drawRectG(Graphics2D g2d){
        for(int i = 0; i < rectPoints.length;i++){
            for(int j = 0; j < rectPoints[0].length;j++){
                if(rectPoints[i][j]==1) {
                    g2d.setColor(Color.black);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRect((int) points[i][j].getX(), (int) points[i][j].getY(), sizeRectW, sizeRectH);
                    g2d.setColor(Color.CYAN);
                    g2d.fillRect((int) points[i][j].getX(), (int) points[i][j].getY(), sizeRectW, sizeRectH);
                    initPX += sizeRectW;
                }
            }
            initPX = 20;
            initPY +=sizeRectH;
        }
        initPX = 20;
        initPY = 40;

    }

    public void start(){
        runner = true;
        hilo = new Thread(this);
        hilo.start();
    }

    public void stop() throws InterruptedException {
        hilo.join();
        runner = false;
    }

    public class MyKeyEvent extends java.awt.event.KeyAdapter{
        @Override
        public void keyPressed(java.awt.event.KeyEvent evt){
            if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
                System.out.println("derecha");
                moveR();
            }
            if(evt.getKeyCode() == KeyEvent.VK_LEFT){
                System.out.println("izquierda");
                moveL();
            }
            if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
                //runner=false;
                mediator.goPanelSettings();
            }

            if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                runner1 = true;
            }

            if(evt.getKeyCode() == KeyEvent.VK_ALT){
                initValues();

            }
        }
    }

    public void moveR(){
        if(xr+sizeR<widthSize){
            xr+=10;
        }
    }

    public class MyMouseMotionEvent extends java.awt.event.MouseMotionAdapter{
        @Override
        public void mouseDragged(MouseEvent evt){
            if(evt.getX()>0 && evt.getX() + sizeR<widthSize) {
                xr = evt.getX();
            }
        }
    }

    public void moveL(){
        if(xr>0){
            xr-=10;
        }
    }

    public void initValues(){
        runner1 = false;
        fs.flag=true;
        fs.goRight = false;
        fs.goTop = false;
        for(int i = 0; i<rectPoints.length;i++){
            for(int j = 0; j<rectPoints[0].length;j++){
                rectPoints[i][j]=1;
            }
        }
        xr = (widthSize - sizeR)/2;
        y = 350;
        x = (widthSize - sizeBall)/2;
    }
}
