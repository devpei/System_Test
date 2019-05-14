package cc.gooto.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.BacklightTest;
import cc.gooto.entity.CameraTest;
import cc.gooto.entity.CanTest;
import cc.gooto.entity.Devices;
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
import cc.gooto.entity.vo.ChartData;
import cc.gooto.entity.vo.TestRecords;
import cc.gooto.entity.vo.TestResult;
import cc.gooto.mapper.DevicesMapper;
import cc.gooto.service.BacklightService;
import cc.gooto.service.CameraService;
import cc.gooto.service.CanService;
import cc.gooto.service.DevicesService;
import cc.gooto.service.EthernetTestService;
import cc.gooto.service.FourGService;
import cc.gooto.service.GpioServcie;
import cc.gooto.service.HdmiService;
import cc.gooto.service.NavigationBarService;
import cc.gooto.service.PressureService;
import cc.gooto.service.RtcService;
import cc.gooto.service.SdCardService;
import cc.gooto.service.SerialPortTestService;
import cc.gooto.service.SimService;
import cc.gooto.service.SystemAttributeService;
import cc.gooto.service.UsbTestService;
import cc.gooto.service.VideoService;
import cc.gooto.service.VolumeService;
import cc.gooto.service.WifiTestService;

@Service
public class DevicesServiceImpl extends ServiceImpl<DevicesMapper, Devices> implements DevicesService {

	@Autowired
	private DevicesMapper devicesMapper;

	@Autowired
	private WifiTestService wifiService;

	@Autowired
	private UsbTestService usbService;

	@Autowired
	private EthernetTestService ethService;

	@Autowired
	private SerialPortTestService sptService;

	@Autowired
	private CanService canService;

	@Autowired
	private FourGService fourGService;

	@Autowired
	private SdCardService sdServcie;

	@Autowired
	private HdmiService hdmiService;

	@Autowired
	private CameraService cameraService;

	@Autowired
	private VideoService videoService;

	@Autowired
	private GpioServcie gpioService;

	@Autowired
	private PressureService pressureService;

	@Autowired
	private RtcService rtcService;

	@Autowired
	private VolumeService volumeService;

	@Autowired
	private BacklightService backlightService;

	@Autowired
	private NavigationBarService nbService;

	@Autowired
	private SystemAttributeService saService;

	@Autowired
	private SimService simServie;

