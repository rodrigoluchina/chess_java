package boardgame;

public class Board {

    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if (rows < 7 || columns < 7) {
            throw new BoardException("There must be 8 rows and columns to create a board");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Piece piece(int row,int column){
        if (!positionExists(row,column)) {
            throw new BoardException("Position do not exists");
        }
        return pieces[row][column];
    }

    public Piece piece(Position position) {
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece,Position position) {
        if (thereIsAPiece(position)) {
            throw new BoardException("There is already a piece on position " + position );
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Position is not on the board");
        }
        if (piece(position) == null) {
            return null;
        }
        piece(position).position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return piece(position);
    }

    public boolean positionExists(int row,int column) {
        return row >=0 && row < rows && column >= 0 && column<columns;
    }

    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Position do not exists on the board");
        }
        return piece(position) != null;
    }
}
