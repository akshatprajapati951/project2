package in.co.rays.proj3.exception;

public class DuplicateRecordException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicateRecordException(String msg) {
		super(msg);
	}
}
