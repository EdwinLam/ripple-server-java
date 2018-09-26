package cn.ripple.common.exception;

import cn.ripple.common.exception.RippleException;
import cn.ripple.config.protocol.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * 处理Rest接口请求时的异常
     * @param request
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(RippleException.class)
    @ResponseBody
    public Map<String, Object> restError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return new Response((RippleException) ex).toMap();
    }
}
