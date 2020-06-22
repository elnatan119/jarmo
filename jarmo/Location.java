/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarmo;

/**
 *
 * @author לוי
 */
public class Location 
{
  private int row, col;
  
    public Location() 
    {
        
    }
    public Location(int row, int col) 
    {
        this.row = row;
        this.col = col;
    }
 

    public int getRow() 
    {
        return row;
    }

    public void setRow(int row) 
    {
        this.row = row;
    }

    public int getCol() 
    {
        return col;
    }

    public void setCol(int col) 
    {
        this.col = col;
    }
    public boolean equal(Location b)
    {
        return this.col == b.col && this.row==b.row;
    }
    @Override
    public String toString() 
    {
        return "Location{" + "row=" + (row-1) + ", col=" + col + '}';
    }
  
            
    
}
