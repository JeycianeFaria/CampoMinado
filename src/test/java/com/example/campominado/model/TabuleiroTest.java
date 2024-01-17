package com.example.campominado.model;

import com.example.campominado.exceptions.CampoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabuleiroTest {

    Tabuleiro tabuleiro;

    @BeforeEach
    void setUp() {
        tabuleiro = new Tabuleiro(2, 2, 1);
    }

    @Test
    void testGetCampo() {

        var result = tabuleiro.getCampo(1, 1);

        assertEquals(1, result.getLinha());
        assertEquals(1, result.getColuna());
        assertEquals(Campo.class, result.getClass());
    }

    @Test
    void testGetCampoExceptionCampoNaoEncontrado() {
        assertThrows(CampoNaoEncontradoException.class, () -> tabuleiro.getCampo(5, 5));
    }

    @Test
    void testAbrirCampo() {

        var campo = tabuleiro.getCampo(1, 1);
        campo.reiniciar();

        tabuleiro.abrirCampo(1, 1);

        assertTrue(campo.isAberto());

    }

    @Test
    void testMarcarCampo() {

        var campo = tabuleiro.getCampo(1, 1);

        tabuleiro.marcarCampo(1, 1);

        assertTrue(campo.isMarcado());

    }

    @Test
    void testObjetivoAlcancado() {

        var result = tabuleiro.objetivoAlcancado();

        assertFalse(result);

    }

    @Test
    void testReiniciar() {

        var campo = tabuleiro.getCampo(1, 1);

        tabuleiro.marcarCampo(1, 1);

        tabuleiro.reiniciar();

        assertFalse(campo.isMarcado());

    }
}