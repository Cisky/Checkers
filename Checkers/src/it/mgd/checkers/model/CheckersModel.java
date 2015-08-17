package it.mgd.checkers.model;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Giuliano
 */
public class CheckersModel implements Model
{
    private class Tile
    {
        private boolean isOccupied;
        private Piece piece;
        public Tile()
        {
            isOccupied = false;
            piece = null;
        }
        public void Occupy(Piece Pawn)
        {
            isOccupied = true;
            piece=Pawn;
        }
        public void Free()
        {
            isOccupied = false;
            piece=null;
        }
        
        public Piece GetPiece()
        {
            return piece;
        }
        
        
        public boolean IsOccupied()
        {
            return isOccupied;
        }
    }
        
    public CheckersModel()
    {
        board = new Tile[dimension][dimension];

        for(int i=0;i<dimension;++i)
            for(int j=0;j<dimension;++j)
                board[i][j]= new Tile();
        
        pieces = new ArrayList();
        for(int i=0;i<dimension * 3;++i)
            pieces.add(new Piece());
    }
    
    
    public ListIterator<Piece> GetPieces()
    {
        return pieces.listIterator();
    }
       
    @Override
    public void Start()
    {
        Piece tmp;
        int k=0;
        for(int i = 0; i < 3; ++i){
            for(int j = i%2; j < dimension; j+=2,++k){
                tmp = pieces.get(k);
                tmp.SetColor("WHITE");
                tmp.SetX(dimension-1-i);
                tmp.SetY(j);
                board[j][dimension-1-i].Occupy(tmp);
                pieces.add(tmp);
          
            }
        }
        for(int i = 0; i < 3; ++i){
            for(int j = (i+1)%2; j < dimension; j+=2,++k){
                tmp = pieces.get(k);
                tmp.SetColor("BLACK");
                tmp.SetX(i);
                tmp.SetY(j);
                board[j][i].Occupy(tmp);
                pieces.add(tmp);
           
            }
        }
    }
    
    @Override
    public void MovePiece(int x,int y,int fx,int fy)
    {
        Piece tmp = board[x][y].GetPiece();
        tmp.SetX(fx);
        tmp.SetY(fy);
        board[fx][fy].Occupy(tmp);
        board[x][y].Free();
        
    }
    
    @Override
    public void Capture(int x,int y)
    {
        board[x][y].GetPiece();
        board[x][y].Free();
    };
    
    @Override
    public Piece PieceAt(int x,int y)
    {
        return board[x][y].GetPiece();
    }
    
    @Override
    public boolean IsOccupied(int x,int y)
    {
        return board[x][y].IsOccupied();
    }
    
    final private List<Piece> pieces;
    final private Tile[][] board;
    private final int dimension = 8;
}