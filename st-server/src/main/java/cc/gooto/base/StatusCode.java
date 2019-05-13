package cc.gooto.base;

/**
 * 状态码
 * 
 * @author peiqianlei
 *
 */
public enum StatusCode {

	SUCCESS(1, "成功"),

	ERROR(0, "错误"),

	SESSION_INVALID(100, "登录已过期，请重新登录");

	private int code;

	private String message;

	private StatusCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
