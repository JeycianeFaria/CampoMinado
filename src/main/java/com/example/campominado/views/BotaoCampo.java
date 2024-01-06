package com.example.campominado.views;

import com.example.campominado.model.Campo;
import com.example.campominado.model.CampoEvento;
import com.example.campominado.model.CampoObservador;

import javax.swing.*;

public class BotaoCampo extends JButton implements CampoObservador {

    public BotaoCampo(Campo campo) {

    }

    @Override
    public void eventoOcorreu(Campo campo, CampoEvento evento) {
        switch (evento){
            case ABRIR:
                break;
            case MARCAR:
                break;
            case EXPLODIR:
                break;
            default:
                break;
        }
    }

}
