
public class Queen {
    private int row; //the queen's row position
    private int col; //the queen's col position
    private boolean isBlack; // indicate the queen's color black or white

    //initialize the row, column, and isBlack flag
    public Queen(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    // checking if the queen's move is legal
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if  (board.verifyHorizontal(this.row, this.col, endRow, endCol) || board.verifyVertical(this.row, this.col, endRow, endCol) || board.verifyDiagonal(this.row, this.col, endRow, endCol)) {
            return (true); // the move is legal
        }
        return false; // invalid move
    }
}
