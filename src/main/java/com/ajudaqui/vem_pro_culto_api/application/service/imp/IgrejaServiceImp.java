package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import java.util.List;
import java.util.Optional;

import com.ajudaqui.vem_pro_culto_api.application.exception.NotFoundException;
import com.ajudaqui.vem_pro_culto_api.application.service.IgrejaService;
import com.ajudaqui.vem_pro_culto_api.application.service.UsuarioService;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.IgrejaUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.request.IgrejaRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.response.IgrejaResponse;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.IgrejaRepository;

public class IgrejaServiceImp implements IgrejaService {
  private IgrejaRepository repository;
  private UsuarioService usuarioService;

  @Override
  public IgrejaResponse registro(String requestedToken, IgrejaRequest igrejaRequest) {


    if (findByRazaoSocial(igrejaRequest.getRazaoSocial()).isPresent())
      throw new IllegalArgumentException("Razão Social já registrado");


    if (findByEmail(igrejaRequest.getEmail()).isPresent())
      throw new IllegalArgumentException("Email já registrado");

    var usuario = usuarioService.findByAuthToken(requestedToken);
    Igreja igreja= new Igreja(igrejaRequest, usuario);
    return new IgrejaResponse(repository.save(igreja));
  }

  @Override
  public List<IgrejaResponse> buscarTodas() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
  }

  @Override
  public List<IgrejaResponse> buscarPorNomeFantasia(String nomeFantasia) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'buscarPorNomeFantasia'");
  }

  @Override
  public IgrejaResponse buscarPorId(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
  }

  @Override
  public IgrejaResponse atualizarIgreja(IgrejaUpdate igrejaDTO) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'atualizarIgreja'");
  }


  @Override
  public IgrejaResponse buscarPorRazaoSocial(String razaoSocial) {
    var igreja = findByRazaoSocial(razaoSocial)
        .orElseThrow(() -> new NotFoundException("Usuário não localizado."));
    return new IgrejaResponse(igreja);
  }

  private Optional<Igreja> findByRazaoSocial(String razaoSocial) {
    return repository.findByRazaoSocial(razaoSocial);
  }

  @Override
  public IgrejaResponse buscarPorEmail(String email) {
    var igreja = findByEmail(email)
        .orElseThrow(() -> new NotFoundException("Usuário não localizado."));
    return new IgrejaResponse(igreja);
  }

  private Optional<Igreja> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  @Override
  public Boolean desativarIgreja(String requestedToken, Long igrejaId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'desativarIgreja'");
  }
}
