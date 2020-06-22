/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package var2;

/**
 *
 * מייצג משבצת במטרציה הלוגית
 */
import var1.*;
import jarmo.*;
import java.util.ArrayList;

public class PlaceModel {

    private int value;// הערך במקום 
    private ArrayList<Location> Arr;
private Location loc ;

    public PlaceModel(int value, Location loc) 
    {
        Arr = new ArrayList<>();
        this.value = value;
       this.loc= loc;
       
    }

    public PlaceModel() 
    {
    Arr = new ArrayList<>();
       loc= new Location();
    }

    PlaceModel(int value) 
    {
          this.value = value;
        Arr = new ArrayList<>();
       loc= new Location();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ArrayList<Location> getPossiblePlaces() {
        return Arr;
    }

    public void setPossiblePlaces(ArrayList<Location> Arr) {
        this.Arr = Arr;
    }

    /**
     * מוסיף לרשימת המקמות החוקיים מקום
     *
     * @param row - מיקום השורה החוקי
     * @param col - מיקום העמודה החוקי
     */
    public void AddPossiblePlacesWITOSvohim(int row, int col) 
    {
        this.Arr.add(new Location(row+1, col));
    }
      public void AddPossiblePlacesNOSvohim(int row, int col) 
    {
        this.Arr.add(new Location(row, col));
    }

    public Location getLocNOSvohim() 
    {
        Location tamp = loc;
        tamp.setRow(loc.getRow()-1);
        return tamp;
    }

    public void setLocNOSvohim(Location loc) 
    {
        loc.setRow(loc.getRow()+1);
        this.loc = loc;
    }
    public Location getLocYesSvohim() 
    {
       
        return  loc ;
    }
      public void setLocYesSvohim(Location loc) 
    {
        
        this.loc = loc;
    }
}
    


