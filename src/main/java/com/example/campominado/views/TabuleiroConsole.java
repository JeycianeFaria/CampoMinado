package com.example.campominado.views;

import com.example.campominado.exceptions.ExplosaoException;
import com.example.campominado.exceptions.SairException;
import com.example.campominado.model.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {

    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    private void executarJogo() {

        try {

            boolean continuar = true;

            while (continuar) {

                cicloDoJogo();

                System.out.println("Outra partida? (S/n): ");
                String resposta = entrada.nextLine();

                if (resposta.equalsIgnoreCase("n")) {
                    continuar = false;
                } else {
                    tabuleiro.reiniciar();
                }

            }

        } catch (SairException e) {
            System.out.println("Até a proxima");
        } finally {
            entrada.close();
        }

    }

    private void cicloDoJogo() {

        try {

            while (!tabuleiro.objetivoAlcancado()) {

                System.out.println(tabuleiro);

                String digitado = capturarValorDigitado("Digite (x, y): ");

                Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                        .mapToInt(i -> Integer.parseInt(i.trim()))
                        .iterator();

                digitado = capturarValorDigitado("1-Abrir ou 2-(Des)Marcar: ");

                if (digitado.equals("1")) {
                    tabuleiro.abrirCampo(xy.next(), xy.next());
                } else if (digitado.equals("2")) {
                    tabuleiro.marcarCampo(xy.next(), xy.next());
                }

            }

            System.out.println(tabuleiro);
            System.out.println("Você ganhou!!!");

        } catch (ExplosaoException e) {

            System.out.println(tabuleiro);
            System.out.println("Você perdeu!");

        }

    }


    private String capturarValorDigitado(String texto) {

        System.out.print(texto);
        String digitado = entrada.nextLine();

        if (digitado.equalsIgnoreCase("sair")) {
            throw new SairException();
        }

        return digitado;
    }

}
