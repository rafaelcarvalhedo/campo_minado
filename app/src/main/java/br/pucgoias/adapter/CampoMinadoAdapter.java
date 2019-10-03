package br.pucgoias.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import br.pucgoias.R;
import br.pucgoias.modelo.Campo;
import br.pucgoias.modelo.Mina;
import br.pucgoias.negocio.Tabuleiro;

public class CampoMinadoAdapter extends BaseAdapter {
    private final Context mContext;
    private final Tabuleiro tabuleiro;
    private AdapterView.OnClickListener onClickListener;
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
        final ImageView btn;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            btn = new ImageView (mContext);
//            btn.setLayoutParams(new GridView.LayoutParams(100, 55));
            btn.setLayoutParams(new GridView.LayoutParams(150, 150));
            btn.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        else {
            btn = (ImageView ) convertView;
        }
        btn.setImageResource(R.drawable.grass);
        final Campo campo = tabuleiro.getCampo(position);
        btn.setOnClickListener(view -> {
            campo.abrirCampo();
            if(campo instanceof Mina) {
                btn.setImageResource(R.drawable.bomba);
            } else {
                btn.setColorFilter(R.color.colorPrimaryDark);
            }
            onClickListener.onClick(view);
        });
        btn.setId(position);

        return btn;
    }

    public Integer getNumeroCamposLivreAbertos(){
        return tabuleiro.getNumeroCamposAbertos();
    }
    public Integer getNumeroCampoMinadoAbertos(){
        return tabuleiro.getNumeroCampoMinadoAbertos();
    }
    public void setOnClickListener(AdapterView.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
