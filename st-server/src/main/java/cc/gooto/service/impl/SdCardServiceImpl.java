package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.SdCardTest;
import cc.gooto.mapper.SdCardMapper;
import cc.gooto.service.SdCardService;

@Service
public class SdCardServiceImpl extends ServiceImpl<SdCardMapper, SdCardTest> implements SdCardService {

}
