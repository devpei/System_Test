package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.PressureTest;
import cc.gooto.mapper.PressureMapper;
import cc.gooto.service.PressureService;

@Service
public class PressureServiceImpl extends ServiceImpl<PressureMapper, PressureTest> implements PressureService {

}
