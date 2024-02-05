public class Bishop {
    //instance variables
    private int row;
    private int col;
    private boolean isBlack;

    //constructs a new Bishop object with specific position and color
    public Bishop(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }


    //Check if the bishop moves legally
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        //Check if the bishop is moving diagonally
        int rowDifference = Math.abs(endRow - this.row);
        int colDifference = Math.abs(endCol - this.col);

        if (rowDifference != colDifference) {
            //setting bishop to only move diagonal
            return false;
        }


//check if the there are any other pieces hindering the bishop's path
        int rowDirection = (endRow > this.row) ? 1 : -1;
        int colDirection = (endCol > this.col) ? 1 : -1;

        int currentRow = this.row + rowDirection;
        int currentCol = this.col + colDirection;

        while (currentRow != endRow && currentCol != endCol) {
            if (board.getPiece(currentRow, currentCol) != null) {
                //When there is a piece on the bishop's path
                return false;
            }
            //using step by step guidelines to heck if any object is blocking bishop's path
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        // Check if the desired destination has an opponent's piece or empty

        Piece destinationPiece = board.getPiece(endRow, endCol);
        if (destinationPiece == null || destinationPiece.getIsBlack() != this.isBlack) {
            return true;
        }

        return false;
    }}