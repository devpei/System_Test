package cc.gooto.mapper;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cc.gooto.entity.Devices;

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
}
