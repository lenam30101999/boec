package com.spring.boec.exceptions;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseResponse {
  public final Object data;
  public final Object message;
  public final Object statusCode;
  public final Object description;
}
