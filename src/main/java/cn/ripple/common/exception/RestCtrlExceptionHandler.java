package cn.ripple.common.exception;


import cn.ripple.common.utils.ResultUtil;
import cn.ripple.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Exrickx
 */
@Slf4j
@RestControllerAdvice
public class RestCtrlExceptionHandler {

    @ExceptionHandler(RippleException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleXCloudException(RippleException e) {
        String errorMsg="Ripple exception";
        if (e!=null){
            errorMsg=e.getMsg();
            log.warn(e.toString());
        }
        return new ResultUtil<>().setErrorMsg(500, errorMsg);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(Exception e) {
        String errorMsg="Exception";
        if (e!=null){
            errorMsg=e.getMessage();
            log.warn(e.toString());
        }
        return new ResultUtil<>().setErrorMsg(500, errorMsg);
    }
}