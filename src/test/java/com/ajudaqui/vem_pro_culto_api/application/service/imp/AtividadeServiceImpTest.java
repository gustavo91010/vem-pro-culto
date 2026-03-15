package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.ajudaqui.vem_pro_culto_api.application.exception.NotFoundException;
import com.ajudaqui.vem_pro_culto_api.application.exception.UnauthorizedException;
import com.ajudaqui.vem_pro_culto_api.application.service.UsuarioService;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.AtividadeDTO;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.EPapel;
import com.ajudaqui.vem_pro_culto_api.domain.entity.atividade.Atividade;
import com.ajudaqui.vem_pro_culto_api.domain.entity.atividade.AtividadeRepository;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuario;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;
import com.ajudaqui.vem_pro_culto_api.domain.enums.EAtividadeTipo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AtividadeServiceImpTest {

    @Mock
    private AtividadeRepository repository;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private AtividadeServiceImp service;

    private static final String AUTH_TOKEN = UUID.randomUUID().toString();
    private static final Long IGREJA_ID = 1L;

    private Usuario usuario;
    private Igreja igreja;
    private Atividade atividade;

    @BeforeEach
    void setUp() {
        igreja = Igreja.builder()
                .id(IGREJA_ID)
                .nomeFantasia("Igreja Batista")
                .razaoSocial("Igreja Batista LTDA")
                .email("contato@batista.com")
                .ativo(true)
                .usuarios(Set.of())
                .build();

        usuario = Usuario.builder()
                .id(1L)
                .authToken(UUID.randomUUID())
                .ativo(true)
                .build();

        atividade = Atividade.builder()
                .id(10L)
                .igrejaId(IGREJA_ID)
                .tipo(EAtividadeTipo.CULTO)
                .descricao("Culto Dominical")
                .horario(LocalDateTime.of(2026, 3, 15, 9, 0))
                .build();
    }

    private Usuario usuarioComPermissao(EPapel papel) {
        IgrejaUsuario iu = new IgrejaUsuario(igreja, usuario, papel);
        usuario.setIgrejas(Set.of(iu));
        return usuario;
    }

    // --- registro ---

    @Test
    void registro_comPermissaoDeDono_deveCriarAtividade() {
        Usuario dono = usuarioComPermissao(EPapel.DONO);
        AtividadeDTO dto = new AtividadeDTO(IGREJA_ID, "CULTO", "Culto Matutino",
                LocalDateTime.of(2026, 3, 15, 9, 0));

        when(usuarioService.findByAuthToken(AUTH_TOKEN)).thenReturn(dono);
        when(repository.save(any(Atividade.class))).thenAnswer(inv -> inv.getArgument(0));

        Atividade result = service.registro(AUTH_TOKEN, dto);

        assertNotNull(result);
        assertEquals(EAtividadeTipo.CULTO, result.getTipo());
        assertEquals("Culto Matutino", result.getDescricao());
        verify(repository).save(any(Atividade.class));
    }

    @Test
    void registro_semPermissao_deveLancarUnauthorized() {
        Usuario membro = usuarioComPermissao(EPapel.MEMBRO);
        AtividadeDTO dto = new AtividadeDTO(IGREJA_ID, "CULTO", "Culto Matutino",
                LocalDateTime.of(2026, 3, 15, 9, 0));

        when(usuarioService.findByAuthToken(AUTH_TOKEN)).thenReturn(membro);

        assertThrows(UnauthorizedException.class,
                () -> service.registro(AUTH_TOKEN, dto));

        verify(repository, never()).save(any());
    }

    @Test
    void registro_semVinculoComIgreja_deveLancarUnauthorized() {
        usuario.setIgrejas(Set.of()); // sem vinculos
        AtividadeDTO dto = new AtividadeDTO(IGREJA_ID, "CULTO", "Culto",
                LocalDateTime.of(2026, 3, 15, 9, 0));

        when(usuarioService.findByAuthToken(AUTH_TOKEN)).thenReturn(usuario);

        assertThrows(UnauthorizedException.class,
                () -> service.registro(AUTH_TOKEN, dto));
    }

    // --- buscarPorId ---

    @Test
    void buscarPorId_comIdExistente_deveRetornarAtividade() {
        when(repository.findById(10L)).thenReturn(Optional.of(atividade));

        Atividade result = service.buscarPorId(10L);

        assertEquals(10L, result.getId());
        assertEquals("Culto Dominical", result.getDescricao());
    }

    @Test
    void buscarPorId_comIdInexistente_deveLancarExcecao() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.buscarPorId(99L));
    }

    // --- buscarAtividades ---

    @Test
    void buscarAtividades_comResultados_deveRetornarLista() {
        LocalDate inicio = LocalDate.of(2026, 3, 1);
        LocalDate fim = LocalDate.of(2026, 3, 31);

        when(repository.buscarAtividades(IGREJA_ID, inicio, fim))
                .thenReturn(List.of(atividade));

        List<Atividade> result = service.buscarAtividades(IGREJA_ID, "2026-03-01", "2026-03-31");

        assertEquals(1, result.size());
        assertEquals("Culto Dominical", result.get(0).getDescricao());
    }

    @Test
    void buscarAtividades_semResultados_deveLancarNotFoundException() {
        LocalDate inicio = LocalDate.of(2026, 3, 1);
        LocalDate fim = LocalDate.of(2026, 3, 31);

        when(repository.buscarAtividades(IGREJA_ID, inicio, fim))
                .thenReturn(List.of());

        assertThrows(NotFoundException.class,
                () -> service.buscarAtividades(IGREJA_ID, "2026-03-01", "2026-03-31"));
    }

    @Test
    void buscarAtividades_comDataFimNula_deveUsarDataInicioComoFim() {
        LocalDate data = LocalDate.of(2026, 3, 15);

        when(repository.buscarAtividades(IGREJA_ID, data, data))
                .thenReturn(List.of(atividade));

        List<Atividade> result = service.buscarAtividades(IGREJA_ID, "2026-03-15", null);

        assertEquals(1, result.size());
        verify(repository).buscarAtividades(IGREJA_ID, data, data);
    }

    @Test
    void buscarAtividades_comDataFimVazia_deveUsarDataInicioComoFim() {
        LocalDate data = LocalDate.of(2026, 3, 15);

        when(repository.buscarAtividades(IGREJA_ID, data, data))
                .thenReturn(List.of(atividade));

        List<Atividade> result = service.buscarAtividades(IGREJA_ID, "2026-03-15", "   ");

        assertEquals(1, result.size());
        verify(repository).buscarAtividades(IGREJA_ID, data, data);
    }

    // --- excluir ---

    @Test
    void excluir_comPermissaoEAtividadeDaIgreja_deveExcluir() {
        Usuario dono = usuarioComPermissao(EPapel.DONO);

        when(usuarioService.findByAuthToken(AUTH_TOKEN)).thenReturn(dono);
        when(repository.findById(10L)).thenReturn(Optional.of(atividade));

        service.excluir(AUTH_TOKEN, IGREJA_ID, 10L);

        verify(repository).delete(10L);
    }

    @Test
    void excluir_semPermissao_deveLancarUnauthorized() {
        Usuario membro = usuarioComPermissao(EPapel.MEMBRO);

        when(usuarioService.findByAuthToken(AUTH_TOKEN)).thenReturn(membro);

        assertThrows(UnauthorizedException.class,
                () -> service.excluir(AUTH_TOKEN, IGREJA_ID, 10L));

        verify(repository, never()).delete(any());
    }

    @Test
    void excluir_atividadeDeOutraIgreja_deveLancarUnauthorized() {
        Usuario dono = usuarioComPermissao(EPapel.DONO);
        Atividade atividadeOutraIgreja = Atividade.builder()
                .id(20L)
                .igrejaId(999L) // outra igreja
                .tipo(EAtividadeTipo.ATIVIDADE)
                .descricao("Outra atividade")
                .horario(LocalDateTime.now())
                .build();

        when(usuarioService.findByAuthToken(AUTH_TOKEN)).thenReturn(dono);
        when(repository.findById(20L)).thenReturn(Optional.of(atividadeOutraIgreja));

        assertThrows(UnauthorizedException.class,
                () -> service.excluir(AUTH_TOKEN, IGREJA_ID, 20L));

        verify(repository, never()).delete(any());
    }

    @Test
    void excluir_atividadeInexistente_deveLancarExcecao() {
        Usuario dono = usuarioComPermissao(EPapel.DONO);

        when(usuarioService.findByAuthToken(AUTH_TOKEN)).thenReturn(dono);
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.excluir(AUTH_TOKEN, IGREJA_ID, 99L));
    }
}
