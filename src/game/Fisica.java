package game;

public class Fisica {
    protected boolean flag = true, goRight = false, goTop = false;
    protected int v = 5;
    public void collision(GamePanel panel){
        collisionR(panel);
        if(flag){
            panel.x+=v;
            panel.y+=v;
        }



        if(panel.x == 0){
            goRight = true;
            flag = false;
        }else if(panel.x+panel.sizeBall>=panel.widthSize){
            goRight =false;
        }

        if(panel.y+panel.sizeBall >= panel.heightSize){
            panel.runner1=false;
            /*goTop = true;*/
            flag = false;
        } else if(panel.y ==0){
            goTop = false;
            flag = false;
        }

        if(goRight){
            panel.x+=v;
        }else{
            panel.x-=v;
        }

        if(goTop){
            panel.y-=v;
        }else{
            panel.y+=v;
        }
        impact(panel);
    }

    public void collisionR(GamePanel panel){
        if(panel.x+ panel.sizeBall >= panel.xr && panel.x<=panel.xr+ panel.sizeR && panel.y + panel.sizeBall== panel.yr){
            goTop = true;
            flag = false;
        }
    }

    public void impact(GamePanel panel){
        for(int i = 0; i< panel.points.length; i++) {
            for(int j = 0; j< panel.points[0].length; j++) {
                if(panel.rectPoints[i][j]==1) {
                    if (panel.x + panel.sizeBall == panel.points[i][j].getX()   &&   panel.y + panel.sizeBall >= panel.points[i][j].getY() && panel.y <= panel.points[i][j].getY()+panel.sizeRectH) {
                        goRight = false;
                        panel.rectPoints[i][j] = 0;
                    } else if (panel.x == panel.points[i][j].getX() + panel.sizeRectW && panel.y + panel.sizeBall >= panel.points[i][j].getY() && panel.y <= panel.points[i][j].getY()+panel.sizeRectH) {
                        goRight = true;
                        panel.rectPoints[i][j] = 0;
                    }

                    if (panel.x + panel.sizeBall >= panel.points[i][j].getX() && panel.x <= panel.points[i][j].getX() + panel.sizeRectW && panel.y + panel.sizeBall == panel.points[i][j].getY()) {
                        goTop = true;
                        panel.rectPoints[i][j] = 0;
                    } else if (panel.x + panel.sizeBall >= panel.points[i][j].getX() && panel.x <= panel.points[i][j].getX() + panel.sizeRectW && panel.y == panel.points[i][j].getY() + panel.sizeRectH) {
                        goTop = false;
                        panel.rectPoints[i][j] = 0;
                    }
                }
            }
        }
    }
}
