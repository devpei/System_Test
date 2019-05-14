package cc.gooto.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.gooto.base.ResultResponse;
import cc.gooto.service.DevicesService;

@RestController
@RequestMapping(value = "dashboard")
public class DashboardController {

	@Autowired
	private DevicesService deviceService;

	@GetMapping(value = "baseStatistics")
	public ResultResponse<Object> baseStatistics() {
		Map<String, Object> data = new HashMap<String, Object>();
		int todayTestNumber = deviceService.todayTestNumber();
		int todayFailNumber = deviceService.todayFailNumber();
		if (todayTestNumber == 0) {
			data.put("todayTestNumber", 0);
			data.put("todayFailNumber", 0);
			data.put("successRate", 0);
		} else {
			double successRate = new BigDecimal((double) (todayTestNumber - todayFailNumber) / (double) todayTestNumber)
					.setScale(2, BigDecimal.ROUND_UP).doubleValue();
			data.put("todayTestNumber", todayTestNumber);
			data.put("todayFailNumber", todayFailNumber);
			data.put("successRate", successRate);
		}

		return ResultResponse.success(data);
	}

	/**
	 * 产量统计
	 * 
	 * @param cycle
	 * @param type
	 * @return
	 */
	@GetMapping(value = "chartData")
	public ResultResponse<Object> chartData(
			@RequestParam(name = "cycle", required = false, defaultValue = "7") int cycle,
			@RequestParam(name = "type", required = false, defaultValue = "yield") String type) {
		return ResultResponse.success(deviceService.yieldChartData(cycle));
	}

	/**
	 * 产量分布
	 * 
	 * @param cycle
	 * @return
	 */
	@GetMapping(value = "profileOption")
	public ResultResponse<Object> profileOption(
			@RequestParam(name = "cycle", required = false, defaultValue = "7") int cycle) {
		int testNumber = deviceService.testNumber(cycle);
		int failNumber = deviceService.failNumber(cycle);
		int successNumber = testNumber - failNumber;

		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();

		Map<String, Object> success = new HashMap<String, Object>();
		success.put("name", "成功");
		success.put("value", successNumber);

		Map<String, Object> fail = new HashMap<String, Object>();
		fail.put("name", "失败");
		fail.put("value", failNumber);

		map.add(success);
		map.add(fail);

		return ResultResponse.success(map);
	}
}
