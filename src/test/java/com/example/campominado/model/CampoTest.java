package com.example.campominado.model;

import com.example.campominado.exceptions.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampoTest {

    Campo campo;

    @BeforeEach
    void setUp() {
        campo = new Campo(3, 3);
    }

    @Test
    void adicionarVizinhoCenarioPositivoNaoDiagonalEsquerda() {
        Campo viznho = new Campo(3, 2);

        boolean result = campo.adicionarVizinho(viznho);

        assertTrue(result);
    }

    @Test
    void adicionarVizinhoCenarioPositivoNaoDiagonalDireita() {
        Campo viznho = new Campo(3, 4);

        boolean result = campo.adicionarVizinho(viznho);

        assertTrue(result);
    }

    @Test
    void adicionarVizinhoCenarioPositivoDiagonalEmCima() {
        Campo viznho = new Campo(2, 2);

        boolean result = campo.adicionarVizinho(viznho);

        assertTrue(result);
    }

    @Test
    void adicionarVizinhoCenarioPositivoDiagonalEmBaixo() {
        Campo viznho = new Campo(4, 4);

        boolean result = campo.adicionarVizinho(viznho);

        assertTrue(result);
    }

    @Test
    void adicionarVizinhoCenarioNegativoNaoDiagonal() {
        Campo viznho = new Campo(3, 1);

        boolean result = campo.adicionarVizinho(viznho);

        assertFalse(result);
    }

    @Test
    void adicionarVizinhoCenarioNegativoDiagonal() {
        Campo viznho = new Campo(1, 1);

        boolean result = campo.adicionarVizinho(viznho);

        assertFalse(result);
    }


    @Test
    void valorPadraoAtributoMarcado() {
        assertFalse(campo.isMarcado());
    }

    @Test
    void alternarMarcacao() {
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void alternarMarcacaoDuplaChamada() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void abrirCampoNaoMinadoNaoMarcadoNaoAberto() {

        boolean result  = campo.abrir();

        assertTrue(campo.isAberto());
        assertTrue(result);
    }

    @Test
    void abrirCampoNaoMinadoMarcadoNaoAberto() {

        campo.alternarMarcacao();

        boolean result  = campo.abrir();

        assertFalse(campo.isAberto());
        assertFalse(result);
    }

    @Test
    void abrirCampoMinadoMarcadoNaoAberto() {

        campo.alternarMarcacao();
        campo.minar();

        boolean result  = campo.abrir();

        assertFalse(campo.isAberto());
        assertFalse(result);
    }

    @Test
    void abrirCampoMinadoNapMarcadoNaoAberto() {

        campo.minar();

        assertThrows(ExplosaoException.class, () -> campo.abrir());
    }

    @Test
    void abrirCampoNaoMinadoNaoMarcadoAberto() {
        campo.abrir();
        boolean result  = campo.abrir();

        assertTrue(campo.isAberto());
        assertFalse(result);
    }


    @Test
    void abrirCampoComVizinhos() {

        Campo vizinho = new Campo(2,2);
        Campo vizinhoDoVizinho = new Campo(1,1);

        vizinho.adicionarVizinho(vizinhoDoVizinho);
        campo.adicionarVizinho(vizinho);


        boolean result  = campo.abrir();

        assertTrue(campo.isAberto());
        assertTrue(vizinho.isAberto());
        assertTrue(vizinhoDoVizinho.isAberto());
        assertTrue(result);
    }


    @Test
    void abrirCampoComVizinhosMinado() {

        Campo vizinho = new Campo(2,2);
        Campo vizinhoDoVizinho = new Campo(1,1);
        Campo vizinhoDoVizinho2 = new Campo(1,2);

        vizinhoDoVizinho.minar();

        vizinho.adicionarVizinho(vizinhoDoVizinho);
        vizinho.adicionarVizinho(vizinhoDoVizinho2);
        campo.adicionarVizinho(vizinho);


        boolean result  = campo.abrir();

        assertTrue(campo.isAberto());
        assertTrue(vizinho.isAberto());
        assertFalse(vizinhoDoVizinho.isAberto());
        assertFalse(vizinhoDoVizinho2.isAberto());
        assertTrue(result);
    }


    @Test
    void testToStringMarcado() {

        campo.alternarMarcacao();

        String result  = campo.toString();

        assertFalse(campo.isAberto());
        assertTrue(campo.isMarcado());
        assertEquals("x",result);
    }


    @Test
    void testToStringAbertoMinado() {

        campo.abrir();
        campo.minar();

        String result  = campo.toString();

        assertTrue(campo.isAberto());
        assertTrue(campo.isMinado());
        assertEquals("*",result);
    }

    @Test
    void testToStringAbertoVizinhoMinado() {

        Campo vizinho = new Campo(2,2);
        vizinho.minar();

        campo.adicionarVizinho(vizinho);
        campo.abrir();


        String result  = campo.toString();

        assertTrue(campo.isAberto());
        assertFalse(vizinho.isAberto());
        assertTrue(vizinho.isMinado());
        assertEquals("1",result);
    }


    @Test
    void testToStringAberto() {

        campo.abrir();

        String result  = campo.toString();

        assertTrue(campo.isAberto());
        assertEquals(" ",result);
    }

    @Test
    void testToStringFechado() {

        String result  = campo.toString();

        assertFalse(campo.isAberto());
        assertEquals("?",result);
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
        assertEquals(3,campo.getLinha());
        assertEquals(3,campo.getColuna());
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
    void testSetAbertoTrue(){
        boolean aberto = true;

        campo.setAberto(aberto);

        assertTrue(campo.isAberto());

    }

    @Test
    void testSetAbertoFalse(){
        boolean aberto = false;

        campo.setAberto(aberto);

        assertFalse(campo.isAberto());

    }

}
