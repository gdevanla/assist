package jdepend.textui;

public class ExitException extends SecurityException {
	public final int status;
	public ExitException(int status) 
	{
		this.status = status;
	}
}
