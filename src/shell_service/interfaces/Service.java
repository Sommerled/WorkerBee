package shell_service.interfaces;

public interface Service {
	/**
	 * Sets Listener <l> to be the Object's listener
	 * A service should have only one service listener
	 * @param l
	 */
	public void setListener(ServiceListener l);
	
	/**
	 * returns the current listener
	 * @return
	 */
	public ServiceListener getListener();
	
	/**
	 * returns the name of the service
	 * @return
	 */
	public String getServiceName();
	
	/**
	 * Executes the service
	 */
	public void run();
}
