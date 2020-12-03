package jar1;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */











/**
 *
 * @author לוי
 */
 public enum PlayeColor 
{
    white,
    black,
    None,
    whiteKing,
    blackKing;
//   private final int None = 0;
//    private final int BLACK = -1;
//    private final int WHITE = 1;
//    private final int BLACKing = -2;
//    private final int WHITEKING = 2;
    static String modele(int value) 
    {
       PlayeColor  tamp = None;
       switch (value) 
          {
      
               
            case -1:
                tamp = black;
                break;
            case 1:
                tamp = white;
                break;
            case -2:
               tamp = blackKing;
                break;
            case 2:
                  tamp = whiteKing;
                break;

         

        }
       return tamp.getASCItype();
       
    }
    public String getASCItype()
    {
        String key= "";
          switch (this) 
          {
            case black:
               key= ("⛂");
                break;
            case white:
               key= ("⛀");
                break;
            case None:
                key= (" ");
                break;
            case blackKing:
                 key= ("⛃");
                break;
            case whiteKing:
                key= ("⛁");
                break;

            default:

        }
          return key;
    }

    Turn Toturn() 
    {
           Turn key = Turn.None;
          switch (this) 
          {
            case black:
               key= Turn.black;
                break;
            case white:
               key= Turn.white;
                break;
            case None:
                    key= Turn.None;
                break;
            case blackKing:
                    key= Turn.black;
                break;
            case whiteKing:
             key= Turn.white;
                break;

            default:

        }
          return key;
        
    }

}