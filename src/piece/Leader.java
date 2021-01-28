package piece;

import java.awt.*;

public class Leader {

    private static final int TILE_SIZE = 100;
    private int row;
    private int col;
    private Color color;
    private int point;

    public Leader(int row, int col, Color color) {

        this.row = row;
        this.col = col;
        this.color = color;

    }

    public int getRow (){

        return row;
    }

    public int getCol (){

        return col;
    }

    public Leader() {

    }

    public boolean isMoveValid ( int moveRow, int moveCol){

        int rowCoeficient = Math.abs(moveRow - this.row);
        int colCoeficient = moveCol - this.col;

        return rowCoeficient == 1 &&
                colCoeficient == 0;
    }

    public boolean isAttackValid ( int attackRow, int attackCol){
        return false;
    }


    public void render (Graphics g){
        int x = this.col * TILE_SIZE;
        int y = this.row * TILE_SIZE;

        g.setColor(this.color);
        g.fillOval(x, y, 50, 50);
    }

    public void move(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
