public class King {
    private int row;//current row of the king
    private int col;  //current column of the king
    private boolean isBlack;  //Indicates whether the king is black or white

    //Initialize the row, column and isBlack flag
    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    //Checking if the move is legal
    public boolean isMoveLegal(Board board, int endRow, int endCol) {

        if (board.verifyAdjacent(this.row, this.col, endRow, endCol)) {
            return (true); //The move is legal


        }
        return false; // Invalid move.
    }
}
