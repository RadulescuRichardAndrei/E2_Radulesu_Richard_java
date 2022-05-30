package GUI;

public class BattleEnemyPosition {
    private boolean dead;
    private int xpoz,ypoz, dim;
    private int size;

    public int getXpoz() {
        return xpoz;
    }

    public int getYpoz() {
        return ypoz;
    }

    public int getDim() {
        return dim;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public BattleEnemyPosition(int xpoz, int ypoz, int dim) {
        this.xpoz = xpoz;
        this.ypoz = ypoz;
        this.dim = dim;
        dead=false;
    }


    public boolean isClickedOn(int x, int y){
        if(xpoz<=x && x<=xpoz+dim && ypoz<=y && y<=ypoz+dim)
            return true;
        return false;
    }
}
