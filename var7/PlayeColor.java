/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package var7;









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
                key= ("");
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