package cc.gooto;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

import cc.gooto.config.GlobalAttribute;

@SpringBootApplication
@MapperScan("cc.gooto.mapper")
@EnableConfigurationProperties({ GlobalAttribute.class })
public class SystemTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemTestApplication.class, args);
	}

	/**
	 * 分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

}
