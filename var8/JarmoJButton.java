/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package var8;





import var7.*;
import javax.swing.JButton;

/**
 *
 * @author לוי
 */
public class JarmoJButton extends JButton
{
private Location loc;
 Clicking click ;// לחיצה 

    public JarmoJButton( Location loc) 
    {
          super();
      this.loc=loc;
      
    }

    public Location getLoc() 
    {
        return loc;
    }

    public void setLoc(Location loc) 
    {
        this.loc = loc;
    }

    public Clicking getClick() 
    {
        return click;
    }

    public void setClick(Clicking click) 
    {
        this.click = click;
    }
    

   
}
