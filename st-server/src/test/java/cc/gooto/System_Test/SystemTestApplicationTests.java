package cc.gooto.System_Test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;

import cc.gooto.entity.User;
import cc.gooto.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemTestApplicationTests {
	
	@Autowired
	private UserMapper userMapper;

	@Test
	public void contextLoads() {
		GlobalConfig globalConfig = new GlobalConfig();
		globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
		globalConfig.setAuthor("jobob");
		globalConfig.setOpen(false);
		
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setUrl("jdbc:mysql://192.168.159.188:3306/system_test?useUnicode=true&useSSL=false");
		dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
		dataSourceConfig.setUsername("root");
		dataSourceConfig.setPassword("Qq1138995353!");
	}

}
