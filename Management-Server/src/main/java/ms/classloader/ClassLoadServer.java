package ms.classloader;

import ms.Management;
import ms.ServerConstants;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.HashMap;

/**
 * ClassLoadServer
 * @author Clayton Williams (Hope)
 */
public class ClassLoadServer extends Thread {
	
	/**
	 * Listening port
	 */
    private static final int PORT = 5050;
    
    /**
     * serverSocket for incoming connections
     */
    private ServerSocket serverSocket = null;

    /**
     * Holds classes and resources already added in the server
     */
    protected static HashMap<String, byte[]> resourceCache = new HashMap<String, byte[]>();

	/**
	 * New socket
	 * @throws UnknownHostException
	 * @throws IOException
	 */
    public ClassLoadServer() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
   
     
    /**
     * Listen for incoming connections
     */
    @Override
    public void run() {
    	resetResourceCache();
       // System.out.println("Listening on port " + PORT + "...");
		while (Management.active) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                //System.out.println("New Connection from : " + clientSocket.getInetAddress());
                WorkerThread wcs  = new WorkerThread(clientSocket);
                wcs.start();
            } catch (Exception e) {}
		}
    }
    
    /**
     * Loads resources recursively from a starting root directory and adds the file bytes to our cache
     * @param path
     * @param pathRoot
     */
    private static void loadResources(String path, String pathRoot) {
    	try {  		
    		path = ServerConstants.fixPath(null, path);
    		pathRoot = ServerConstants.fixPath(null, pathRoot);
    		File root = new File(path);
    		File[] list = root.listFiles();
    		if (list == null) return;
    		for (File f : list) {
    			if (f.isDirectory()) {
    				loadResources(f.getAbsolutePath(), pathRoot);
    			} else {
    				if (f.exists()) {
	    				if (!f.getName().startsWith(".")) {
	    					byte[] bytes = Files.readAllBytes(f.toPath());
	    					String name = ServerConstants.fixPath(null, f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf(pathRoot)));
	    					//System.out.println("[ClassLoadServer] Loaded resource '" + f.getName() + 
	    						//	"' Size: " + NumberFormat.getInstance().format(bytes.length) + " bytes");
	    					resourceCache.put(name, bytes); 			
	    				}
    				}
    			}  				
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Empties the class cache and loads resources
     */
    public static void resetResourceCache() {
    	resourceCache.clear();
    	//loadResources("bin/org/keldagrim/launcher/", "org/arios/launcher/");
    	//loadResources("resources/", "org/keldagrim/launcher/");
    	//System.out.println("Loaded all resources!");
    }
  
}
