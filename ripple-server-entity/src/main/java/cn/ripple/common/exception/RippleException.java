package cn.ripple.common.exception;

import lombok.Data;

@Data
public class RippleException extends RuntimeException {

    private String msg;

    public RippleException(String msg){
        super(msg);
        this.msg = msg;
    }
}
