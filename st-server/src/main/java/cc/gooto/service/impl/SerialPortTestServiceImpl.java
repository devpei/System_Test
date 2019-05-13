package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.SerialPortTest;
import cc.gooto.mapper.SerialPortTestMapper;
import cc.gooto.service.SerialPortTestService;

@Service
public class SerialPortTestServiceImpl extends ServiceImpl<SerialPortTestMapper, SerialPortTest>
		implements SerialPortTestService {

}
