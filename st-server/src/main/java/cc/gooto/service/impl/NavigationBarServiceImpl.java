package cc.gooto.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cc.gooto.entity.NavigationBarTest;
import cc.gooto.mapper.NavigationBarMapper;
import cc.gooto.service.NavigationBarService;

@Service
public class NavigationBarServiceImpl extends ServiceImpl<NavigationBarMapper, NavigationBarTest>
		implements NavigationBarService {

}
