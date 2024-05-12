package com.example.utils;

import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;

import jakarta.ws.rs.core.Response;
import lombok.*;
import org.jboss.resteasy.reactive.RestResponse;
import jakarta.ws.rs.core.Response.ResponseBuilder;

import static jakarta.ws.rs.core.Response.ok;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> implements Serializable {

  @JsonbTransient
  private RestResponse.Status status;
  private int code;
  private String message;
  private T data;

  public GenericResponse(RestResponse.Status status) {
    this.status = status;
    this.code = status.getStatusCode();
    this.message = status.getReasonPhrase();
  }

  public GenericResponse(RestResponse.Status status, String message) {
    this.status = status;
    this.code = status.getStatusCode();
    this.message = message;
  }

  public GenericResponse(RestResponse.Status status, String message, int code) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

  public GenericResponse(RestResponse.Status status, T data) {
    this.status = status;
    this.code = status.getStatusCode();
    this.message = status.getReasonPhrase();
    this.data = data;
  }

  public GenericResponse(RestResponse.Status status, String message, T data) {
    this.status = status;
    this.code = status.getStatusCode();
    this.message = message;
    this.data = data;
  }

  public GenericResponse(RestResponse.Status status, String message, int code, T data) {
    this.status = status;
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public static <T> RestResponse<GenericResponse<T>> GenerateHttpResponse(GenericResponse<T> genericResponse){
    return RestResponse.ResponseBuilder.create(genericResponse.status, genericResponse).build();
  }

  public static <T> Response GenerateResponse(GenericResponse<T> genericResponse){
    var b = ok();
    b.status(genericResponse.status);
    b.entity(genericResponse);
    return b.build();
  }
}
