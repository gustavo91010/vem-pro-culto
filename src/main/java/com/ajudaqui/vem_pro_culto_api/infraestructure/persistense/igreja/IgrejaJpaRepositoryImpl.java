package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igreja;

import java.util.List;
import java.util.Optional;

import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.IgrejaRepository;

public class IgrejaJpaRepositoryImpl implements IgrejaRepository {
  private IgrejaJpaRepository repository;

  @Override
  public Optional<Igreja> findByEmail(String email) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
  }

  @Override
  public Optional<Igreja> findByRazaoSocial(String razaoSocial) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByRazaoSocial'");
  }

  @Override
  public Igreja save(Igreja model) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public List<Igreja> buscarTodas() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
  }

  @Override
  public List<Igreja> buscarPorNomeFantasia(String nomeFantasia) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'buscarPorNomeFantasia'");
  }

  @Override
  public Igreja buscarPorIr(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'buscarPorIr'");
  }

}
