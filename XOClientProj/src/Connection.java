import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Connection ערוץ תקשורת .
 * 10/09/2020 
 * Ilan Perez (ilanperets@gmail.com)
 */
public class Connection
{
    private String id;
    private Socket socket;
    private DataInputStream is;
    private DataOutputStream os;

    public Connection(Socket socket)
    {
        this.socket = socket;
        try
        {
            this.is = new DataInputStream(socket.getInputStream());
            this.os = new DataOutputStream(socket.getOutputStream());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public Connection(Socket socket, String id)
    {
        this.id = id;
        this.socket = socket;
        try
        {
            this.is = new DataInputStream(socket.getInputStream());
            this.os = new DataOutputStream(socket.getOutputStream());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void writeData(String data)
    {
        try
        {
            os.writeUTF(data);
        }
        catch(Exception ex)
        {
            //Utils.debug(ex);
        }
    }

    public String readData()
    {
        String data = null;
        try
        {
            data = is.readUTF();
        }
        catch(Exception ex)
        {
            //Utils.debug(ex);
        }
        return data;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void close()
    {
        try
        {
            socket.close();  // will close the socket and is & os streams 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public String getLocalAddress()
    {
        return socket.getLocalSocketAddress().toString().substring(1);
    }
    
    public String getRemoteAddress()
    {
        return socket.getRemoteSocketAddress().toString().substring(1);
    }

    public boolean isOpen()
    {
        return socket != null && !socket.isClosed();
    }
}
