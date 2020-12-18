package io.rpcfx.demo.core.exception;

/**
 * @version 1.0
 * @Description 自定义rpcexception
 * @Author honghonghui
 * @Date
 **/
public class RpcfxException extends Exception {
    private String s;
    public RpcfxException(String s){
        this.s= s;
    }
}
