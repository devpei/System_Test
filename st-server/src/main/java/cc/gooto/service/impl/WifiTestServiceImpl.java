package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.WifiTest;
import cc.gooto.mapper.WifiTestMapper;
import cc.gooto.service.WifiTestService;

@Service
public class WifiTestServiceImpl extends ServiceImpl<WifiTestMapper, WifiTest> implements WifiTestService {

}
