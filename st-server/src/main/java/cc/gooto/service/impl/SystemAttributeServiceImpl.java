package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.SystemAttributeTest;
import cc.gooto.mapper.SystemAttributeMapper;
import cc.gooto.service.SystemAttributeService;

@Service
public class SystemAttributeServiceImpl extends ServiceImpl<SystemAttributeMapper, SystemAttributeTest>
		implements SystemAttributeService {

}
