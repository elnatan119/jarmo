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
public class Message implements java.io.Serializable
{
  private Make make;

    public Message() {
    }

    public Message(Make make) {
        this.make = make;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }
    public  boolean equMake(Make make) 
    {
        return  this.make.equals(make);
    }
   
   
    
}
