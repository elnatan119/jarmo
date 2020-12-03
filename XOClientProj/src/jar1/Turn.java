package jar1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */






import java.awt.Color;

/**
 *
 * @author לוי
 */
public enum Turn 
{
    black, white, None;

    public Color getColor() 
    {
        if (this == Turn.black) 
        {
            return Color.black;
        }
        if (this == Turn.white) 
        {
            return Color.WHITE;
        } else// זה שווה לכלום 
        {
            return Color.BLUE;
        }
    }

    String getTurn() {
        
        String turn = "";
        switch (this) {
            case black:
                turn = "black turn";
                break;

            case white:

                turn = "white turn";
                break;

            default:
            // code block
        }
        return turn;
    }
      String getTurnName() 
      {
           String turn = "";
        switch (this) {
            case black:
                turn = "black ";
                break;

            case white:

                turn = "white ";
                break;
                
            default:
            // code block
        }
        return turn;
      }
      Turn opturn ()
      {
          if(this == Turn.white) return Turn.black;
          else return  Turn.white ;
      }
      
}
