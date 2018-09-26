package cn.ripple.config.protocol;

import cn.ripple.common.exception.RippleException;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Response implements Serializable {
    /** 返回的响应码 为空，说明是正常返回*/
    private Integer status;
    /** 异常信息 */
    private String message;
    /** 时间 */
    private Date timestamp;
    private String error;
    private String path;

    public Response(Integer status,String message,Date timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Response(RippleException rippleException){
        this.status = rippleException.getCode();
        this.message = rippleException.getMessage();
        this.timestamp = new Date();
    }

    public HashMap toMap(){
        HashMap valueMap = new HashMap();
        valueMap.put("status",this.status);
        valueMap.put("message",this.message);
        valueMap.put("timestamp",this.timestamp);
        return valueMap;
    }
}
