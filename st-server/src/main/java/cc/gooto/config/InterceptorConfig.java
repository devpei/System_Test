package cc.gooto.config;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import cc.gooto.base.ResultResponse;

/**
 * 拦截器
 * 
 * @author peiqianlei
 *
 */
public class InterceptorConfig implements HandlerInterceptor {

	/**
	 * 进入controller层之前拦截请求
	 *
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
			throws Exception {
		HttpSession session = httpServletRequest.getSession();
		System.out.println("==>" + session.getAttribute("user"));
		if (!StringUtils.isEmpty(session.getAttribute("user"))) {
			return true;
		} else {
			httpServletResponse.setContentType("application/json");
			ServletOutputStream outputStream = httpServletResponse.getOutputStream();
			outputStream
					.write(new ObjectMapper().writeValueAsString(ResultResponse.sessionInvalid()).getBytes("UTF-8"));
			return false;
		}

	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
	}
}
