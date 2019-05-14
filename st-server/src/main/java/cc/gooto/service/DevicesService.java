package cc.gooto.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import cc.gooto.entity.Devices;
import cc.gooto.entity.vo.ChartData;
import cc.gooto.entity.vo.TestResult;

public interface DevicesService extends IService<Devices> {

	/**
	 * 保存测试结果
	 * 
	 * @param testResult
	 * @return
	 */
	boolean saveTestResult(TestResult testResult);

	/**
	 * 更新测试结果
	 * 
	 * @param devices
	 * @param testResult
	 * @return
	 */
	boolean updateTestResult(Devices devices, TestResult testResult);

	/**
	 * 查找设备的所有测试记录
	 * 
	 * @param id
	 * @return
	 */
	TestResult getTestResultByDevices(Integer id);

	/**
	 * 今天测试的数量
	 * 
	 * @return
	 */
	int todayTestNumber();

	/**
	 * 今天失败的数量
	 * 
	 * @return
	 */
	int todayFailNumber();

	/**
	 * 查询周期范围内的测试总数量
	 * 
	 * @param cycle
	 * @return
	 */
	int testNumber(int cycle);

	/**
	 * 查询周期范围内的失败的数量
	 * 
	 * @param cycle
	 * @return
	 */
	int failNumber(int cycle);

	/**
	 * 产量查询
	 * 
	 * @param cycle 周期（天）
	 * @return
	 */
	List<ChartData> yieldChartData(int cycle);
}
