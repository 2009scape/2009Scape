package ms.classloader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

import ms.ServerConstants;
import ms.system.OperatingSystem;

/**
 * Worker thread for launcher connections
 * @author Clayton Williams (Hope)
 *
 */
public class WorkerThread extends Thread {

	/**
	 * Our launcher connection
	 */
	private Socket launcher = null;
    
    /**
     * Input stream from launcher
     */
    private ObjectInputStream is = null;
    
    /**
     * Output stream to launcher
     */
    private ObjectOutputStream os = null;

    /**
     * The user's operating system type
     */
    private OperatingSystem operatingSystem = OperatingSystem.WINDOWS;

    /**
     * Creates a new worker thread to handle the launcher requests
     * @param socket
     * @param classesCache
     * @throws IOException
     */
    public WorkerThread(Socket launcher)  throws IOException {
        super();
        this.launcher = launcher;
        try {
            os = new ObjectOutputStream(new BufferedOutputStream(this.launcher.getOutputStream()));
            os.flush();
            is = new ObjectInputStream(new BufferedInputStream(launcher.getInputStream()));
        } catch (IOException ioe) {
            this.launcher.close();
        }
    }

   /**
    * Input Stream handler
    */
    @SuppressWarnings("unused") //saving resource type for later
	public void run() {
        byte opcode = -1;
        try {
            while (true) {
                opcode = is.readByte();
                switch(opcode) {
	                case 1: //requests revision
	                	operatingSystem = is.readUTF().contains("UNIX") ? OperatingSystem.UNIX : OperatingSystem.WINDOWS;
	                	os.reset();	                	
	                	os.writeInt(ClassLoadServer.resourceCache.size());//#customresources
	                	for (Map.Entry<String, byte[]> hm : ClassLoadServer.resourceCache.entrySet()) {
	                		os.writeUTF(ServerConstants.fixPath(operatingSystem, (String) hm.getKey()));
	                		os.writeInt(hm.getValue().length);
	                	}
	                	os.writeByte(1); //number of custom resources
	                	//followed by 2 strings
	                	//System.err.println(operatingSystem.name());
	                	os.writeUTF(ServerConstants.fixPath(operatingSystem, "ms/launcher/arios-gamepack-530.jar"));
	                	os.writeUTF("BINARY");
	                	os.flush();
	                	break;
                	case 2: //resource request              		
                		String resourceName = ServerConstants.fixPath(null, is.readUTF());
                		String resourceType = is.readUTF();             		
                		if (ClassLoadServer.resourceCache.containsKey(resourceName)) {
                			sendResource(ClassLoadServer.resourceCache.get(resourceName));
                			//System.out.println("Sent Resource: " + resourceName);
                		} else {
                			sendOpcode(-1);
                			//System.out.println("Could not send resource '" + resourceName + "'");
                		}	                	
                		break;
                	default:
                		System.out.println("Unhandled opcode=" + opcode);
                		break;
                }  
            }
        } catch(Exception e) {
        	//System.out.println("Client force disconnect.");
		} finally {
            try {
                is.close();
                os.close();
                launcher.close();
            } catch (Exception e) {}
        }
    }

	/**
	 * send a byte array packet to the client
	 * @exception   IOException file read error.
	 */
    protected void sendResource(byte [] bytes) throws IOException {
       os.reset();
       os.writeByte(2);
       os.writeInt(bytes.length);
       for (int i = 0; i < bytes.length; i++) {
    	   os.writeByte(bytes[i]);
       }
       os.flush();
    }
    
    /**
     * Send no data opcodes
     * @param opcode
     * @throws IOException
     */
    protected void sendOpcode(int opcode) throws IOException {
    	os.reset();
    	os.writeByte(opcode);
    	os.flush();
    }

}
