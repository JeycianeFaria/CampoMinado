package com.example.campominado.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CampoTest {

    Campo campo;

    @BeforeEach
    void setUp() {
        campo = new Campo(3, 3);
    }


    @Test
    void testValorPadraoAtributoMarcado() {
        assertFalse(campo.isMarcado());
    }

    @Test
    void testAlternarMarcacao() {
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testAlternarMarcacaoDuplaChamada() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testAbrirCampoNaoMinadoNaoMarcadoNaoAberto() {

        boolean result = campo.abrir();

        assertTrue(campo.isAberto());
        assertTrue(result);
    }

    @Test
    void testAbrirCampoNaoMinadoMarcadoNaoAberto() {

        campo.alternarMarcacao();

        boolean result = campo.abrir();

        assertFalse(campo.isAberto());
        assertFalse(result);
    }

    @Test
    void testAbrirCampoMinadoMarcadoNaoAberto() {

        campo.alternarMarcacao();
        campo.minar();

        boolean result = campo.abrir();

        assertFalse(campo.isAberto());
        assertFalse(result);
    }

    @Test
    void testAbrirCampoMinadoNaoMarcadoNaoAberto() {

        campo.minar();

        boolean result = campo.abrir();

        assertTrue(result);

    }

    @Test
    void testAbrirCampoNaoMinadoNaoMarcadoAberto() {
        campo.abrir();
        boolean result = campo.abrir();

        assertTrue(campo.isAberto());
        assertFalse(result);
    }


    @Test
    void testAbrirCampoComVizinhos() {

        Campo vizinho = new Campo(2, 2);
        Campo vizinhoDoVizinho = new Campo(1, 1);

        vizinho.adicionarVizinho(vizinhoDoVizinho);
        campo.adicionarVizinho(vizinho);


        boolean result = campo.abrir();

        assertTrue(campo.isAberto());
        assertTrue(vizinho.isAberto());
        assertTrue(vizinhoDoVizinho.isAberto());
        assertTrue(result);
    }


    @Test
    void testAbrirCampoComVizinhosMinado() {

        Campo vizinho = new Campo(2, 2);
        Campo vizinhoDoVizinho = new Campo(1, 1);
        Campo vizinhoDoVizinho2 = new Campo(1, 2);

        vizinhoDoVizinho.minar();

        vizinho.adicionarVizinho(vizinhoDoVizinho);
        vizinho.adicionarVizinho(vizinhoDoVizinho2);
        campo.adicionarVizinho(vizinho);


        boolean result = campo.abrir();

        assertTrue(campo.isAberto());
        assertTrue(vizinho.isAberto());
        assertFalse(vizinhoDoVizinho.isAberto());
        assertFalse(vizinhoDoVizinho2.isAberto());
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 2",
            "3, 4",
            "2, 2",
            "4, 4"
    })
    void testAdicionarVizinhoCenarioPositivo(Integer linha, Integer coluna) {
        Campo viznho = new Campo(linha, coluna);

        boolean result = campo.adicionarVizinho(viznho);

        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 1",
            "1, 1",
            "1, 3",
            "5, 5"
    })
    void testAdicionarVizinhoCenarioNegativo(Integer linha, Integer coluna) {
        Campo viznho = new Campo(linha, coluna);

        boolean result = campo.adicionarVizinho(viznho);

        assertFalse(result);
    }

    @Test
    void testMinasNaVizinhaca(){

        Campo viznho = new Campo(2, 3);
        viznho.minar();

        campo.adicionarVizinho(viznho);

        int result = campo.minasNaVizinhaca();

        assertEquals(1 , result);

    }


    @Test
    void testReiniciar() {

        campo.abrir();

        campo.reiniciar();

        assertFalse(campo.isAberto());
        assertFalse(campo.isMinado());
        assertFalse(campo.isMarcado());
    }


    @Test
    void testGetLinhaEColuna() {
        assertEquals(3, campo.getLinha());
        assertEquals(3, campo.getColuna());
    }

    @Test
    void testObjetivoAlcancadoTrueDesvendado() {
        campo.abrir();

        boolean result = campo.objetivoAlcancado();

        assertFalse(campo.isMinado());
        assertTrue(campo.isAberto());
        assertTrue(result);
    }


    @Test
    void testObjetivoAlcancadoTrueProtegido() {
        campo.minar();
        campo.alternarMarcacao();

        boolean result = campo.objetivoAlcancado();

        assertTrue(campo.isMinado());
        assertTrue(campo.isMarcado());
        assertTrue(result);
    }

    @Test
    void testObjetivoAlcancadoFalseDesvendado() {
        campo.minar();

        boolean result = campo.objetivoAlcancado();

        assertTrue(campo.isMinado());
        assertFalse(result);
    }


    @Test
    void testObjetivoAlcancadoFalseProtegido() {

        boolean result = campo.objetivoAlcancado();

        assertFalse(campo.isMinado());
        assertFalse(campo.isMarcado());
        assertFalse(result);
    }

    @Test
    void testSetAbertoTrue() {
        boolean aberto = true;

        campo.setAberto(aberto);

        assertTrue(campo.isAberto());

    }

    @Test
    void testSetAbertoFalse() {
        boolean aberto = false;

        campo.setAberto(aberto);

        assertFalse(campo.isAberto());

    }

}
