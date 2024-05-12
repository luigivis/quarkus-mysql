package com.example.service;

import com.example.dto.request.ClientCreateRequestDto;
import com.example.entity.ClientEntity;
import com.example.repository.ClientRepository;
import com.example.utils.GenericResponse;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@ApplicationScoped
public class ClientService {

  @Autowired private ClientRepository repository;

  public GenericResponse<List<ClientEntity>> findAll() {
    var findAll = repository.findAll();
    if (findAll.isEmpty()) return new GenericResponse<>(RestResponse.Status.NOT_FOUND);
    return new GenericResponse<>(RestResponse.Status.OK, findAll);
  }

  public GenericResponse<ClientEntity> save(ClientCreateRequestDto clientCreateRequestDto) {
    var save = repository.save(new ClientEntity(clientCreateRequestDto));
    return new GenericResponse<>(RestResponse.Status.CREATED, save);
  }

  public GenericResponse<Object> deleteByUuid(UUID uuid) {
    repository.deleteById(uuid);
    return new GenericResponse<>(RestResponse.Status.OK);
  }

  public GenericResponse<ClientEntity> findById(UUID uuid) {
    var result = repository.findById(uuid);
    return result
        .map(clientEntity -> new GenericResponse<>(RestResponse.Status.OK, clientEntity))
        .orElseGet(() -> new GenericResponse<>(RestResponse.Status.NOT_FOUND));
  }
}
