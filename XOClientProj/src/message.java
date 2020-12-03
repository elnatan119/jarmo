/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author לוי
 */
public class message implements java.io.Serializable
{
   Make make;

    public message() {
    }

    public message(Make make) {
        this.make = make;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }
   
   
    
}
