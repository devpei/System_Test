package cc.gooto.base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cc.gooto.config.GlobalAttribute;
import cc.gooto.entity.vo.TestRecords;
import cc.gooto.entity.vo.TestResult;

@Service
public class ExcelUtils {

	@Autowired
	private GlobalAttribute globalAttribute;

	// 此处测试项数组顺序一定和模板测试项顺序一致，并且值与TestRecords的属性名称一致
	private static final String[] TYPES = { "wifi", "ethernet", "serialPortList", "usbList", "backlight", "can",
			"fourG", "sdCard", "hdmi", "camera", "video", "gpio", "pressure", "rtc", "volume", "navigationBar",
			"systemAttribute", "sim" };

	/**
	 * 根据模板生成Excel表格
	 * 
	 * @param deviceModel 设备型号
	 * @param userCode    工号
	 * @param dateRange   时间周期
	 * @param testResults 数据
	 * @return 文件名称
	 */
	public String generateExcel(String deviceModel, String userCode, String[] dateRange, List<TestResult> testResults) {
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(new FileInputStream(globalAttribute.getExcelModelPath()));
			Sheet sheet = wb.getSheetAt(0);

			// 找到第四行的数据，为了补全表头信息
			// 坐标位置（3，0）代表设备型号，（3，6）代表工位，（3，13）代表时间范围
			if (testResults.size() > 0) {
				Row row = sheet.getRow(3);
				if (StringUtils.isEmpty(deviceModel)) {
					deviceModel = "全部";
				}
				if (StringUtils.isEmpty(userCode)) {
					userCode = "全部";
				}
				row.getCell(0).setCellValue(row.getCell(0).getStringCellValue() + deviceModel);
				row.getCell(6).setCellValue(row.getCell(6).getStringCellValue() + userCode);
				row.getCell(13)
						.setCellValue(row.getCell(13).getStringCellValue() + dateRange[0] + " — " + dateRange[1]);
			}

			// 根据模板计算，开始从第8行开始为数据，实际以模板为准
			for (int i = 0; i < testResults.size(); i++) {
				Row row = sheet.createRow(i + 7);
				TestResult testResult = testResults.get(i);
				// 第一条数据串号
				row.createCell(0).setCellValue(testResult.getDevices().getSerialNumber());
				TestRecords testRrcords = testResult.getTestRrcords();
				Class<? extends TestRecords> testRrcordsClass = testRrcords.getClass();
				Field[] fields = testRrcordsClass.getDeclaredFields();
				ObjectMapper om = new ObjectMapper();
				// 当前项是否测试通过
				boolean res = true;
				// 遍历测试项
				// 该方法效率较低，后期优化算法，利用break跳转及缩容。
				// 列偏移量1位
				for (int j = 0; j < ExcelUtils.TYPES.length; j++) {
					for (Field field : fields) {
						String name = field.getName();
						if (ExcelUtils.TYPES[j].equals(name)) {
							Cell createCell = row.createCell(j + 1);
							// 设置文本居中
							createCell.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
							Method method = testRrcordsClass
									.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
							if (!ObjectUtils.isEmpty(method.invoke(testRrcords))) {
								if (method.invoke(testRrcords) instanceof List) {
									List<Map<String, Object>> list = om.readValue(
											om.writeValueAsString(method.invoke(testRrcords)),
											new TypeReference<List<Map<String, Object>>>() {
											});
									for (Map<String, Object> map : list) {
										res = res && (boolean) map.get("success");
									}
								} else {
									Map<String, Object> obj = om.readValue(
											om.writeValueAsString(method.invoke(testRrcords)),
											new TypeReference<Map<String, Object>>() {
											});
									res = (boolean) obj.get("success");
								}
								if (res) {

									createCell.setCellValue("√");
								} else {
									createCell.setCellValue("×");
								}
							} else {
								// 说明该项没有测试
								createCell.setCellValue("-");
							}
						}
					}
				}
			}
			String fileName = new Date().getTime() + ".xlsx";
			wb.write(new FileOutputStream(globalAttribute.getExcelOutPath() + fileName));
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (wb != null) {
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
