package com.example.exception;

import static com.example.utils.GenericResponse.GenerateHttpResponse;

import com.example.utils.GenericResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.ext.Provider;
import java.sql.SQLIntegrityConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@Slf4j
@Provider
public class ExceptionHandler {

  @ServerExceptionMapper
  public RestResponse<GenericResponse<Object>> mapException(NotFoundException e) {
    log.error("Error " + e);
    return GenerateHttpResponse(new GenericResponse<>(RestResponse.Status.NOT_FOUND));
  }

  @ServerExceptionMapper
  public RestResponse<GenericResponse<Object>> mapException(
      SQLIntegrityConstraintViolationException e) {
    log.error("Error " + e);
    return GenerateHttpResponse(new GenericResponse<>(RestResponse.Status.CONFLICT));
  }

  @ServerExceptionMapper
  public RestResponse<GenericResponse<Object>> mapException(ConstraintViolationException e) {
    log.error("Error " + e);

    if (e.getConstraintViolations().stream().findAny().isPresent()) {
      return GenerateHttpResponse(
          new GenericResponse<>(
              RestResponse.Status.BAD_REQUEST,
              e.getConstraintViolations().stream().findAny().get().getMessageTemplate()));
    }

    return GenerateHttpResponse(new GenericResponse<>(RestResponse.Status.BAD_REQUEST));
  }

  @ServerExceptionMapper
  public RestResponse<GenericResponse<Object>> mapException(
      org.hibernate.exception.ConstraintViolationException e) {
    log.error("Error " + e);
    return GenerateHttpResponse(
        new GenericResponse<>(
            RestResponse.Status.CONFLICT, "Database conflict, please contact IT department."));
  }
}
