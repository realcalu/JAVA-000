package io.rpcfx.demo.core.entity;

import lombok.Data;

@Data
public class RpcfxRequest {

  private String serviceClass;

  private String method;

  private Object[] params;

}
