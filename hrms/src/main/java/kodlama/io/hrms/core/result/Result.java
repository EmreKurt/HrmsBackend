package kodlama.io.hrms.core.result;

public class Result {
	boolean success;
	String message;
	
	public Result(boolean success) {
		this.success = success;
	}
	
	public Result(boolean success,String message) {
		this(success);
		this.message = message;
	}
	
	public boolean isSuccess() {
		return this.success;
	}
	
	public String getMessage() {
		return this.message;
	}
}
