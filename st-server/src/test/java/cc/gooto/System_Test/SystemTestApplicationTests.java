package cc.gooto.System_Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cc.gooto.base.ExcelUtils;
import cc.gooto.config.GlobalAttribute;
import cc.gooto.entity.Devices;
import cc.gooto.entity.vo.TestRecords;
import cc.gooto.entity.vo.TestResult;
import cc.gooto.mapper.DevicesMapper;
import cc.gooto.service.DevicesService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemTestApplicationTests {

	@Autowired
	private DevicesMapper devicesMapper;

	@Autowired
	private DevicesService deviceService;

	@Autowired
	private GlobalAttribute globalAttribute;

	@Autowired
	private ExcelUtils excelUtils;

	@Test
	public void contextLoads() {
		int todayTestNumber = devicesMapper.todayTestNumber();
		System.out.println(todayTestNumber);
	}

	@Test
	public void classTest() {
		TestResult testResultByDevices = deviceService.getTestResultByDevices(1);
		TestRecords testRrcords = testResultByDevices.getTestRrcords();
		Class<? extends TestRecords> testRrcordsClass = testRrcords.getClass();
		Field[] fields = testRrcordsClass.getDeclaredFields();
		ObjectMapper om = new ObjectMapper();
		boolean res = true;
		for (Field field : fields) {
			String name = field.getName();
			try {
				Method method = testRrcordsClass
						.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
				if (!ObjectUtils.isEmpty(method.invoke(testRrcords))) {
					if (method.invoke(testRrcords) instanceof List) {
						List<Map<String, Object>> list = om.readValue(om.writeValueAsString(method.invoke(testRrcords)),
								new TypeReference<List<Map<String, Object>>>() {
								});
						for (Map<String, Object> map : list) {
							res = res && (boolean) map.get("success");
						}
					} else {
						Map<String, Object> obj = om.readValue(om.writeValueAsString(method.invoke(testRrcords)),
								new TypeReference<Map<String, Object>>() {
								});
						res = (boolean) obj.get("success");
					}
					System.out.println("==>" + name + ":" + res);
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testExcel() {
		Page<Devices> page = new Page<Devices>(1, 10);
		deviceService.page(page);
		List<TestResult> trs = new ArrayList<TestResult>();
		for (Devices devices : page.getRecords()) {
			trs.add(deviceService.getTestResultByDevices(devices.getId()));
		}
		excelUtils.generateExcel("测试", "测试", new String[] { "测试", "测试" }, trs);
	}

	@Test
	public void globalAttribute() {
		System.out.println("==>全局属性：" + globalAttribute.getExcelModelPath());
	}

}
