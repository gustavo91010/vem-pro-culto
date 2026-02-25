package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igreja;

import java.util.*;

import com.ajudaqui.vem_pro_culto_api.application.service.dto.FiltroBuscaIgrejaDTO;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.IgrejaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class IgrejaJpaRepositoryImpl implements IgrejaRepository {

  @Autowired
  private IgrejaSpringDataRepository repository;

  @Autowired
  @Lazy // TODO depos ve se pode tirar...
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

  // @Override
  // public List<Igreja> buscarTodas() {
  // return repository.findAll().stream()
  // .map(mapper::toModel)
  // .toList();
  // }

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

  @Override
  public List<Igreja> buscarTodas(FiltroBuscaIgrejaDTO dto) {

    Map<String, String> filters = dto.toFilterMap();
    Specification<IgrejaEntity> specification = Specification.where(null);

    for (Map.Entry<String, String> entry : filters.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();

      specification = specification.and((root, query, criteriaBuilder) -> {
        switch (key) {
          case "razaoSocial":
            return criteriaBuilder.like(root.get("razaoSocial"), "%" + value + "%");
          case "cnpj":
            return criteriaBuilder.equal(root.get("cnpj"), value);
          case "nomeFantasia":
            return criteriaBuilder.like(root.get("nomeFantasia"), "%" + value + "%");
          case "logradouro":
            return criteriaBuilder.like(
                root.get("endereco").get("logradouro"),
                "%" + value + "%");
          case "bairro":
            return criteriaBuilder.like(
                root.get("endereco").get("bairro"),
                "%" + value + "%");

          case "cidade":
            return criteriaBuilder.like(
                root.get("endereco").get("cidade"),
                "%" + value + "%");
          case "estado":
            return criteriaBuilder.like(
                root.get("endereco").get("estado"),
                "%" + value + "%");
          case "cep":
            return criteriaBuilder.like(
                root.get("endereco").get("cep"),
                "%" + value + "%");
          default:
            throw new IllegalArgumentException("Campo de filtro inválido: " + key);
        }
      });
    }

    return repository.findAll(specification).stream().map(mapper::toModel).toList();
  }

}
