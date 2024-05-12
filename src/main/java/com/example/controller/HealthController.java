package com.example.controller;

import com.example.utils.GenericResponse;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestResponse;

import static com.example.utils.GenericResponse.GenerateHttpResponse;

@Slf4j
@Path("/health")
public class HealthController {

  @GET
  public RestResponse<GenericResponse<Object>> hello() {
    log.info("UP");
    return GenerateHttpResponse(new GenericResponse<>(RestResponse.Status.OK, "UP"));
  }
}
