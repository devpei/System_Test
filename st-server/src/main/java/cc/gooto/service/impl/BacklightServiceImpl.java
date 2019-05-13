package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.BacklightTest;
import cc.gooto.mapper.BacklightMapper;
import cc.gooto.service.BacklightService;

@Service
public class BacklightServiceImpl extends ServiceImpl<BacklightMapper, BacklightTest> implements BacklightService {

}
