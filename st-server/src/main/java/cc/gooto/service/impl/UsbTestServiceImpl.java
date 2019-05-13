package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.UsbTest;
import cc.gooto.mapper.UsbTestMapper;
import cc.gooto.service.UsbTestService;

@Service
public class UsbTestServiceImpl extends ServiceImpl<UsbTestMapper, UsbTest> implements UsbTestService {

}
