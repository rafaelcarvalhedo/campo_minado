package br.pucgoias.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import br.pucgoias.R;
import br.pucgoias.modelo.Campo;
import br.pucgoias.modelo.Mina;
import br.pucgoias.negocio.Tabuleiro;

public class CampoMinadoAdapter extends BaseAdapter {
    private Context mContext;
    private Tabuleiro tabuleiro;

    // Gets the context so it can be used later
    public CampoMinadoAdapter(Context c, Tabuleiro tabuleiro) {
        mContext = c;
        this.tabuleiro = tabuleiro;
    }

    // Total number of things contained within the adapter
    public int getCount() {
        return tabuleiro.getNumeroSlots();
    }

    // Require for structure, not really used in my code.
    public Object getItem(int position) {
        return null;
    }

    // Require for structure, not really used in my code. Can
    // be used to get the id of an item in the adapter for
    // manual control.
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position,
                        View convertView, ViewGroup parent) {
        Button btn;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            btn = new Button(mContext);
            btn.setLayoutParams(new GridView.LayoutParams(100, 55));
            btn.setPadding(8, 8, 8, 8);
        }
        else {
            btn = (Button) convertView;
        }
        Campo campo = tabuleiro.getCampo(position);
        btn.setText(campo instanceof Mina ? "Mina" : "Campo Livre");
        // filenames is an array of strings
        btn.setTextColor(Color.WHITE);
        btn.setId(position);

        return btn;
    }

}
