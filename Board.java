public class Board {

    // Instance variables
    private Piece[][] board;
    int counter = 0;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        board = new Piece[8][8];

    }


    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {

        return board[row][col];
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {

        board[row][col] = piece;
    }


    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move
    // is legal, and to execute the move if it is. Your Game class should not
    // directly call any other method of this class.


    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece startPiece = this.getPiece(startRow, startCol);
        if (this.getPiece(startRow, startCol).isMoveLegal(this, endRow, endCol) && this.verifySourceAndDestination(startRow, startCol, endRow, endCol, this.getPiece(startRow, startCol).getIsBlack())) {
            this.setPiece(endRow, endCol, startPiece);
            this.setPiece(startRow, startCol, null);
            startPiece.setPosition(endRow, endCol);
            counter ++;
            return true;
        }
        return false;
    }

    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        boolean whiteKingExists = false;
        boolean blackKingExists = false;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece != null) {
                    if (piece.toString().equals("\u2654")) {
                        whiteKingExists = true;
                    } else if (piece.toString().equals("\u265A")) {
                        blackKingExists = true;
                    }
                }
            }
        }

        return !whiteKingExists || !blackKingExists;
    }


    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < 8; i++) {
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for (int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }
    }


    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (startRow < 0 || startRow >= 8 || startCol < 0 || startCol >= 8 || endRow < 0 || endRow >= 8 || endCol < 0 || endCol >= 8) {
            return false;
        }

        if (board[startRow][startCol] == null) {
            return false;
        }

        if (board[startRow][startCol].getIsBlack() != isBlack) {
            return false;
        }

        Piece endPiece = board[endRow][endCol];
        if (endPiece != null && endPiece.getIsBlack() == isBlack) {
            return false;
        }

        return true;
    }



    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        int rowDifference = Math.abs(endRow - startRow);
        int colDifference = Math.abs(endCol - startCol);

        return rowDifference <= 1 && colDifference <= 1;
    }

    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow != endRow) {
            return false;
        }

        int minCol = Math.min(startCol, endCol);
        int maxCol = Math.max(startCol, endCol);

        for (int col = minCol + 1; col < maxCol; col++) {
            if (board[startRow][col] != null) {
                return false;
            }
        }

        return true;
    }

    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol != endCol) {
            return false;
        }

        int minRow = Math.min(startRow, endRow);
        int maxRow = Math.max(startRow, endRow);

        for (int row = minRow + 1; row < maxRow; row++) {
            if (board[row][startCol] != null) {
                return false;
            }
        }

        return true;
    }


    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        // Calculate the difference in row and column positions
        int rowDifference = Math.abs(endRow - startRow);
        int colDifference = Math.abs(endCol - startCol);

        // Check the movement is diagonal
        if (rowDifference == colDifference) {
            // Determine the direction of the diagonal move (upward or downward)
            int rowDirection = (endRow - startRow > 0) ? 1 : -1;
            int colDirection = (endCol - startCol > 0) ? 1 : -1;

            for (int i = 1; i < rowDifference; i++) {
                int currentRow = startRow + (i * rowDirection);
                int currentCol = startCol + (i * colDirection);

                // Check the space is empty
                if (board[currentRow][currentCol] != null) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }
}
