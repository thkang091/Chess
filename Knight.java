public class Knight {
    private int row; //where the knights is located on the chessboard on the row from 0 to 7
    private int col; //where the knight is located on the chessboard on the column from 0 to 7
    private boolean isBlack; //indicates whether the knight is black or whtie

    //constructs a Knight object with row, col and isBlack
    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    //check if the move for the knight is legal
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        int deltaX = Math.abs(endRow - this.row);
        int deltaY = Math.abs(endCol - this.col);
        //check if the move goes to the L shape pattern for a knight
        if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
            //Get the  piece at the target piece on the board
            Piece targetPiece = board.getPiece(endRow, endCol);
            //when the target position is empty, the move is legal
            if (targetPiece == null || targetPiece.getIsBlack() != this.isBlack) {
                return true;
            }
        }

        //If the condition does not match, it is an illegal move for the knight.

        return false;
    }
}
