package jar1;





public class Game
{

    static void lockUndo(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void ReplaceTurn(Turn turn, Turn noturn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static Clicking getUniversalclick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static PlaceModel getPlace(Location Now) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
     static Turn getTurn() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     static Turn getNOturn() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    private Player p1,p2,currentPlayer;
    //private Model model; // board, minimax,....

    public Game(Player p1, Player p2)
    {
        this.p1 = p1;
        this.p2 = p2;
        this.currentPlayer = p1;
    }
    
    
    
    // this method will be called by the server
    // after he make a Pair of players.
    public void startGame()
    {
        // Thread ליצור 
        // =========================
        
        currentPlayer = p1;
        while(true)
        {
            // להודיע לשחקן השני זה שלא תורו לשחק שימתין
//           if(currentPlayer == p1)
//                p2.waitForYourTurn();
//            else
//                p1.waitForYourTurn();
            currentPlayer.getPartner().waitForYourTurn();
            
            // get move from current player
            Move move = currentPlayer.getMove();
            
            // עדכון לוח המשחק במהלך שהשחקן הנוכחי ביקש
            //model.apply(move);
            
            // לעדכן את היריב בלוח החדש
            // ========================
            
            // check if current player wins?
//            if(gameOver)
//            {
//                break;
//            }
            
            //switch players
//            if(currentPlayer == p1)
//                currentPlayer = p2;
//            else
//                currentPlayer = p1;
            currentPlayer = currentPlayer.getPartner();
            
        }
    }
    
}
