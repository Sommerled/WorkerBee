package shell_service.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;

public interface ServiceListener {
	/**
	 * Returns the Socket's InputStream
	 * @return
	 * @throws IOException 
	 */
	public InputStream getInputStream() throws IOException;
	
	/**
	 * Reads a line of text from the Socket
	 * @return
	 * @throws SocketTimeoutException 
	 */
	public String readLine() throws SocketTimeoutException;
	
	
	/**
	 * Reads the 1st 1024 bytes of data on the input buffer into
	 * a string.
	 * 
	 * @param charType the type of characters that
	 *        are being received like UTF-8
	 * 
	 * @return
	 * @throws SocketTimeoutException 
	 */
	public String readStream(String charType) throws SocketTimeoutException;

	/**
	 * Reads from the input buffer into
	 * a string until input is shutdown.
	 * 
	 * @param charType the type of characters that
	 *        are being received like UTF-8
	 * 
	 * @return
	 * @throws SocketTimeoutException 
	 */
	public String readFullStream(String charType) throws SocketTimeoutException;
	
	/**
	 * Returns the number of bytes ready for input
	 * @return
	 */
	public int available();
	
	/**
	 * Writes a line of text to a Socket
	 * @param str
	 */
	public void writeLine(String str);
	
	/**
	 * Closes the socket connection
	 * @throws IOException 
	 */
	public void closeConnection() throws IOException;
}
