package com.example.campominado.views;

import com.example.campominado.model.Tabuleiro;

import javax.swing.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {

        Tabuleiro tabuleiro = new Tabuleiro(16, 30, 50);
        PainelTabuleiro painelTabuleiro = new PainelTabuleiro(tabuleiro);

        add(painelTabuleiro);

        setTitle("Campo Minado");
        setSize(690, 438);
        setLocationRelativeTo(null);

        setVisible(true);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public static void main(String[] args) {
        new TelaPrincipal();
    }

}
