
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *  Document   : XOServer
 *  Created on : Nov 4, 2020, 1:40:26 PM
 *  Author     : User
 */
public class XOServer
{
    private static final int DEFAULT_SERVER_PORT = 1234;
    private static final Dimension WIN_SIZE = new Dimension(400, 400);
    private static final Color LOG_BG_COLOR = Color.BLACK;
    private static final Color LOG_FONT_COLOR = Color.GREEN;
    private static final Font LOG_FONT = new Font(Font.MONOSPACED, Font.BOLD, 12);

    // תכונות
    private String serverIP;
    private int serverPort;
    private JFrame win;
    private JTextArea logArea;
    private ServerSocket serverSocket;
    private int autoClientID;
    private ArrayList<Connection> clientsList;

    
    public XOServer()
    {
        createServerGUI();
        createServerSocket();
        
        autoClientID = 0;
        clientsList = new ArrayList();
    }

    // Run the server - wait for clients to connect & handle them
    public void runServer() 
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                log("Server start RUNNING on " + serverIP + ":" + serverPort + "\n");

                while(true)
                {
                    // Wait(blocking) for a client to connect.
                    Socket clientSocket = waitForAClient();

                    if(clientSocket == null)
                        break;

                    // create a thread to handle client messages
                    handleClient(clientSocket);
                }

                closeServer();
            }
        });
        thread.setName("server");
        thread.start();
    }

    // Wait(blocking method) for client to connect.
    // Return the client socket or null if serverSocket closed.
    private Socket waitForAClient()
    {
        Socket clientSocket = null;
        try
        {
            // Wait for a new client to connect. return client socket.
            clientSocket = serverSocket.accept();
        }
        catch(IOException ex)
        {
            //Utils.debug(ex);
        }

        return clientSocket;
    }
    
    // handle client with thread
    private void handleClient(Socket clientSocket)
    {
        Thread cilentThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                String clientId = "Guest#" + autoClientID++;
                String clientAddress = clientSocket.getRemoteSocketAddress().toString().substring(1);
                log(clientId + " connected from " + clientAddress);

                // create Connection object to the new client
                Connection conToClient = new Connection(clientSocket, clientId);

                // add the new Connection object to the clients list
                clientsList.add(conToClient);
                if(clientsList.size()==2)
                {
                    Game game = new Game(new PlayerNet(clientsList.get(0)), new PlayerNet(clientsList.get(1)));
                        clientsList.get(0).writeData("#open" );
                           clientsList.get(1).writeData("#open" );
                }
                else
                {
                      conToClient.writeData("#wait....... for onter person");
                      System.out.println(".run()ddddddddddd");
                      
                }
               
            
                
                // handle the messages comming from the current client
                while(true)
                {
                    // wait for data from client
                    String msg = conToClient.readData();

                    if(msg == null)
                        break;

                    // proccess the message from the client
                    proccessMsgFromClient(conToClient, msg);
                }

                disconnectClient(conToClient);
            }
        });
        
        cilentThread.setName("Guest#" + autoClientID + "Handler");
        cilentThread.start();
    }

    private void proccessMsgFromClient(Connection fromClient, String msg)
    {
        log("[" +fromClient.getId() + "]: " + msg);

        
        if(msg.startsWith("online?"))
        {
             
             fromClient.writeData(getOnlineClientName());
             return;
        }
        
        if(msg.startsWith("[Guest#"))
        {
            getConnection(msg).writeData(msg);
        }
        
        
//        switch(msg)
//        {
//            case "online?":
//                // send to this client all online guests
//                break;
//                
//            case     
//        }

        // message to all other clients
        for(int i = 0; i < clientsList.size(); i++)
        {
            Connection otherClient = clientsList.get(i);

            String clientId = fromClient.getId();
            if(otherClient == fromClient)
                clientId = "Me";
            
            otherClient.writeData("[" + clientId + "]: " + msg);
        }
        
    }

    private void disconnectClient(Connection clientCon)
    {
        log(clientCon.getId() + " Disconnected!");
        clientsList.remove(clientCon);
        clientCon.close();
    }

    private void exitServer()
    {
        int option = JOptionPane.showConfirmDialog(win, "Do you realy want to Exit?", "Server Exit", JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.OK_OPTION)
            stopServer();
    }

    private void stopServer()
    {
        try
        {
            // This will throw cause an Exception on serverSocket.accept() in waitForClient() method
            serverSocket.close();

            // close all threads & clients
            for(int i = 0; i < clientsList.size(); i++)
                clientsList.get(i).close();

            log("Server Stoped!");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    private void closeServer()
    {
        if(serverSocket != null && !serverSocket.isClosed())
            stopServer();

        log("Server Closed!");

        // close GUI 
        win.dispose();

        // close server
        //System.exit(0);
    }

    // set server address (IP & Port) 
    private void createServerSocket()
    {
        String port=null;
        try
        {
            // get the Computer IP on this machine
            serverIP = InetAddress.getLocalHost().getHostAddress();

            port = JOptionPane.showInputDialog(win, "Enter Server PORT Number:", DEFAULT_SERVER_PORT);
            
            // Check if Cancel button was pressed
            if(port == null)
                closeServer();
            
            serverPort = Integer.parseInt(port);
            serverSocket = new ServerSocket(serverPort);
            
            win.setTitle("Chat Server (" + serverIP + ":" + serverPort + ")");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            String errMsg = "Can't run server on (" + serverIP + ":" + port + ")\n";
            errMsg += "Please check the Port Number & try again...";
            JOptionPane.showMessageDialog(win, errMsg, "Chat Server Error", JOptionPane.ERROR_MESSAGE);
            createServerSocket();
        }
    }

    // create server GUI
    private void createServerGUI()
    {
        win = new JFrame();
        win.setSize(WIN_SIZE);
        win.setAlwaysOnTop(true);
        win.setTitle("Chat Server");
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        win.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                exitServer();
            }
        });

        // create displayArea
        logArea = new JTextArea();
        logArea.setLineWrap(true);
        logArea.setFont(LOG_FONT);
        logArea.setMargin(new Insets(5, 5, 5, 5));
        logArea.setBackground(LOG_BG_COLOR);
        logArea.setForeground(LOG_FONT_COLOR);

        logArea.setEditable(false);

        // add all components to window
        win.add(new JScrollPane(logArea), BorderLayout.CENTER);

        // show window
        win.setVisible(true);
    }

    private void log(String msg)
    {
        logArea.append(msg + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
        
        System.out.println(msg);
    }

    // main
    public static void main(String args[]) 
    {
        XOServer chatServer = new XOServer();
        chatServer.runServer();
        System.out.println("main server finished!");
    }

    private String getOnlineClientName() 
    {
        String Sand ="" ;
         Sand += "The connected users are ";
        for (int i = 0; i <clientsList.size() ; i++) 
        {
            Sand += " \n"+clientsList.get(i).getId();
        }
        return Sand;
    }

    private Connection getConnection(String msg) 
    {
        for (int i = 0; i < clientsList.size(); i++) 
        {
            
            if(msg.contains(clientsList.get(i).getId()))
            {
                return clientsList.get(i);
            }
        }
      
        return null;
    }
}
