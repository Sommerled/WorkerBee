package shell_service.shell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

import shell_service.interfaces.ServiceListener;

public class InnerShell implements ServiceListener{
	private Socket s;
	private InputStream is;
	private BufferedReader br;
	private BufferedWriter writer;
	
	public InnerShell(Socket s) throws IOException{
		this.s = s;
		this.is = s.getInputStream();
		this.br = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
		this.writer = new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream()));
	}

	@Override
	public String readLine() throws SocketTimeoutException {
		String str = null;
		
		try {
			System.out.println("InnerShell: Reading message");
			str = this.br.readLine();
			System.out.println("InnerShell Message: " + str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return str;
	}

	@Override
	public String readStream(String charType) throws SocketTimeoutException {
		String str = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();				
			byte[] buffer = new byte[1024];
			int read = 0;
			System.out.println("InnerShell: Reading message");
			if ((read = this.is.read(buffer, 0, buffer.length)) != -1) {
				baos.write(buffer, 0, read);
			}		
			baos.flush();		
			str = new String(baos.toByteArray(), charType);
			System.out.println("InnerShell Message: " + str);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return str;
	}

	@Override
	public void writeLine(String str) {
		if(this.s != null){
			try {
				System.out.println("InnerShell: Sending message");
				this.writer.write(str);
				this.writer.newLine();
				this.writer.flush();
				System.out.println("InnerShell: Wrote message");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void closeConnection() throws IOException {
		this.br.close();
		this.writer.close();
		this.s.close();
		this.s = null;
	}

	@Override
	public String readFullStream(String charType) throws SocketTimeoutException{
		String str = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();				
			byte[] buffer = new byte[1024];
			int read = 0;
			System.out.println("InnerShell: Reading message");
			while ((read = this.is.read(buffer, 0, buffer.length)) != -1) {
				baos.write(buffer, 0, read);
			}		
			baos.flush();		
			str = new String(baos.toByteArray(), charType);
			System.out.println("InnerShell Message: " + str);
			
		} catch (SocketTimeoutException e) {
			//because SocketTimeoutException is an IOException
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return str;
	}

	@Override
	public int available() {
		int ret = 0;
		try {
			ret = this.is.available();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
}
