package shell_service.interfaces;

import java.io.IOException;
import java.net.SocketTimeoutException;

public interface ServiceListener {
	/**
	 * Reads a line of text from the Socket
	 * @return
	 * @throws SocketTimeoutException 
	 */
	public String readLine() throws SocketTimeoutException;
	
	
	/**
	 * Reads the 1st set of data on the input buffer into
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
	 * Reads the entire input buffer into
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
