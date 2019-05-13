package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.GpioTest;
import cc.gooto.mapper.GpioMapper;
import cc.gooto.service.GpioServcie;

@Service
public class GpioServiceImpl extends ServiceImpl<GpioMapper, GpioTest> implements GpioServcie {

}
