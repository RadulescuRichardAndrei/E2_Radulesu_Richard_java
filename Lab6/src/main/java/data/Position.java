package data;

public class Position {
    private int player;
    private boolean up,down,left,right;

    public Position() {
        player=0;
    }

    public Position(int player, boolean up, boolean down, boolean left, boolean right) {
        this.player = player;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
