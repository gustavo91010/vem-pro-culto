package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.ajudaqui.vem_pro_culto_api.application.exception.NotFoundException;
import com.ajudaqui.vem_pro_culto_api.application.exception.UnauthorizedException;
import com.ajudaqui.vem_pro_culto_api.application.service.UsuarioService;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.FiltroBuscaIgrejaDTO;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.IgrejaUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.request.IgrejaRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.response.StatusResponse;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.EPapel;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Endereco;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.IgrejaRepository;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuario;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuarioRepository;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IgrejaServiceImpTest {

    @Mock
    private IgrejaRepository repository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private IgrejaUsuarioRepository igrejaUsuarioRepository;

    @InjectMocks
    private IgrejaServiceImp service;

    private Igreja igreja;
    private Usuario usuario;
    private static final String AUTH_TOKEN = UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        igreja = Igreja.builder()
                .id(1L)
                .nomeFantasia("Igreja Batista Central")
                .razaoSocial("Igreja Batista Central LTDA")
                .email("contato@batista.com")
                .cnpj("12345678000100")
                .ativo(true)
                .usuarios(Set.of())
                .build();

        usuario = Usuario.builder()
                .id(1L)
                .authToken(UUID.randomUUID())
                .ativo(true)
                .build();
    }

    private Usuario usuarioComPermissao(Igreja igreja, EPapel papel) {
        IgrejaUsuario igrejaUsuario = new IgrejaUsuario(igreja, usuario, papel);
        usuario.setIgrejas(Set.of(igrejaUsuario));
        return usuario;
    }

    // --- registro ---

    @Test
    void registro_comDadosValidos_deveCriarIgreja() {
        IgrejaRequest request = new IgrejaRequest();
        request.setNomeFantasia("Igreja Nova");
        request.setRazaoSocial("Igreja Nova LTDA");
        request.setEmail("nova@igreja.com");
        request.setCnpj("99999999000100");

        when(repository.findByRazaoSocial("Igreja Nova LTDA")).thenReturn(Optional.empty());
        when(repository.findByEmail("nova@igreja.com")).thenReturn(Optional.empty());
        when(usuarioService.findByAuthToken(AUTH_TOKEN)).thenReturn(usuario);
        when(repository.save(any(Igreja.class))).thenAnswer(inv -> {
            Igreja i = inv.getArgument(0);
            i.setId(2L);
            return i;
        });
        when(igrejaUsuarioRepository.save(any(IgrejaUsuario.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        Igreja result = service.registro(AUTH_TOKEN, request);

        assertNotNull(result);
        assertFalse(result.getAtivo());
        verify(igrejaUsuarioRepository).save(any(IgrejaUsuario.class));
    }

    @Test
    void registro_comRazaoSocialDuplicada_deveLancarExcecao() {
        IgrejaRequest request = new IgrejaRequest();
        request.setRazaoSocial("Igreja Batista Central LTDA");
        request.setEmail("outra@igreja.com");

        when(repository.findByRazaoSocial("Igreja Batista Central LTDA"))
                .thenReturn(Optional.of(igreja));

        assertThrows(IllegalArgumentException.class,
                () -> service.registro(AUTH_TOKEN, request));

        verify(repository, never()).save(any());
    }

    @Test
    void registro_comEmailDuplicado_deveLancarExcecao() {
        IgrejaRequest request = new IgrejaRequest();
        request.setRazaoSocial("Outra Razao Social");
        request.setEmail("contato@batista.com");

        when(repository.findByRazaoSocial("Outra Razao Social")).thenReturn(Optional.empty());
        when(repository.findByEmail("contato@batista.com")).thenReturn(Optional.of(igreja));

        assertThrows(IllegalArgumentException.class,
                () -> service.registro(AUTH_TOKEN, request));

        verify(repository, never()).save(any());
    }

    @Test
    void registro_deveCriarVinculoComDono() {
        IgrejaRequest request = new IgrejaRequest();
        request.setNomeFantasia("Igreja Nova");
        request.setRazaoSocial("Razao Unica");
        request.setEmail("unica@igreja.com");

        when(repository.findByRazaoSocial(any())).thenReturn(Optional.empty());
        when(repository.findByEmail(any())).thenReturn(Optional.empty());
        when(usuarioService.findByAuthToken(AUTH_TOKEN)).thenReturn(usuario);
        when(repository.save(any(Igreja.class))).thenAnswer(inv -> inv.getArgument(0));
        when(igrejaUsuarioRepository.save(any(IgrejaUsuario.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        service.registro(AUTH_TOKEN, request);

        verify(igrejaUsuarioRepository).save(argThat(iu ->
                iu.getPapel().equals(EPapel.DONO) && iu.getUsuario().equals(usuario)));
    }

    // --- buscarTodas ---

    @Test
    void buscarTodas_deveDelegarParaRepository() {
        FiltroBuscaIgrejaDTO dto = FiltroBuscaIgrejaDTO.builder().cidade("Sao Paulo").build();
        when(repository.buscarTodas(dto)).thenReturn(List.of(igreja));

        List<Igreja> result = service.buscarTodas(dto);

        assertEquals(1, result.size());
        verify(repository).buscarTodas(dto);
    }

    // --- buscarPorId ---

    @Test
    void buscarPorId_comIdExistente_deveRetornarIgreja() {
        when(repository.buscarPorIr(1L)).thenReturn(Optional.of(igreja));

        Igreja result = service.buscarPorId(1L);

        assertEquals("Igreja Batista Central", result.getNomeFantasia());
    }

    @Test
    void buscarPorId_comIdInexistente_deveLancarNotFoundException() {
        when(repository.buscarPorIr(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.buscarPorId(99L));
    }

    // --- buscarPorRazaoSocial ---

    @Test
    void buscarPorRazaoSocial_existente_deveRetornarIgreja() {
        when(repository.findByRazaoSocial("Igreja Batista Central LTDA"))
                .thenReturn(Optional.of(igreja));

        Igreja result = service.buscarPorRazaoSocial("Igreja Batista Central LTDA");

        assertEquals("Igreja Batista Central LTDA", result.getRazaoSocial());
    }

    @Test
    void buscarPorRazaoSocial_inexistente_deveLancarNotFoundException() {
        when(repository.findByRazaoSocial("Nao Existe")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> service.buscarPorRazaoSocial("Nao Existe"));
    }

    // --- buscarPorEmail ---

    @Test
    void buscarPorEmail_existente_deveRetornarIgreja() {
        when(repository.findByEmail("contato@batista.com")).thenReturn(Optional.of(igreja));

        Igreja result = service.buscarPorEmail("contato@batista.com");

        assertEquals("contato@batista.com", result.getEmail());
    }

    @Test
    void buscarPorEmail_inexistente_deveLancarNotFoundException() {
        when(repository.findByEmail("nao@existe.com")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> service.buscarPorEmail("nao@existe.com"));
    }

    // --- atualizarIgreja ---

    @Test
    void atualizarIgreja_comPermissao_deveAtualizarCampos() {
        Usuario dono = usuarioComPermissao(igreja, EPapel.DONO);
        IgrejaUpdate dto = new IgrejaUpdate();
        dto.setNomeFantasia("Nome Atualizado");
        dto.setDescricao("Nova descricao");
        dto.setImagemUrl("http://img.com/nova.jpg");

        when(usuarioService.findByAuthToken(AUTH_TOKEN)).thenReturn(dono);
        when(repository.buscarPorIr(1L)).thenReturn(Optional.of(igreja));
        when(repository.save(any(Igreja.class))).thenAnswer(inv -> inv.getArgument(0));

        Igreja result = service.atualizarIgreja(AUTH_TOKEN, 1L, dto);

        assertEquals("Nome Atualizado", result.getNomeFantasia());
        assertEquals("Nova descricao", result.getDescricao());
        assertEquals("http://img.com/nova.jpg", result.getImagemUrl());
    }

    @Test
    void atualizarIgreja_semPermissao_deveLancarUnauthorized() {
        Usuario membro = usuarioComPermissao(igreja, EPapel.MEMBRO);
        IgrejaUpdate dto = new IgrejaUpdate();
        dto.setNomeFantasia("Novo Nome");

        when(usuarioService.findByAuthToken(AUTH_TOKEN)).thenReturn(membro);

        assertThrows(UnauthorizedException.class,
                () -> service.atualizarIgreja(AUTH_TOKEN, 1L, dto));

        verify(repository, never()).save(any());
    }

    @Test
    void atualizarIgreja_comCamposNulos_naoDeveAlterarCamposExistentes() {
        Usuario dono = usuarioComPermissao(igreja, EPapel.DONO);
        IgrejaUpdate dto = new IgrejaUpdate();
        // todos os campos nulos

        when(usuarioService.findByAuthToken(AUTH_TOKEN)).thenReturn(dono);
        when(repository.buscarPorIr(1L)).thenReturn(Optional.of(igreja));
        when(repository.save(any(Igreja.class))).thenAnswer(inv -> inv.getArgument(0));

        Igreja result = service.atualizarIgreja(AUTH_TOKEN, 1L, dto);

        assertEquals("Igreja Batista Central", result.getNomeFantasia());
        assertEquals("contato@batista.com", result.getEmail());
    }

    @Test
    void atualizarIgreja_comEndereco_deveAtualizarEndereco() {
        Usuario dono = usuarioComPermissao(igreja, EPapel.DONO);
        Endereco novoEndereco = new Endereco();
        IgrejaUpdate dto = new IgrejaUpdate();
        dto.setEndereco(novoEndereco);

        when(usuarioService.findByAuthToken(AUTH_TOKEN)).thenReturn(dono);
        when(repository.buscarPorIr(1L)).thenReturn(Optional.of(igreja));
        when(repository.save(any(Igreja.class))).thenAnswer(inv -> inv.getArgument(0));

        Igreja result = service.atualizarIgreja(AUTH_TOKEN, 1L, dto);

        assertEquals(novoEndereco, result.getEndereco());
    }

    // --- alternarStatus ---

    @Test
    void alternarStatus_deAtivoParaInativo() {
        igreja.setAtivo(true);
        when(repository.buscarPorIr(1L)).thenReturn(Optional.of(igreja));
        when(repository.save(any(Igreja.class))).thenReturn(igreja);

        StatusResponse response = service.alternarStatus(AUTH_TOKEN, 1L);

        assertFalse(response.getAtivo());
    }

    @Test
    void alternarStatus_deInativoParaAtivo() {
        igreja.setAtivo(false);
        when(repository.buscarPorIr(1L)).thenReturn(Optional.of(igreja));
        when(repository.save(any(Igreja.class))).thenReturn(igreja);

        StatusResponse response = service.alternarStatus(AUTH_TOKEN, 1L);

        assertTrue(response.getAtivo());
    }

    @Test
    void alternarStatus_comIgrejaInexistente_deveLancarNotFoundException() {
        when(repository.buscarPorIr(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> service.alternarStatus(AUTH_TOKEN, 99L));
    }

    // --- buscarPorNomeFantasia ---

    @Test
    void buscarPorNomeFantasia_deveDelegarParaRepository() {
        when(repository.buscarPorNomeFantasia("Batista")).thenReturn(List.of(igreja));

        List<Igreja> result = service.buscarPorNomeFantasia("Batista");

        assertEquals(1, result.size());
        verify(repository).buscarPorNomeFantasia("Batista");
    }
}
