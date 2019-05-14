package cc.gooto.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cc.gooto.entity.Devices;
import cc.gooto.entity.vo.ChartData;

public interface DevicesMapper extends BaseMapper<Devices> {

	/**
	 * 今天测试的数量
	 * 
	 * @return
	 */
	@Select("select count(0) from tb_devices where to_days(test_date) = to_days(now())")
	int todayTestNumber();

	/**
	 * 今天测试失败的数量
	 */
	@Select("select count(0) from tb_devices where to_days(test_date) = to_days(now()) and success = 0")
	int todayFailNumber();

	/**
	 * 查询周期范围内的测试总数量
	 * 
	 * @param cycle
	 * @return
	 */
	@Select("select count(0) from tb_devices where date_sub(curdate(), interval #{cycle} day) <= date(test_date)")
	int testNumber(int cycle);

	/**
	 * 查询周期范围内的失败的数量
	 * 
	 * @param cycle
	 * @return
	 */
	@Select("select count(0) from tb_devices where date_sub(curdate(), interval #{cycle} day) <= date(test_date) and success = 0")
	int failNumber(int cycle);

	/**
	 * 产量查询
	 * 
	 * @param cycle 周期（天）
	 * @return
	 */
	List<ChartData> yieldChartData(int cycle);
}
