package game;

import piece.Guard;
import piece.Leader;
import piece.TurtleWarrior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GameBoard extends JFrame implements MouseListener {


    public final int tileSideCount = 5;


    private Object [][] pieceCollection;
    private Object selectedPiece;

    public GameBoard() {


        this.pieceCollection = new Object[tileSideCount][tileSideCount];

        //yellow
        this.pieceCollection[0][0] = (new Guard(0,0,Color.YELLOW));
        this.pieceCollection[0][1] = (new Guard(0,1,Color.YELLOW));
        this.pieceCollection[0][2] = (new Guard(0,2,Color.YELLOW));
        this.pieceCollection[0][3] = (new Guard(0,3,Color.YELLOW));
        this.pieceCollection[0][4] = (new Leader(0,4,Color.YELLOW));


        //green
        this.pieceCollection[4][0] = (new Leader(4,0,Color.GREEN));
        this.pieceCollection[4][1] = (new Guard(4,1,Color.GREEN));
        this.pieceCollection[4][2] = (new Guard(4,2,Color.GREEN));
        this.pieceCollection[4][3] = (new Guard(4,3,Color.GREEN));
        this.pieceCollection[4][4] = (new Guard(4,4,Color.GREEN));

        //red
        this.pieceCollection[3][0] = (new TurtleWarrior(3,0,Color.RED));
        this.pieceCollection[3][4] = (new TurtleWarrior(3,4,Color.RED));




        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int row = this.getRowBasedOnCoordinates(mouseEvent.getY());
        int col = this.getRowBasedOnCoordinates(mouseEvent.getX());

        if(this.selectedPiece != null){

                Guard gu = (Guard) this.selectedPiece;

            if(gu.isMoveValid(row,col)) {

                movePiece(row, col, gu);
                this.repaint();
                return;
            }
        }

        if(this.hasBoardPiece(row, col)){
            this.selectedPiece = this.getBoardPiece(row, col);
        }

        if(this.selectedPiece != null){

            Leader l=(Leader) this.selectedPiece;

            int initialRow = l.getRow();
            int initialCol = l.getCol();
            l.move(row, col);
            this.pieceCollection[l.getRow()][l.getCol()] = this.selectedPiece;
            this.pieceCollection[initialRow][initialCol] = null;
            this.selectedPiece = null;
            this.repaint();
            return;

        }

        if(this.hasBoardPiece(row, col)){
            this.selectedPiece = this.getBoardPiece(row, col);
        }

        if(this.selectedPiece != null){

            TurtleWarrior tw=(TurtleWarrior) this.selectedPiece;

            int initialRow = tw.getRow();
            int initialCol = tw.getCol();
            tw.move(row, col);
            this.pieceCollection[tw.getRow()][tw.getCol()] = this.selectedPiece;
            this.pieceCollection[initialRow][initialCol] = null;
            this.selectedPiece = null;
            this.repaint();
            return;

        }

        if(this.hasBoardPiece(row, col)){
            this.selectedPiece = this.getBoardPiece(row, col);
        }

    }



    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    //@Override
    public void paint(Graphics g) {

        super.paint(g);

        for (int row = 0; row < 5; row++) {


            for (int col = 0; col < 5; col++) {

                this.renderGameTile(g, row, col);
                this.renderGamePiece(g, row, col);
            }

        }
    }

    private void movePiece(int row, int col, Guard gu) {
        int initialRow = gu.getRow();
        int initialCol = gu.getCol();
        gu.move(row, col);
        this.pieceCollection[gu.getRow()][gu.getCol()] = this.selectedPiece;
        this.pieceCollection[initialRow][initialCol] = null;
        this.selectedPiece = null;
    }

    private Color getTileColor(int row, int col) {
        boolean isRowEven = (row % 2 == 0);
        boolean isRowOdd = !isRowEven;
        boolean isColEven = (col % 2 == 0);
        boolean isColOdd = !isColEven;

        if(isRowEven && isColEven) return Color.BLACK;
        if(isRowEven && isColOdd) return Color.WHITE;
        if(isRowOdd && isColEven) return Color.WHITE;
        return Color.BLACK;

    }

    private void renderGameTile (Graphics g, int row, int col){
        Color tileColor = this.getTileColor(row, col);
        GameTile tile = new GameTile(row, col, tileColor);
        tile.render(g);

    }

    private Object getBoardPiece(int row, int col) {
        return this.pieceCollection[row][col];
    }

    private boolean hasBoardPiece(int row, int col){

        return this.getBoardPiece(row, col) != null;
    }

    private void renderGamePiece (Graphics g, int row, int col){

        if(this.hasBoardPiece(row, col)) {

            Guard gu = (Guard) this.getBoardPiece(row, col);
            gu.render(g);

        }

        if(this.hasBoardPiece(row, col)) {

            Leader l = (Leader) this.getBoardPiece(row, col);
            l.render(g);

        }

        if(this.hasBoardPiece(row, col)) {

            TurtleWarrior tw = (TurtleWarrior) this.getBoardPiece(row, col);
            tw.render(g);

        }
    }
    private int getRowBasedOnCoordinates(int coordinates){
        return coordinates / GameTile.TILE_SIZE;
    }

}
