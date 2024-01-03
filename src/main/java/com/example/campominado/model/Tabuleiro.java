package com.example.campominado.model;

import com.example.campominado.exceptions.CampoNaoEncontradoException;
import com.example.campominado.exceptions.ExplosaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {

    private int linhas;
    private int colunas;
    private int minas;

    private final List<Campo> campos = new ArrayList<>();


    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearAsMinas();

    }

    public Campo getCampo(int linha, int coluna){

        var campoOptional = campos.stream()
                .filter(campo -> campo.getLinha() == linha && campo.getColuna() == coluna)
                .findFirst();

        if (campoOptional.isEmpty()) {
           throw new CampoNaoEncontradoException();
        }

        return campoOptional.get();
    }


    public void abrirCampo(int linha, int coluna){

        try {

            campos.parallelStream()
                    .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                    .findFirst()
                    .ifPresent(c -> c.abrir());

        }catch (ExplosaoException e){
            campos.forEach(c -> c.setAberto(true));
            throw e;

        }

    }

    public void marcarCampo(int linha, int coluna){
        campos.parallelStream()
                .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                .findFirst()
                .ifPresent(c -> c.alternarMarcacao());
    }

    private void gerarCampos() {

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                campos.add(new Campo(linha, coluna));
            }
        }

    }

    private void associarVizinhos() {
        for (Campo c1 : campos) {
            for (Campo c2 : campos) {
                c1.adicionarVizinho(c2);
            }
        }
    }

    private void sortearAsMinas() {
        long minasArmadas = 0;
        Predicate<Campo> minado = c -> c.isMinado();

        do {

            int aleatorio = (int) (Math.random() * campos.size());
            campos.get(aleatorio).minar();
            minasArmadas = campos.stream().filter(minado).count();

        }while (minasArmadas < minas);


    }

    public boolean objetivoAlcancado(){
        return campos.stream().allMatch(c -> c.objetivoAlcancado());
    }

    public void reiniciar(){
        campos.stream().forEach(c -> c.reiniciar());
        sortearAsMinas();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("  ");

        for (int c = 0; c < colunas; c++){
            sb.append(" ");
            sb.append(c);
            sb.append(" ");
        }

        sb.append("\n");

        int i = 0;

        for (int linha = 0; linha < linhas; linha++) {
            sb.append(" ");
            sb.append(linha);

            for (int coluna = 0; coluna < colunas; coluna++) {
                sb.append(" ");
                sb.append(campos.get(i));
                sb.append(" ");

                i++;
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