	@Override
	public boolean saveTestResult(TestResult testResult) {
		Devices devices = testResult.getDevices();

		boolean saveOk = true;

		boolean saveDevices = this.saveOrUpdate(devices);

		if (saveDevices && !ObjectUtils.isEmpty(testResult.getTestRrcords())) {
			WifiTest wifi = testResult.getTestRrcords().getWifi();
			if (!ObjectUtils.isEmpty(wifi)) {
				wifi.setDevices(devices.getId());
				saveOk = saveOk && wifiService.save(wifi);
			}
			List<UsbTest> usbList = testResult.getTestRrcords().getUsbList();
			if (!ObjectUtils.isEmpty(usbList)) {
				for (UsbTest usbTest : usbList) {
					usbTest.setDevices(devices.getId());
				}
				saveOk = saveOk && usbService.saveBatch(usbList);
			}
			EthernetTest ethernet = testResult.getTestRrcords().getEthernet();
			if (!ObjectUtils.isEmpty(ethernet)) {
				ethernet.setDevices(devices.getId());
				saveOk = saveOk && ethService.save(ethernet);
			}
			List<SerialPortTest> serialPortList = testResult.getTestRrcords().getSerialPortList();
			if (!ObjectUtils.isEmpty(serialPortList)) {
				for (SerialPortTest serialPortTest : serialPortList) {
					serialPortTest.setDevices(devices.getId());
				}
				saveOk = saveOk && sptService.saveBatch(serialPortList);
			}
			CanTest can = testResult.getTestRrcords().getCan();
			if (!ObjectUtils.isEmpty(can)) {
				can.setDevices(devices.getId());
				saveOk = saveOk && canService.save(can);
			}
			FourGTest fourG = testResult.getTestRrcords().getFourG();
			if (!ObjectUtils.isEmpty(fourG)) {
				fourG.setDevices(devices.getId());
				saveOk = saveOk && fourGService.save(fourG);
			}
			SdCardTest sdCard = testResult.getTestRrcords().getSdCard();
			if (!ObjectUtils.isEmpty(sdCard)) {
				sdCard.setDevices(devices.getId());
				saveOk = saveOk && sdServcie.save(sdCard);
			}
			HdmiTest hdmi = testResult.getTestRrcords().getHdmi();
			if (!ObjectUtils.isEmpty(hdmi)) {
				hdmi.setDevices(devices.getId());
				saveOk = saveOk && hdmiService.save(hdmi);
			}
			CameraTest camera = testResult.getTestRrcords().getCamera();
			if (!ObjectUtils.isEmpty(camera)) {
				camera.setDevices(devices.getId());
				saveOk = saveOk && cameraService.save(camera);
			}
			VideoTest video = testResult.getTestRrcords().getVideo();
			if (!ObjectUtils.isEmpty(video)) {
				video.setDevices(devices.getId());
				saveOk = saveOk && videoService.save(video);
			}
			GpioTest gpio = testResult.getTestRrcords().getGpio();
			if (!ObjectUtils.isEmpty(gpio)) {
				gpio.setDevices(devices.getId());
				saveOk = saveOk && gpioService.save(gpio);
			}
			PressureTest pressure = testResult.getTestRrcords().getPressure();
			if (!ObjectUtils.isEmpty(pressure)) {
				pressure.setDevices(devices.getId());
				saveOk = saveOk && pressureService.save(pressure);
			}
			RtcTest rtc = testResult.getTestRrcords().getRtc();
			if (!ObjectUtils.isEmpty(rtc)) {
				rtc.setDevices(devices.getId());
				saveOk = saveOk && rtcService.save(rtc);
			}
			VolumeTest volume = testResult.getTestRrcords().getVolume();
			if (!ObjectUtils.isEmpty(volume)) {
				volume.setDevices(devices.getId());
				saveOk = saveOk && volumeService.save(volume);
			}
			BacklightTest backlight = testResult.getTestRrcords().getBacklight();
			if (!ObjectUtils.isEmpty(backlight)) {
				backlight.setDevices(devices.getId());
				saveOk = saveOk && backlightService.save(backlight);
			}
			NavigationBarTest navigationBar = testResult.getTestRrcords().getNavigationBar();
			if (!ObjectUtils.isEmpty(navigationBar)) {
				navigationBar.setDevices(devices.getId());
				saveOk = saveOk && nbService.save(navigationBar);
			}
			SystemAttributeTest systemAttribute = testResult.getTestRrcords().getSystemAttribute();
			if (!ObjectUtils.isEmpty(systemAttribute)) {
				systemAttribute.setDevices(devices.getId());
				saveOk = saveOk && saService.save(systemAttribute);
			}
			SimTest sim = testResult.getTestRrcords().getSim();
			if (!ObjectUtils.isEmpty(sim)) {
				sim.setDevices(devices.getId());
				saveOk = saveOk && simServie.save(sim);
			}

			return saveOk;
		}
		return false;
	}

