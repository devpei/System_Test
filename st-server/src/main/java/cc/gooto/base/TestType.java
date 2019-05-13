package cc.gooto.base;

/**
 * 测试项
 * 
 * @author peiqianlei
 *
 */
public enum TestType {

	WIFI(0, "wifi"),

	USB(1, "usb"),

	ETHERNET(2, "以太网"),

	SERIALPORT(3, "串口端口"),

	CAN(4, "can总线"),

	FOUR_G(5, "4G"),

	SD_CARD(6, "sd卡"),

	HDMI_SHOW(7, "hdmi显示"),

	CAMERA(8, "相机"),

	VIDEO_PLAY(9, "播放器"),

	GPIO(10, "gpio"),

	STREE_TEST(11, "压力测试"),

	RTC(12, "时钟"),

	VOLUME(13, "容量"),

	BACK_LIGHT(14, "背光"),

	NAVIGATION_BAR(15, "状态导航栏"),

	SYSTEM_ATTRIBUTE(16, "系统属性"),

	SIM(17, "sim卡");

	private int code;

	private String name;

	private TestType(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
