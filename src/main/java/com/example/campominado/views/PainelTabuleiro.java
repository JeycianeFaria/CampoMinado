package com.example.campominado.views;

import com.example.campominado.model.Tabuleiro;

import javax.swing.*;
import java.awt.*;

public class PainelTabuleiro extends JPanel {

    public PainelTabuleiro(Tabuleiro tabuleiro) {

        setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));

        tabuleiro.paraCadaCampo(campo -> add(new BotaoCampo(campo)));

        tabuleiro.registrarObservadores(evento -> {
            //TODO mostrar resultado pro usuario
        });

    }
}
