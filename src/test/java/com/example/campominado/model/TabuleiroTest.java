package com.example.campominado.model;

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
    void testObjetivoAlcancado() {

        var result = tabuleiro.objetivoAlcancado();

        assertFalse(result);

    }

    @Test
    void testAbrirCampo() {

        var campo = tabuleiro.getCampo(1, 1);
        campo.reiniciar();

        tabuleiro.abrirCampo(1, 1);

        assertTrue(campo.isAberto());

    }

    @Test
    void testMostrarMinas(){
        Campo campo = tabuleiro.getCampo(1,1);
        campo.minar();


        tabuleiro.mostrarMinas();

        assertTrue(campo.isMinado());
        assertTrue(campo.isAberto());
        assertFalse(campo.isMarcado());
    }

    @Test
    void testMarcarCampo() {

        var campo = tabuleiro.getCampo(1, 1);

        tabuleiro.marcarCampo(1, 1);

        assertTrue(campo.isMarcado());

    }

    @Test
    void testReiniciar() {

        var campo = tabuleiro.getCampo(1, 1);

        tabuleiro.marcarCampo(1, 1);

        tabuleiro.reiniciar();

        assertFalse(campo.isMarcado());

    }

    @Test
    void testEventoOcorreuExplodir() {

        Campo campo = tabuleiro.getCampo(1,1);
        campo.minar();
        tabuleiro.abrirCampo(1,1);

        CampoEvento evento = CampoEvento.EXPLODIR;

        tabuleiro.eventoOcorreu(campo, evento);

        assertTrue(campo.isAberto());
        assertTrue(campo.isMinado());
        assertFalse(campo.isMarcado());

    }

    @Test
    void testEventoOcorreuObjetivoAlcancado() {

        Tabuleiro tabuleiro_test = new Tabuleiro(1,1,1);

        tabuleiro_test.marcarCampo(0,0);
        Campo campo = tabuleiro_test.getCampo(0,0);

        CampoEvento evento = CampoEvento.MARCAR;

        tabuleiro_test.eventoOcorreu(campo, evento);

        assertFalse(campo.isAberto());
        assertTrue(campo.isMinado());
        assertTrue(campo.isMarcado());
        assertTrue(tabuleiro_test.objetivoAlcancado());

    }

}