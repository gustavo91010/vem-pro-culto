package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igreja;

import java.util.List;
import java.util.Optional;

import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.IgrejaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class IgrejaJpaRepositoryImpl implements IgrejaRepository {
  @Autowired
  private IgrejaSpringDataRepository repository;
  @Autowired
  @Lazy
  private IgrejaMapper mapper;

  @Override
  public Optional<Igreja> findByEmail(String email) {
    return repository.findByEmail(email)
        .map(mapper::toModel);

  }

  @Override
  public Optional<Igreja> findByRazaoSocial(String razaoSocial) {
    return repository.findByRazaoSocial(razaoSocial)
        .map(mapper::toModel);
  }

  @Override
  public Igreja save(Igreja model) {
    var igreja = repository.save(mapper.toEntity(model));
    return mapper.toModel(igreja);
  }

  @Override
  public List<Igreja> buscarTodas() {
    return repository.findAll().stream()
        .map(mapper::toModel)
        .toList();
  }

  @Override
  public List<Igreja> buscarPorNomeFantasia(String nomeFantasia) {
    return repository.findByNomeFantasia(nomeFantasia).stream()
        .map(mapper::toModel)
        .toList();
  }

  @Override
  public Optional<Igreja> buscarPorIr(Long id) {
    return repository.findById(id)
        .map(mapper::toModel);
  }

}
