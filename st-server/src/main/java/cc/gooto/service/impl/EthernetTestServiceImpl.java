package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.EthernetTest;
import cc.gooto.mapper.EthernetTestMapper;
import cc.gooto.service.EthernetTestService;

@Service
public class EthernetTestServiceImpl extends ServiceImpl<EthernetTestMapper, EthernetTest>
		implements EthernetTestService {

}
