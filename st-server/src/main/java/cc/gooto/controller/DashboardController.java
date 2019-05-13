package cc.gooto.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
