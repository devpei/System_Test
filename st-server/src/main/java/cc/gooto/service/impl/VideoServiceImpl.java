package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.VideoTest;
import cc.gooto.mapper.VideoMapper;
import cc.gooto.service.VideoService;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, VideoTest> implements VideoService {

}
