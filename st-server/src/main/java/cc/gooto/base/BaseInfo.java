package cc.gooto.base;

import lombok.Data;

@Data
public class BaseInfo {

	/**
	 * 设备
	 */
	public int devices;

	/**
	 * 测试是否通过
	 */
	public boolean success;
}
