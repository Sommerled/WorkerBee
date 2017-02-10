package shell_service.shell;

import java.io.IOException;
import java.net.Socket;

import shell_service.interfaces.Service;

public class OuterShell implements Runnable{
	private Service service;
	private Socket socket;
	private int readTimeout;
	
	public OuterShell(){
		
	}
	
	public OuterShell(Service service, Socket socket){
		this.service = service;
		this.socket = socket;
	}
	
	public void setService(Service service){
		this.service = service;
	}
	
	public void setSocket(Socket socket){
		this.socket = socket;
	}

	@Override
	public void run() {
		InnerShell inner;
		try {
			inner = new InnerShell(this.socket);
			this.service.setListener(inner);
			this.service.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
