/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package var3;

import java.awt.Color;



/**
 *
 * @author לוי
 */
public enum Turn {
    black,white,None;
   
    
    public Color getColor()
    {
        if(this == Turn.black)
        {
            return Color.black;
        }
         if(this == Turn.white)
        {
           return Color.WHITE; 
        }
         else// זה שווה לכלום 
         {
             return Color.BLUE;
         }
    }
    
}
