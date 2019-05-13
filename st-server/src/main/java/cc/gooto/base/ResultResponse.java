package cc.gooto.base;

import lombok.Data;

/**
 * 统一响应
 * 
 * @author peiqianlei
 *
 * @param <T>
 */
@Data
public class ResultResponse<T> {

	private int result;

	private String message;

	private T data;

	/**
	 * 成功
	 * 
	 * @param data
	 * @return
	 */
	public static ResultResponse<Object> success(Object data) {
		ResultResponse<Object> re = new ResultResponse<>();
		re.setResult(StatusCode.SUCCESS.getCode());
		re.setMessage(StatusCode.SUCCESS.getMessage());
		re.setData(data);
		return re;
	}

	/**
	 * 普通错误
	 * 
	 * @param message
	 * @return
	 */
	public static ResultResponse<Object> error(String message) {
		ResultResponse<Object> re = new ResultResponse<>();
		re.setResult(StatusCode.ERROR.getCode());
		re.setMessage(message);
		re.setData(null);
		return re;
	}

	/**
	 * session失效
	 * 
	 * @return
	 */
	public static ResultResponse<Object> sessionInvalid() {
		ResultResponse<Object> re = new ResultResponse<>();
		re.setResult(StatusCode.SESSION_INVALID.getCode());
		re.setMessage(StatusCode.SESSION_INVALID.getMessage());
		re.setData(null);
		return re;
	}

}
