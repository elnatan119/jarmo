/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package var4;




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

}