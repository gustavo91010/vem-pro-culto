package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.response.StatusResponse;
import com.ajudaqui.vem_pro_culto_api.application.service.response.UsuarioResponse;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImpTest {

  @Mock
  private UsuarioRepository usuarioRepository;

  @InjectMocks
  private UsuarioServiceImp service;

  private static final String AUTH_INTERNAL = "app-secret-token";
  private static final UUID AUTH_TOKEN = UUID.randomUUID();

  private Usuario usuario;

  @BeforeEach
  void setUp() {
    ReflectionTestUtils.setField(service, "authInternal", AUTH_INTERNAL);

    usuario = Usuario.builder()
        .id(1L)
        .authToken(AUTH_TOKEN)
        .ativo(true)
        .build();
  }

  private UsuarioRequest criarRequest(String authToken) {
    return new UsuarioRequest(authToken, null, null, null);
  }

  // --- registro ---

  @Test
  void registro_comDadosValidos_deveCriarUsuario() {
    UsuarioRequest request = criarRequest(AUTH_TOKEN.toString());

    when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

    UsuarioResponse response = service.registro(AUTH_INTERNAL, request);

    assertNotNull(response);
    verify(usuarioRepository).save(any(Usuario.class));
  }

  @Test
  void registro_comAuthAppInvalido_deveLancarExcecao() {
    UsuarioRequest request = criarRequest( AUTH_TOKEN.toString());

    assertThrows(IllegalArgumentException.class,
        () -> service.registro("token-errado", request));

    verify(usuarioRepository, never()).save(any());
  }

  @Test
  void registro_comEmailDuplicado_deveLancarExcecao() {
    UsuarioRequest request = criarRequest( AUTH_TOKEN.toString());


    assertThrows(IllegalArgumentException.class,
        () -> service.registro(AUTH_INTERNAL, request));

    verify(usuarioRepository, never()).save(any());
  }

  // --- buscarTodos ---

  @Test
  void buscarTodos_semUsuarios_deveRetornarListaVazia() {
    when(usuarioRepository.buscarTodos()).thenReturn(List.of());

    List<UsuarioResponse> result = service.buscarTodos();

    assertTrue(result.isEmpty());
  }

  // --- findByEmail ---


  // --- findById ---

  @Test
  void findById_comIdExistente_deveRetornarUsuario() {
    when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

    Usuario result = service.findById(1L);

    assertEquals(1L, result.getId());
  }

  @Test
  void findById_comIdInexistente_deveLancarExcecao() {
    when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> service.findById(99L));
  }

  // --- findByAuthToken ---

  @Test
  void findByAuthToken_comTokenValido_deveRetornarUsuario() {
    when(usuarioRepository.findByAuthToken(AUTH_TOKEN)).thenReturn(Optional.of(usuario));

    Usuario result = service.findByAuthToken(AUTH_TOKEN.toString());

    assertEquals(AUTH_TOKEN, result.getAuthToken());
  }

  @Test
  void findByAuthToken_comTokenInexistente_deveLancarExcecao() {
    UUID outroToken = UUID.randomUUID();
    when(usuarioRepository.findByAuthToken(outroToken)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class,
        () -> service.findByAuthToken(outroToken.toString()));
  }

  @Test
  void findByAuthToken_comTokenInvalido_deveLancarIllegalArgument() {
    assertThrows(IllegalArgumentException.class,
        () -> service.findByAuthToken("nao-e-uuid"));
  }

  // --- alternarStatus ---

  @Test
  void alternarStatus_deAtivoParaInativo() {
    usuario.setAtivo(true);
    when(usuarioRepository.findByAuthToken(AUTH_TOKEN)).thenReturn(Optional.of(usuario));
    when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

    StatusResponse response = service.alternarStatus(AUTH_TOKEN.toString());

    assertFalse(response.getAtivo());
    verify(usuarioRepository).save(usuario);
  }

  @Test
  void alternarStatus_deInativoParaAtivo() {
    usuario.setAtivo(false);
    when(usuarioRepository.findByAuthToken(AUTH_TOKEN)).thenReturn(Optional.of(usuario));
    when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

    StatusResponse response = service.alternarStatus(AUTH_TOKEN.toString());

    assertTrue(response.getAtivo());
  }

  // --- update ---

  // @Test
  // void update_deveAtualizarNome() {
  // UsuarioUpdate updateDto = new UsuarioUpdate("Joao Atualizado", null, null,
  // null);

  // when(usuarioRepository.findByAuthToken(AUTH_TOKEN)).thenReturn(Optional.of(usuario));
  // when(usuarioRepository.update(eq(1L),
  // any(Usuario.class))).thenReturn(usuario);

  // UsuarioResponse response = service.update(AUTH_TOKEN.toString(), updateDto);

  // assertNotNull(response);
  // verify(usuarioRepository).update(eq(1L), any(Usuario.class));
  // }

  // @Test
  // void update_comTokenInvalido_deveLancarExcecao() {
  // UsuarioUpdate updateDto = new UsuarioUpdate("Novo Nome", null, null, null);

  // assertThrows(IllegalArgumentException.class,
  // () -> service.update("token-invalido", updateDto));
  // }
}
