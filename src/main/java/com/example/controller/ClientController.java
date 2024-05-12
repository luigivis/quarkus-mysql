package com.example.controller;

import static com.example.utils.GenericResponse.GenerateHttpResponse;

import com.example.dto.request.ClientCreateRequestDto;
import com.example.entity.ClientEntity;
import com.example.service.ClientService;
import com.example.utils.GenericResponse;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import java.util.List;
import java.util.UUID;

import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestResponse;

@Slf4j
@Path("/api/v1/client")
public class ClientController {

  @Inject ClientService service;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public RestResponse<GenericResponse<List<ClientEntity>>> findAllClient() {
    var result = service.findAll();
    return GenerateHttpResponse(result);
  }

  @POST
  public RestResponse<GenericResponse<ClientEntity>> save(@Valid ClientCreateRequestDto clientCreateRequestDto) {
    var result = service.save(clientCreateRequestDto);
    return GenerateHttpResponse(result);
  }

  @DELETE
  @NonBlocking
  @Path("/{uuid}")
  public RestResponse<GenericResponse<Object>> delete(@PathParam("uuid") UUID uuid) {
    var result = service.deleteByUuid(uuid);
    return GenerateHttpResponse(result);
  }

  @GET
  @Path("/{uuid}")
  public RestResponse<GenericResponse<ClientEntity>> findByUUID(@PathParam("uuid") UUID uuid) {
    var result = service.findById(uuid);
    return GenerateHttpResponse(result);
  }
}
