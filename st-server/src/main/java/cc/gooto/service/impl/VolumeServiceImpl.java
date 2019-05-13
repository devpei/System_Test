package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.VolumeTest;
import cc.gooto.mapper.VolumeMapper;
import cc.gooto.service.VolumeService;

@Service
public class VolumeServiceImpl extends ServiceImpl<VolumeMapper, VolumeTest> implements VolumeService {

}
