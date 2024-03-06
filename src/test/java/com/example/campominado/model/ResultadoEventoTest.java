package com.example.campominado.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultadoEventoTest {

    @Test
    void isGanhou() {

        ResultadoEvento resultadoEvento = new ResultadoEvento(true);

        var result = resultadoEvento.isGanhou();

        assertTrue(result);

    }

}
