package shell_service.shell;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import shell_service.interfaces.Service;
import shell_service.interfaces.ServiceFactory;

public class Shell {
	/**
	 * <cfg> needs to have Port, ThreadMax, and Timeout properties
	 */
	private Properties cfg;
	private ServiceFactory factory;
	
	public Shell(){
		
	}
	
	public Shell(Properties cfg, ServiceFactory svc){
		this.cfg = cfg;
		this.factory = svc;
	}
	
	public void setConfig(Properties cfg){
		this.cfg = cfg;
	}
	
	public void setService(ServiceFactory svc){
		this.factory = svc;
	}
	
	public void startService() throws ConfigException, ServiceException, IOException{
		if(this.cfg == null){
			throw new ConfigException("Configuration object is null");
		}else if(!this.cfg.containsKey("Port") && this.cfg.getProperty("Port") == null && this.cfg.getProperty("Port").isEmpty()){
			throw new ConfigException("Port property not set");
		}else if(!this.cfg.containsKey("ThreadMax") && this.cfg.getProperty("ThreadMax") == null && this.cfg.getProperty("ThreadMax").isEmpty()){
			throw new ConfigException("ThreadMax property not set");
		}else if(!this.cfg.containsKey("Timeout") && this.cfg.getProperty("Timeout") == null && this.cfg.getProperty("Timeout").isEmpty()){
			throw new ConfigException("v property not set");
		}
		
		if(this.factory == null){
			throw new ServiceException("Service object is null");
		}
		
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(Integer.parseInt(this.cfg.getProperty("Port")));
		int threadMax = Integer.parseInt(this.cfg.getProperty("ThreadMax"));
		int timeout = Integer.parseInt(this.cfg.getProperty("Timeout"));
		if(threadMax > -1){
			ExecutorService executor = Executors.newFixedThreadPool(threadMax);
			System.out.println("Shell: starting up service");
			while(true){
				Socket socket = serverSocket.accept();
				socket.setSoTimeout(timeout);
				System.out.println("Shell: received conncection");
				Service svc = this.factory.createServiceInstance();
				OuterShell outer = new OuterShell(svc, socket);
				executor.execute(outer);
			}
		}else{
			System.out.println("Shell: starting up service");
			while(true){
				Socket socket = serverSocket.accept();
				socket.setSoTimeout(timeout);
				System.out.println("Shell: received conncection");
				Service svc = this.factory.createServiceInstance();
				OuterShell outer = new OuterShell(svc, socket);
				(new Thread(outer)).start();
			}
		}
	}	
}
