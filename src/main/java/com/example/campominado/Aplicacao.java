package com.example.campominado;

import com.example.campominado.model.Tabuleiro;
import com.example.campominado.views.TabuleiroConsole;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aplicacao {

    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(6,6,6);

        new TabuleiroConsole(tabuleiro);

    }

}
