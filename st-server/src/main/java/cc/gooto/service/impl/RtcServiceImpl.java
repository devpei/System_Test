package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.RtcTest;
import cc.gooto.mapper.RtcMapper;
import cc.gooto.service.RtcService;

@Service
public class RtcServiceImpl extends ServiceImpl<RtcMapper, RtcTest> implements RtcService {

}
