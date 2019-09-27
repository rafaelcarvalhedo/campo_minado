package br.pucgoias.modelo;

import br.pucgoias.R;

public enum NivelJogoEnum {

    FACIL(R.string.nivel_facil,10),
    MEDIO(R.string.nivel_medio,15),
    DIFICIL(R.string.nivel_dificil,25);

    private int stringResource;
    private int quantidadeMinas;

    NivelJogoEnum(int stringResource, int quantidadeMinas) {
        this.stringResource = stringResource;
        this.quantidadeMinas = quantidadeMinas;
    }

    public int getStringResource() {
        return stringResource;
    }

    public void setStringResource(int stringResource) {
        this.stringResource = stringResource;
    }

    public int getQuantidadeMinas() {
        return quantidadeMinas;
    }
}
