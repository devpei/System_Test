package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.FourGTest;
import cc.gooto.mapper.FourGMapper;
import cc.gooto.service.FourGService;

@Service
public class FourGServiceImpl extends ServiceImpl<FourGMapper, FourGTest> implements FourGService {

}
