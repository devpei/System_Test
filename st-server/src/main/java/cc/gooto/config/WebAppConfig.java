package cc.gooto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器
 * 
 * @author peiqianlei
 *
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册自定义拦截器，添加拦截路径和排除拦截路径
		registry.addInterceptor(new InterceptorConfig()).excludePathPatterns("/login").excludePathPatterns("/api/login")
				.excludePathPatterns("/devices/resultTest").excludePathPatterns("/api/devices/resultTest")
				.excludePathPatterns("/checkHealth");
	}
}
