package cc.gooto.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "cc.gooto.variable")
@Data
public class GlobalAttribute {

	/**
	 * 表格模板地址
	 */
	private String excelModelPath;

	/**
	 * 表格输出地址
	 */
	private String excelOutPath;
}
