package cc.gooto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import cc.gooto.base.BaseInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 通用输入输出测试
 * 
 * @author peiqianlei
 *
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_gpio")
public class GpioTest extends BaseInfo {

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private int id;
}
