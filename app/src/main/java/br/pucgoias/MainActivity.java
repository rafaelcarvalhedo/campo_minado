package br.pucgoias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import br.pucgoias.adapter.CampoMinadoAdapter;
import br.pucgoias.modelo.NivelJogoEnum;
import br.pucgoias.negocio.Tabuleiro;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new CampoMinadoAdapter(getApplicationContext(),new Tabuleiro(NivelJogoEnum.FACIL)));
    }
}
