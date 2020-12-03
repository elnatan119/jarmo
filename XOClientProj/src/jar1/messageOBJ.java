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
public class MessageOBJ extends  Message
{
   private Bring bring ;
  private Object objcet;

    public MessageOBJ(Bring bring) 
    {
        this.bring = bring;
    }

    public Bring getBring() {
        return bring;
    }

    public void setBring(Bring bring) {
        this.bring = bring;
    }

    public Object getObjcet() {
        return objcet;
    }

    public void setObjcet(Object objcet) {
        this.objcet = objcet;
    }
    
}
