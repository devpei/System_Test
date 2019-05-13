package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.SimTest;
import cc.gooto.mapper.SimMapper;
import cc.gooto.service.SimService;

@Service
public class SimServiceImpl extends ServiceImpl<SimMapper, SimTest> implements SimService {

}
