

public class PlayerNet extends Player
{
    private Connection client;
    
    public PlayerNet(Connection con)
    {
        super(con.getId());
    }

    @Override
    public Move getMove()
    {
         Move humanMove = null;
     //  humanMove = client.readData();
        
        
        return humanMove;
    }
    
    
}
