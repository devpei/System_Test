package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.HdmiTest;
import cc.gooto.mapper.HdmiMapper;
import cc.gooto.service.HdmiService;

@Service
public class HdmiServiceImpl extends ServiceImpl<HdmiMapper, HdmiTest> implements HdmiService {

}
