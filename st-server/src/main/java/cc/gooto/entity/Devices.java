package cc.gooto.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 设备
 * 
 * @author peiqianlei
 *
 */
@Data
@TableName(value = "tb_devices")
public class Devices {

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * mac地址
	 */
	private String mac;

	/**
	 * 串号
	 */
	private String serialNumber;

	/**
	 * 设备型号
	 */
	private String devicesModel;

	/**
	 * 系统版本
	 */
	private String systemVersion;

	/**
	 * 测试员编号
	 */
	private String userCode;

	/**
	 * 测试日期
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date testDate;

	/**
	 * 测试是否通过
	 */
	private boolean success;
}
