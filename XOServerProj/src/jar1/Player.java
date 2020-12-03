package jar1;





public abstract class  Player
{
    private String id; //
    private Player partner; 
    
    public Player(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
    
    public abstract Move getMove();

    public Player getPartner() {
        return partner;
    }

    public void setPartner(Player partner) {
        this.partner = partner;
    }

    void waitForYourTurn() 
    {
      
    }

   
}