	@Override
	public boolean updateTestResult(Devices devices, TestResult testResult) {
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("devices", devices.getId());
		boolean removeWifi = wifiService.removeByMap(columnMap);
		boolean removeUsb = usbService.removeByMap(columnMap);
		boolean removeEth = ethService.removeByMap(columnMap);
		boolean removeSpt = sptService.removeByMap(columnMap);
		boolean removeCan = canService.removeByMap(columnMap);
		boolean remove4G = fourGService.removeByMap(columnMap);
		boolean removeSd = sdServcie.removeByMap(columnMap);
		boolean removeHdmi = hdmiService.removeByMap(columnMap);
		boolean removeCamera = cameraService.removeByMap(columnMap);
		boolean removeVideo = videoService.removeByMap(columnMap);
		boolean removeGpio = gpioService.removeByMap(columnMap);
		boolean removePressure = pressureService.removeByMap(columnMap);
		boolean removeRtc = rtcService.removeByMap(columnMap);
		boolean removeVolume = volumeService.removeByMap(columnMap);
		boolean removeBl = backlightService.removeByMap(columnMap);
		boolean removeNb = nbService.removeByMap(columnMap);
		boolean removeSa = saService.removeByMap(columnMap);
		boolean removeSim = simServie.removeByMap(columnMap);
		if (removeWifi && removeUsb && removeEth && removeSpt && removeCan && remove4G && removeSd && removeHdmi
				&& removeCamera && removeVideo && removeGpio && removePressure && removeRtc && removeVolume && removeBl
				&& removeNb && removeSa && removeSim) {
			testResult.getDevices().setId(devices.getId());
			return this.saveTestResult(testResult);
		}
		return false;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TestResult getTestResultByDevices(Integer id) {
		TestResult testResult = new TestResult();
		TestRecords testRecords = new TestRecords();
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("devices", id);
		// 设备信息
		testResult.setDevices(this.getById(id));
		// wifi信息
		List<WifiTest> wifi = (List) wifiService.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(wifi)) {
			testRecords.setWifi(wifi.get(0));
		}
		// USB信息
		List<UsbTest> usbList = (List) usbService.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(usbList)) {
			testRecords.setUsbList(usbList);
		}
		// 以太网信息
		List<EthernetTest> ethernet = (List) ethService.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(ethernet)) {
			testRecords.setEthernet(ethernet.get(0));
		}
		// 串口信息
		List<SerialPortTest> sptList = (List) sptService.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(sptList)) {
			testRecords.setSerialPortList(sptList);
		}
		// 总线信息
		List<CanTest> can = (List) canService.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(can)) {
			testRecords.setCan(can.get(0));
		}
		// 4G网络信息
		List<FourGTest> fourG = (List) fourGService.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(fourG)) {
			testRecords.setFourG(fourG.get(0));
		}
		// SD卡内存信息
		List<SdCardTest> sd = (List) sdServcie.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(sd)) {
			testRecords.setSdCard(sd.get(0));
		}
		// 视频接口信息
		List<HdmiTest> hdmi = (List) hdmiService.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(hdmi)) {
			testRecords.setHdmi(hdmi.get(0));
		}
		// 相机信息
		List<CameraTest> camera = (List) cameraService.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(camera)) {
			testRecords.setCamera(camera.get(0));
		}
		// 播放器信息
		List<VideoTest> video = (List) videoService.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(video)) {
			testRecords.setVideo(video.get(0));
		}
		// 通用输入输出信息
		List<GpioTest> gpio = (List) gpioService.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(gpio)) {
			testRecords.setGpio(gpio.get(0));
		}
		// 压力测试
		List<PressureTest> pressure = (List) pressureService.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(pressure)) {
			testRecords.setPressure(pressure.get(0));
		}
		// RTC
		List<RtcTest> rtc = (List) rtcService.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(rtc)) {
			testRecords.setRtc(rtc.get(0));
		}
		// 容量
		List<VolumeTest> volume = (List) volumeService.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(volume)) {
			testRecords.setVolume(volume.get(0));
		}
		// 背光
		List<BacklightTest> backlight = (List) backlightService.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(backlight)) {
			testRecords.setBacklight(backlight.get(0));
		}
		// 导航栏
		List<NavigationBarTest> nb = (List) nbService.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(nb)) {
			testRecords.setNavigationBar(nb.get(0));
		}
		// 系统
		List<SystemAttributeTest> sa = (List) saService.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(sa)) {
			testRecords.setSystemAttribute(sa.get(0));
		}
		// SIM卡热插拔
		List<SimTest> sim = (List) simServie.listByMap(columnMap);
		if (!ObjectUtils.isEmpty(sim)) {
			testRecords.setSim(sim.get(0));
		}
		testResult.setTestRrcords(testRecords);
		return testResult;
	}

	@Override
	public int todayTestNumber() {
		return devicesMapper.todayTestNumber();
	}

	@Override
	public int todayFailNumber() {
		return devicesMapper.todayFailNumber();
	}

	@Override
	public List<ChartData> yieldChartData(int cycle) {
		return devicesMapper.yieldChartData(cycle);
	}

	@Override
	public int testNumber(int cycle) {
		return devicesMapper.testNumber(cycle);
	}

	@Override
	public int failNumber(int cycle) {
		return devicesMapper.failNumber(cycle);
	}
}
