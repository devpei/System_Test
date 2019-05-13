package cc.gooto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import cc.gooto.base.BaseInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 容量测试
 * 
 * @author peiqianlei
 *
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_volume")
public class VolumeTest extends BaseInfo {

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private int id;
}
