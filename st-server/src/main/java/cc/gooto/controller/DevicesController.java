package cc.gooto.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cc.gooto.base.ExcelUtils;
import cc.gooto.base.ResultResponse;
import cc.gooto.config.GlobalAttribute;
import cc.gooto.entity.Devices;
import cc.gooto.entity.vo.TestResult;
import cc.gooto.service.DevicesService;

@RestController
@RequestMapping(value = "devices")
public class DevicesController {

	@Autowired
	private DevicesService deviceService;

	@Autowired
	private ExcelUtils excelUtils;

	@Autowired
	private GlobalAttribute globalAttribute;

	/**
	 * 分页设备信息
	 * 
	 * @param current      当前页，默认1
	 * @param size         每页数量，默认10
	 * @param serialNumber 串号
	 * @param devicesModel 设备型号
	 * @param userCode     测试用户
	 * @param startDate    开始时间段
	 * @param endDate      结束时间段
	 * @return
	 * @throws ParseException
	 */
	@GetMapping(value = "page")
	public ResultResponse<Object> page(@RequestParam(name = "current", defaultValue = "1") int current,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "serialNumber", required = false) String serialNumber,
			@RequestParam(name = "devicesModel", required = false) String devicesModel,
			@RequestParam(name = "userCode", required = false) String userCode,
			@RequestParam(name = "startDate", required = false) String startDate,
			@RequestParam(name = "endDate", required = false) String endDate) throws ParseException {
		Page<Devices> page = new Page<Devices>(current, size);
		QueryWrapper<Devices> queryWrapper = new QueryWrapper<Devices>();
		queryWrapper.orderByDesc("id");
		if (!StringUtils.isEmpty(serialNumber)) {
			queryWrapper.like("serial_number", serialNumber);
		}

		if (!StringUtils.isEmpty(devicesModel)) {
			queryWrapper.eq("devices_model", devicesModel);
		}

		if (!StringUtils.isEmpty(userCode)) {
			queryWrapper.eq("user_code", userCode);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (!StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)) {
			// 大于等于
			queryWrapper.ge("test_date", sdf.parse(startDate));
		}

		if (StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
			// 小于等于
			queryWrapper.le("test_date", sdf.parse(endDate));
		}

		if (!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
			// 两者之间
			queryWrapper.between("test_date", sdf.parse(startDate), sdf.parse(endDate));
		}

		deviceService.page(page, queryWrapper);
		return ResultResponse.success(page);
	}

	/**
	 * 测试结果上报
	 * 
	 * @param object
	 * @return
	 */
	@PostMapping(value = "resultTest")
	public ResultResponse<Object> resultTest(@RequestBody TestResult testResult) {
		System.out.println(testResult);
		QueryWrapper<Devices> queryWrapper = new QueryWrapper<Devices>();
		queryWrapper.eq("serial_number", testResult.getDevices().getSerialNumber());
		Devices one = deviceService.getOne(queryWrapper);
		if (!ObjectUtils.isEmpty(one)) {
			if (testResult.isForceUpdate()) {
				boolean updateTestResult = deviceService.updateTestResult(one, testResult);
				return updateTestResult ? ResultResponse.success(testResult) : ResultResponse.error("强制写入失败");
			} else {
				return ResultResponse.error("该串号已经使用");
			}
		} else {
			// 故意塞值，防止非法手段中途拼装数据，即使是不合法数据，但是能保证数据唯一
			testResult.getDevices().setId(null);
			boolean saveTestResult = deviceService.saveTestResult(testResult);
			return saveTestResult ? ResultResponse.success(testResult) : ResultResponse.error("保存失败");
		}
	}

	/**
	 * 查看测试记录详情
	 * 
	 * @param id 设备主键
	 * @return
	 */
	@GetMapping(value = "testResult/{id}")
	public ResultResponse<Object> testResult(@PathVariable int id) {
		return ResultResponse.success(deviceService.getTestResultByDevices(id));
	}

	/**
	 * 生成Excel表格Base64方式
	 * 
	 * @param deviceModel 设备型号
	 * @param userCode    工号
	 * @param startDate   开始时间
	 * @param endDate     结束时间
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@GetMapping(value = "generateExcel")
	public ResultResponse<Object> generateExcel(
			@RequestParam(name = "deviceModel", required = false) String deviceModel,
			@RequestParam(name = "userCode", required = false) String userCode,
			@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate,
			HttpServletResponse response) throws ParseException, IOException {
		QueryWrapper<Devices> queryWrapper = new QueryWrapper<Devices>();
		if (!StringUtils.isEmpty(deviceModel)) {
			queryWrapper.like("devices_model", deviceModel);
		}
		if (!StringUtils.isEmpty(userCode)) {
			queryWrapper.eq("user_code", userCode);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (!StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)) {
			// 大于等于
			queryWrapper.ge("test_date", sdf.parse(startDate));
		}

		if (StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
			// 小于等于
			queryWrapper.le("test_date", sdf.parse(endDate));
		}

		if (!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
			// 两者之间
			queryWrapper.between("test_date", sdf.parse(startDate), sdf.parse(endDate));
		}
		List<Devices> devicesList = deviceService.list(queryWrapper);
		if (ObjectUtils.isEmpty(devicesList)) {
			return ResultResponse.error("当前条件下无数据，无需导出。");
		}
		List<TestResult> testResults = new ArrayList<TestResult>();

		for (Devices devices : devicesList) {
			testResults.add(deviceService.getTestResultByDevices(devices.getId()));
		}

		String excelFile = excelUtils.generateExcel(deviceModel, userCode, new String[] { startDate, endDate },
				testResults);
		String fileEncode = null;
		if (excelFile != null) {
			// 定义缓存大小
			byte[] cache = new byte[1024];
			FileInputStream fis = new FileInputStream(globalAttribute.getExcelOutPath() + excelFile);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while (fis.read(cache) != -1) {
				baos.write(cache);
			}
			fileEncode = Base64.getEncoder().encodeToString(baos.toByteArray());
			baos.close();
			fis.close();
			return ResultResponse.success(fileEncode);
		} else {
			return ResultResponse.error("失败");
		}
	}

	/**
	 * 生成Excel表格流传输方式
	 * 
	 * @param deviceModel 设备型号
	 * @param userCode    工号
	 * @param startDate   开始时间
	 * @param endDate     结束时间
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@GetMapping(value = "generateExcel1")
	public void generateExcel1(@RequestParam(name = "deviceModel", required = false) String deviceModel,
			@RequestParam(name = "userCode", required = false) String userCode,
			@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate,
			HttpServletResponse response) throws ParseException, IOException {
		QueryWrapper<Devices> queryWrapper = new QueryWrapper<Devices>();
		if (!StringUtils.isEmpty(deviceModel)) {
			queryWrapper.like("devices_model", deviceModel);
		}
		if (!StringUtils.isEmpty(userCode)) {
			queryWrapper.eq("user_code", userCode);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (!StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)) {
			// 大于等于
			queryWrapper.ge("test_date", sdf.parse(startDate));
		}

		if (StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
			// 小于等于
			queryWrapper.le("test_date", sdf.parse(endDate));
		}

		if (!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {
			// 两者之间
			queryWrapper.between("test_date", sdf.parse(startDate), sdf.parse(endDate));
		}
		List<Devices> devicesList = deviceService.list(queryWrapper);
		if (ObjectUtils.isEmpty(devicesList)) {
		}
		List<TestResult> testResults = new ArrayList<TestResult>();

		for (Devices devices : devicesList) {
			testResults.add(deviceService.getTestResultByDevices(devices.getId()));
		}

		String excelFile = excelUtils.generateExcel(deviceModel, userCode, new String[] { startDate, endDate },
				testResults);

		if (excelFile != null) {
			File f = new File(globalAttribute.getExcelOutPath() + excelFile);
			// 配置文件下载
			response.setCharacterEncoding("UTF-8");
			response.setHeader("content-type", "application/octet-stream;charset=UTF-8");
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.addHeader("Content-Length", String.valueOf(f.length()));
			// 下载文件能正常显示中文
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excelFile, "UTF-8"));
			ServletOutputStream outputStream = response.getOutputStream();

			// 定义缓存大小
			byte[] cache = new byte[1024];
			FileInputStream fis = new FileInputStream(f);

			while (fis.read(cache) != -1) {
				outputStream.write(cache);
			}

			fis.close();
		}
	}

}
