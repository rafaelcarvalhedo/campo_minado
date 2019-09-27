package br.pucgoias.negocio;

import java.util.Random;

import br.pucgoias.modelo.Campo;
import br.pucgoias.modelo.CampoLivre;
import br.pucgoias.modelo.Mina;
import br.pucgoias.modelo.NivelJogoEnum;

public class Tabuleiro {

    private Campo[][] slots;
    private NivelJogoEnum nivelJogoEnum;

    private static final int NUMERO_SLOTS_X = 7;
    private static final int NUMERO_SLOTS_Y = 6;

    public Tabuleiro(NivelJogoEnum nivelJogoEnum){
        this.nivelJogoEnum = nivelJogoEnum;
        this.iniciarTabuleiro();
    }

    private void iniciarTabuleiro(){
        //Instancia matriz com a quantidade de slots
        slots = new Campo[NUMERO_SLOTS_X][NUMERO_SLOTS_Y];

        //Inicializa a matriz como os campos livres
        for(int x = 0 ; x < NUMERO_SLOTS_X ; x++){
            for(int y = 0; y < NUMERO_SLOTS_Y; y++){
                slots[x][y] = new CampoLivre();
            }
        }

        //Adicionas a quantidade de minas de acordo com o nível em posições aleatorias
        for(int i = 0; i < nivelJogoEnum.getQuantidadeMinas(); i ++){
            adicionarMinaAleaorio();
        }
    }

    private void adicionarMinaAleaorio(){
        Random random = new Random();
        int posX = random.nextInt(NUMERO_SLOTS_X);
        int posY = random.nextInt(NUMERO_SLOTS_Y);
        slots[posX][posY] = new Mina();
    }

    public int getNumeroSlots(){
        return NUMERO_SLOTS_X * NUMERO_SLOTS_Y;
    }

    public Campo getCampo(int posicao){
        return slots[posicao/NUMERO_SLOTS_X][posicao%NUMERO_SLOTS_Y];
    }
}
