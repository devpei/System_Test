package cc.gooto.entity.vo;

import java.util.List;

import cc.gooto.entity.BacklightTest;
import cc.gooto.entity.CameraTest;
import cc.gooto.entity.CanTest;
import cc.gooto.entity.EthernetTest;
import cc.gooto.entity.FourGTest;
import cc.gooto.entity.GpioTest;
import cc.gooto.entity.HdmiTest;
import cc.gooto.entity.NavigationBarTest;
import cc.gooto.entity.PressureTest;
import cc.gooto.entity.RtcTest;
import cc.gooto.entity.SdCardTest;
import cc.gooto.entity.SerialPortTest;
import cc.gooto.entity.SimTest;
import cc.gooto.entity.SystemAttributeTest;
import cc.gooto.entity.UsbTest;
import cc.gooto.entity.VideoTest;
import cc.gooto.entity.VolumeTest;
import cc.gooto.entity.WifiTest;
import lombok.Data;

/**
 * 测试记录
 * 
 * @author peiqianlei
 *
 */
@Data
public class TestRecords {

	/**
	 * 串口测试结果
	 */
	private List<SerialPortTest> serialPortList;

	/**
	 * usb测试结果
	 */
	private List<UsbTest> usbList;

	/**
	 * wifi测试结果
	 */
	private WifiTest wifi;

	/**
	 * 以太网测试结果
	 */
	private EthernetTest ethernet;

	/**
	 * 总线测试结果
	 */
	private CanTest can;

	/**
	 * 4G网络测试结果
	 */
	private FourGTest fourG;

	/**
	 * 内存卡测试结果
	 */
	private SdCardTest sdCard;

	/**
	 * 视频接口测试结果
	 */
	private HdmiTest hdmi;

	/**
	 * 相机测试结果
	 */
	private CameraTest camera;

	/**
	 * 播放器测试结果
	 */
	private VideoTest video;

	/**
	 * 通用输入输出测试结果
	 */
	private GpioTest gpio;

	/**
	 * 压力测试结果
	 */
	private PressureTest pressure;

	/**
	 * RTC测试结果
	 */
	private RtcTest rtc;

	/**
	 * 容量测试结果
	 */
	private VolumeTest volume;

	/**
	 * 背光测试结果
	 */
	private BacklightTest backlight;

	/**
	 * 状态/导航栏测试结果
	 */
	private NavigationBarTest navigationBar;

	/**
	 * 系统属性测试结果
	 */
	private SystemAttributeTest systemAttribute;

	/**
	 * SIM卡热插拔测试结果
	 */
	private SimTest sim;
}
