package cc.gooto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import cc.gooto.base.BaseInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_usb")
public class UsbTest extends BaseInfo {

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private int id;

	/**
	 * 接入设备标识
	 */
	private String mark;
}
