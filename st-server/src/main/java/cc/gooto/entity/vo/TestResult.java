package cc.gooto.entity.vo;

import cc.gooto.entity.Devices;
import lombok.Data;

/**
 * 测试结果
 * 
 * @author peiqianlei
 *
 */
@Data
public class TestResult {

	/**
	 * 设备详情
	 */
	private Devices devices;

	/**
	 * 测试记录
	 */
	private TestRecords testRrcords;

	/**
	 * 是否强制更新
	 */
	private boolean forceUpdate;
}
