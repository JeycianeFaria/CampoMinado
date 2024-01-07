package com.example.campominado.views;

import com.example.campominado.model.Campo;
import com.example.campominado.model.CampoEvento;
import com.example.campominado.model.CampoObservador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BotaoCampo extends JButton implements CampoObservador, MouseListener {

    private final Color BG_PADRAO = new Color(184, 184, 184);
    private final Color BG_MARCAR = new Color(8, 179, 247);
    private final Color BG_EXPLODIR = new Color(189, 66, 68);
    private final Color TEXTO_VERDE = new Color(0, 100, 0);

    private Campo campo;


    public BotaoCampo(Campo campo) {

        this.campo = campo;

        setBorder(BorderFactory.createBevelBorder(0));
        setBackground(BG_PADRAO);

        addMouseListener(this);

        campo.registrarObservadores(this);

    }

    @Override
    public void eventoOcorreu(Campo campo, CampoEvento evento) {
        switch (evento) {
            case ABRIR:
                aplicarEstiloAbrir();
                break;
            case MARCAR:
                aplicarEstiloMarcar();
                break;
            case EXPLODIR:
                aplicarEstiloExplodir();
                break;
            default:
                aplicarEstiloPadrao();
                break;
        }
    }

    private void aplicarEstiloPadrao() {

        setBackground(BG_PADRAO);
        setText("");

    }

    private void aplicarEstiloExplodir() {

        setBackground(BG_EXPLODIR);
        setForeground(Color.WHITE);
        setText("X");

    }

    private void aplicarEstiloMarcar() {

        setBackground(BG_MARCAR);
        setForeground(Color.BLACK);
        setText("M");

    }

    private void aplicarEstiloAbrir() {

        switch (campo.minasNaVizinhaca()){
            case 1:
                setForeground(TEXTO_VERDE);
                break;
            case 2:
                setForeground(Color.BLUE);
                break;
            case 3:
                setForeground(Color.YELLOW);
                break;
            case 4,5,6:
                setForeground(Color.ORANGE);
                break;
            default:
                setForeground(Color.RED);
        }

        String valor =  !campo.vizinhancaSegura() ? campo.minasNaVizinhaca() + "" : "";

        setBackground(BG_PADRAO);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setText(valor);

    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1 ){
            campo.abrir();
        }else {
            campo.alternarMarcacao();
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

}
