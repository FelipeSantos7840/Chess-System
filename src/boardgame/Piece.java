package boardgame;

public abstract class Piece {
    //PROTECTED PARA FACILITAR O ACESSO DURANTE HERANÇA
    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
        position = null;
        //AS PEÇAS NÃO SERÃO COLOCADAS NESSA INICIALIZAÇÃO
    }

    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove(){
        boolean[][] mat = possibleMoves();
        for(int x=0;x < mat.length;x++){
            for(int y=0; y < mat[x].length;y++){
                if(mat[x][y]){
                    return true;
                }
            }
        }
        return false;
    }
}
