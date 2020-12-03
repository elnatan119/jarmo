
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *  Document   : XOClient
 *  Created on : Nov 4, 2020, 1:41:31 PM
 *  Author     : User
 */
public class XOClient
{
     private static final int DEFAULT_SERVER_PORT = 1234;
    private static final Dimension WIN_SIZE = new Dimension(450, 250);
    private static final Color LOG_BG_COLOR = Color.LIGHT_GRAY;
    private static final Color LOG_FONT_COLOR = Color.BLACK;
    private static final Font LOG_FONT = new Font(Font.MONOSPACED, Font.BOLD, 12);
     public static final int DEFAULT_BOARD_WIDTH = 5;
    public static final int DEFAULT_BOARD_LENGTH = 7;

  

    // for Client
    private int serverPort;
    private String serverIP;
    private Connection conToServer;
    
   // for GUI
   private static View view;
    public XOClient()
    {
        view = new View(DEFAULT_BOARD_LENGTH, DEFAULT_BOARD_WIDTH);
        connectToServer();
    }
    public static void main(String[] args)
    {
         
              XOClient xoClient = new XOClient();
        xoClient.runClient();
          
       
        
    }

    static ArrayList<Location> GetRedZone(Turn turn) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static ArrayList<Location> getEmtyPlaces() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static PlayeColor getType(Location location) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static boolean getSonudCheck() 
    {
        return  false;
    }

    static void setSonud(boolean b) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void setupGame() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void Undo() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void pcMove() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void buttonClicked(Location loc, Clicking click) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      private void connectToServer()
    {
        try
        {
            // Set the IP & PORT of the server 
            serverPort = DEFAULT_SERVER_PORT;
            serverIP = InetAddress.getLocalHost().getHostAddress(); // IP of this computer
       
            // 
            String serverAddress = JOptionPane.showInputDialog(view, "Enter SERVER Address [IP : PORT]", serverIP + " : " + serverPort);

            // check if Cancel button was pressed 
            if(serverAddress == null)
                closeClient();

            serverAddress = serverAddress.replace(" ", ""); // remove all spaces
            serverIP = serverAddress.substring(0, serverAddress.indexOf(":"));
            serverPort = Integer.parseInt(serverAddress.substring(serverAddress.indexOf(":") + 1));

            // create Connection object to the server
            conToServer = new Connection(new Socket(serverIP, serverPort));
             if(conToServer.readData().startsWith("#wait"))
            {
                System.out.println("XOClient.proccessMsgFromServerddddddddddddddddddddd()");
                // הקפצת דיאלוג שיבקש מהלקוח איך הוא רוצה להתחבר כאורח או כרשום
               view.tell("wait", LOG_BG_COLOR);
               view.lockAllButton(false);
                return;
            }
             else
             {
            // wait for the server to send the client id
            String clientId = conToServer.readData();
             
            conToServer.setId(clientId);
            
            // show Client & Server Addresses on log & window title
            System.out.println("Chat Client (" + conToServer.getLocalAddress() + ") " + clientId);
             System.out.println("Client connected to Server (" + conToServer.getRemoteAddress() + ") as " + clientId);
             }
           
        }
        catch(Exception ex)
        {
          
        }
    }
         private void closeClient()
    {
        if(conToServer.isOpen())
        {
            String msg = "The connection with the Server is LOST!\nClient will be close...";
            JOptionPane.showMessageDialog(view, msg, "Chat Client Error", JOptionPane.ERROR_MESSAGE);
            stopClient();
        }
        
      
        
        // close GUI
        view.dispose();
        
        // close client
        //System.exit(0);
    }
          public void runClient()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(true)
                {
                    // wait for a message from the server or null if socket closed
                    String msg = conToServer.readData();

                    if(msg == null)
                        break;

                    // proccess the message from the server
                    proccessMsgFromServer(msg); 
                }
                closeClient();
            }
        });
        thread.setName("client");
        thread.start();
    }

    
    private void proccessMsgFromServer(String msg)
    {
         System.out.println("hahahah"+msg);
        // האם התקבלה פקודה מיוחדת מהשרת
        // הקשורה למצב במכונת המצבים
        if(msg.startsWith("#"))
        {
            if(msg.startsWith("#wait"))
            {
                System.out.println("XOClient.proccessMsgFromServer()gggggggggggggggggggggggg");
                // הקפצת דיאלוג שיבקש מהלקוח איך הוא רוצה להתחבר כאורח או כרשום
               view.tell(msg, LOG_BG_COLOR);
               view.lockAllButton(false);
                return;
            }
            
        if(msg.startsWith("#open"))
        {
      
              view.lockAllButton(true);
             
           
        }
        
        
//        if(msg.startsWith("#your_turn"))
//        {
//        }
//        
//        if(msg.startsWith("#game_over"))
//        {
//        }
        
        }
        

      
    }

  
    // call when window X pressed
    private void exitClient()
    {
        int option = JOptionPane.showConfirmDialog(view, "Do you realy want to Exit?", "Client Exit", JOptionPane.YES_NO_OPTION);

        if(option == JOptionPane.OK_OPTION)
            stopClient();
    }

    private void stopClient()
    {
        conToServer.close(); // will throw 'SocketException' and unblock I/O. see close() API 
        System.out.println("Client " + conToServer.getId() + " Stoped!");
    }

}
