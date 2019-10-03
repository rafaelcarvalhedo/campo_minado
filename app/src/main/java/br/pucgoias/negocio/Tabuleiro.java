package br.pucgoias.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.pucgoias.modelo.Campo;
import br.pucgoias.modelo.CampoLivre;
import br.pucgoias.modelo.Mina;
import br.pucgoias.modelo.NivelJogoEnum;

public class Tabuleiro {

    private List<Campo> slots;
    private final NivelJogoEnum nivelJogoEnum;

    private static final int NUMERO_SLOTS_X = 4;
    private static final int NUMERO_SLOTS_Y = 9;

    public Tabuleiro(NivelJogoEnum nivelJogoEnum) {
        this.nivelJogoEnum = nivelJogoEnum;
        this.iniciarTabuleiro();
    }

    private void iniciarTabuleiro() {
        //Instancia matriz com a quantidade de slots
        slots = new ArrayList<Campo>();

        //Inicializa a matriz como os campos livres
        for (int x = 0; x < NUMERO_SLOTS_X * NUMERO_SLOTS_Y; x++) {
            slots.add(new CampoLivre());
        }

        //Adicionas a quantidade de minas de acordo com o nível em posições aleatorias
        for (int i = 0; i < nivelJogoEnum.getQuantidadeMinas(); i++) {
            adicionarMinaAleaorio();
        }
    }

    private void adicionarMinaAleaorio() {
        Random random = new Random();
        int pos = random.nextInt(NUMERO_SLOTS_X * NUMERO_SLOTS_Y - 1);
        if (slots.get(pos) instanceof Mina) {
            adicionarMinaAleaorio();
        } else {
            slots.set(pos, new Mina());
        }
    }

    public int getNumeroSlots() {
        return NUMERO_SLOTS_X * NUMERO_SLOTS_Y;
    }

    public Campo getCampo(int posicao) {
        return slots.get(posicao);
    }

    public Integer getNumeroCamposAbertos() {
        return (int) slots.stream().filter(p -> p.isAberto() && p instanceof CampoLivre).count();
    }

    public Integer getNumeroCampoMinadoAbertos() {
        return (int) slots.stream().filter(p -> p.isAberto() && p instanceof Mina).count();
    }
}
