package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.CameraTest;
import cc.gooto.mapper.CameraMapper;
import cc.gooto.service.CameraService;

@Service
public class CameraServiceImpl extends ServiceImpl<CameraMapper, CameraTest> implements CameraService {

}
