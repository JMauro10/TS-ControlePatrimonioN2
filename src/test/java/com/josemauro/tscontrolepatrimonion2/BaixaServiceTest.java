package com.josemauro.tscontrolepatrimonion2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.josemauro.tscontrolepatrimonion2.entities.Baixa;
import com.josemauro.tscontrolepatrimonion2.entities.Patrimonio;
import com.josemauro.tscontrolepatrimonion2.entities.Pessoa;
import com.josemauro.tscontrolepatrimonion2.repository.BaixaRepository;
import com.josemauro.tscontrolepatrimonion2.repository.PatrimonioRepository;
import com.josemauro.tscontrolepatrimonion2.repository.PessoaRepository;

interface BiometricReader {
    enum Status {
        CONECTADO, DESCONECTADO
    }

    Status getStatus();

    String scanFingerprint();
}

class BaixaService {

    private BiometricReader biometricReader; // O Hardware
    private BaixaRepository baixaRepo;
    private PatrimonioRepository patrimonioRepo;
    private PessoaRepository pessoaRepo;

    public BaixaService(BiometricReader reader, BaixaRepository bRepo, PatrimonioRepository pRepo,
            PessoaRepository peRepo) {
        this.biometricReader = reader;
        this.baixaRepo = bRepo;
        this.patrimonioRepo = pRepo;
        this.pessoaRepo = peRepo;
    }

    public Baixa autorizarBaixaComBiometria(Long patrimonioId, Long gerenteId) {
        if (biometricReader.getStatus() != BiometricReader.Status.CONECTADO) {
            throw new RuntimeException("Leitor biométrico desconectado.");
        }

        Patrimonio p = patrimonioRepo.findById(patrimonioId)
                .orElseThrow(() -> new RuntimeException("Patrimônio não encontrado"));

        Pessoa gerente = pessoaRepo.findById(gerenteId)
                .orElseThrow(() -> new RuntimeException("Gerente não encontrado"));
        if (p.getCusto() > 5000 && "Gerente".equals(gerente.getCargo())) {

            System.out.println("Item de alto valor. Aguardando autenticação biométrica...");

            String digitalEscaneada = biometricReader.scanFingerprint();

            if (digitalEscaneada == null || !digitalEscaneada.equals(gerente.getCpf())) {
                throw new SecurityException("Autenticação biométrica falhou. A baixa foi negada.");
            }
            System.out.println("Gerente autenticado com sucesso.");
        }

        Baixa baixa = new Baixa();
        baixa.setPatrimonio(p);
        baixa.setAutorizadoPor(gerente);
        baixa.setMotivo(com.josemauro.tscontrolepatrimonion2.enums.MotivoBaixaEnum.OUTROS);
        baixa.setDataBaixa("11/11/2025");

        return baixaRepo.save(baixa);
    }
}

@ExtendWith(MockitoExtension.class)
public class BaixaServiceTest {

    @Mock
    private BiometricReader mockBiometricReader;

    @Mock
    private BaixaRepository mockBaixaRepo;
    @Mock
    private PatrimonioRepository mockPatrimonioRepo;
    @Mock
    private PessoaRepository mockPessoaRepo;

    @InjectMocks
    private BaixaService baixaService;

    private String idBiometricoGerente = "ID-BIO-GERENTE-123";
    private String digitalErrada = "ID-BIO-INVASOR-999";
    
    @Test
    public void BiometriaConfere() {
        Patrimonio patrimonioCaro = new Patrimonio();
        patrimonioCaro.setId(100L);
        patrimonioCaro.setCusto(10000.00);

        Pessoa gerente = new Pessoa();
        gerente.setId(1L);
        gerente.setCargo("Gerente");
        gerente.setCpf(idBiometricoGerente);

        Baixa baixaSalva = new Baixa();
        baixaSalva.setId(99L);
        baixaSalva.setPatrimonio(patrimonioCaro);
        baixaSalva.setAutorizadoPor(gerente);

        when(mockBiometricReader.getStatus()).thenReturn(BiometricReader.Status.CONECTADO);
        when(mockBiometricReader.scanFingerprint()).thenReturn(idBiometricoGerente);

        when(mockPatrimonioRepo.findById(100L)).thenReturn(Optional.of(patrimonioCaro));
        when(mockPessoaRepo.findById(1L)).thenReturn(Optional.of(gerente));
        when(mockBaixaRepo.save(any(Baixa.class))).thenReturn(baixaSalva);

        Baixa resultado = baixaService.autorizarBaixaComBiometria(100L, 1L);

        assertNotNull(resultado);
        assertEquals(99L, resultado.getId());
        assertEquals("Gerente", resultado.getAutorizadoPor().getCargo());

        verify(mockBiometricReader, times(1)).getStatus();
        verify(mockBiometricReader, times(1)).scanFingerprint();
        verify(mockBaixaRepo, times(1)).save(any(Baixa.class));
    }

    @Test
    public void BiometriaFalha() {
        Patrimonio patrimonioCaro = new Patrimonio();
        patrimonioCaro.setId(100L);
        patrimonioCaro.setCusto(10000.00);

        Pessoa gerente = new Pessoa();
        gerente.setId(1L);
        gerente.setCargo("Gerente");
        gerente.setCpf(idBiometricoGerente);

        when(mockBiometricReader.getStatus()).thenReturn(BiometricReader.Status.CONECTADO);

        when(mockBiometricReader.scanFingerprint()).thenReturn(digitalErrada);

        when(mockPatrimonioRepo.findById(100L)).thenReturn(Optional.of(patrimonioCaro));
        when(mockPessoaRepo.findById(1L)).thenReturn(Optional.of(gerente));

        SecurityException exception = assertThrows(SecurityException.class, () -> {
            baixaService.autorizarBaixaComBiometria(100L, 1L);
        });

        assertEquals("Autenticação biométrica falhou. A baixa foi negada.", exception.getMessage());

        verify(mockBiometricReader, times(1)).scanFingerprint(); 
        verify(mockBaixaRepo, never()).save(any(Baixa.class));
    }
}
