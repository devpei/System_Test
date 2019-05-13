package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.CanTest;
import cc.gooto.mapper.CanMapper;
import cc.gooto.service.CanService;

@Service
public class CanServiceImpl extends ServiceImpl<CanMapper, CanTest> implements CanService {

}
