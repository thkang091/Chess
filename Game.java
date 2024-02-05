import java.util.Scanner;
public class Game {
    public static void main(String[] args) {
        // Initialize the chessboard
        Board board = new Board();
        // Load the initial position using FEN notation
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
        int gameCount = 0; //tracks turns of the game

        // a scanner for input
        Scanner scanner = new Scanner(System.in);
        // Initialize a flag to keep track of whose turn it is

        boolean isBlackTurn = false;
        // Main game loop

        while (!board.isGameOver()) {
            System.out.println(board.toString());

            // Determine the current player's color
            String playerColor = isBlackTurn ? "Black" : "White";

            System.out.println("It is currently " + playerColor + "'s turn to play.");
            // Prompt the player for their move

            System.out.print("What is your move? (format: [start row] [start col] [end row] [end col]): ");
            int startRow = scanner.nextInt();
            int startCol = scanner.nextInt();
            int endRow = scanner.nextInt();
            int endCol = scanner.nextInt();
            //to make the move

            if (board.movePiece(startRow, startCol, endRow, endCol)) {
                System.out.println("Move correctly");
                isBlackTurn = !isBlackTurn;
                // Check if a pawn has reached the last rank to promote it
                Piece EndPiece = board.getPiece(endRow, endCol);
                char pieceCharacter = EndPiece.getCharacter();
                if (pieceCharacter == '\u2659' || pieceCharacter == '\u265f') {
                    EndPiece.promotePawn(endRow, pieceCharacter == '\u265f'); // Pass the parameters accordingly
                }
            } else {
                System.out.println("Invalid Move. Try again.");
            }
        }

        // Determine the winner and display the result

        if (isBlackTurn) {
            System.out.println("White has won the game!");
        } else {
            System.out.println("Black has won the game!");
        }

        scanner.close();
    }
}
