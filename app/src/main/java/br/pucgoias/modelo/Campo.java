package br.pucgoias.modelo;

public abstract class Campo {

    private boolean aberto;

    public void abrirCampo(){
        this.aberto = true;
    }

    public boolean isAberto() {
        return aberto;
    }
}
