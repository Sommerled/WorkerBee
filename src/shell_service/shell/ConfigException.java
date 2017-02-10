package shell_service.shell;

public class ConfigException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4433047797690843586L;

	public ConfigException() { super(); }
	public ConfigException(String message) { 
		super(message); 
	}
	
	public ConfigException(String message, Throwable cause) { 
		super(message, cause); 
	}
	
	public ConfigException(Throwable cause) { 
		super(cause); 
	}

}
