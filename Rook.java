
public class Rook {
    private int row; // the current row of the king
    private int col; // the current column of the king
    private boolean isBlack; // indicate the color of king (black or white)

    //Initialize the row, column and isBlack flag
    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    //Checking if the move is legal
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // rook can be moved vertically or horizontally
        if (this.row == endRow || this.col == endCol) {
            // Make sure that there are no other piece on the path the rook is trying to take
            if (this.row == endRow) {
                // horizontal
                int minCol = Math.min(this.col, endCol);
                int maxCol = Math.max(this.col, endCol);
                for (int col = minCol + 1; col < maxCol; col++) {
                    if (board.getPiece(this.row, col) != null) {
                        return false;
                    }
                }
            } else {
                // vertical
                int minRow = Math.min(this.row, endRow);
                int maxRow = Math.max(this.row, endRow);
                for (int row = minRow + 1; row < maxRow; row++) {
                    if (board.getPiece(row, this.col) != null) {
                        return false;
                    }
                }
            }

            // If there is no other piece in the target position, or if there is another piece, you can catch the opponent's piece
            Piece targetPiece = board.getPiece(endRow, endCol);
            if (targetPiece == null || targetPiece.getIsBlack() != this.isBlack) {
                return true;
            }
        }
        return false;
    }}