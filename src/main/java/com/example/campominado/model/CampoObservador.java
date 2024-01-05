package com.example.campominado.model;

@FunctionalInterface
public interface CampoObservador {

    void eventoOcorreu(Campo campo, CampoEvento evento);

}
