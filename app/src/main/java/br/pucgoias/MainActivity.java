package br.pucgoias;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import br.pucgoias.adapter.CampoMinadoAdapter;
import br.pucgoias.adapter.SpinAdapter;
import br.pucgoias.modelo.NivelJogoEnum;
import br.pucgoias.negocio.Tabuleiro;

public class MainActivity extends AppCompatActivity {

    private final Integer NUMERO_ACERTOS_VITORIA = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializa spinner nivel Jogo
        SpinAdapter nivelJogoAdapter = new SpinAdapter(this,R.layout.spinner,NivelJogoEnum.values());
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(nivelJogoAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                atualizarTabuleiro(NivelJogoEnum.values()[spinner.getSelectedItemPosition()]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // Inicializa Tabuleiro
        atualizarTabuleiro(NivelJogoEnum.FACIL);
        // Atualiza Numero Acertos
        atualizarNumeroAcertos();
    }

    private void atualizarNumeroAcertos(){
        final GridView gridView = findViewById(R.id.gridView);
        final Spinner spinner = findViewById(R.id.spinner);
        CampoMinadoAdapter campoMinadoAdapter = (CampoMinadoAdapter) gridView.getAdapter();
        Integer numeroAcertos = campoMinadoAdapter.getNumeroCamposLivreAbertos();
        Integer numeroErros = campoMinadoAdapter.getNumeroCampoMinadoAbertos();
        atualizaNumeroAcertos(numeroAcertos);
        if(numeroAcertos == NUMERO_ACERTOS_VITORIA && numeroErros == 0){
            showModalMessage(R.string.ganhou);
            atualizarTabuleiro(NivelJogoEnum.values()[spinner.getSelectedItemPosition()]);
        } else if(numeroErros > 0 ){
            showModalMessage(R.string.perdeu);
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            atualizarTabuleiro(NivelJogoEnum.values()[spinner.getSelectedItemPosition()]);
                        }
                    }, Toast.LENGTH_LONG * 1000);
        }

    }



    private void atualizarTabuleiro(NivelJogoEnum nivelJogoEnum){
        GridView gridView = findViewById(R.id.gridView);
        CampoMinadoAdapter campoMinadoAdapter = new CampoMinadoAdapter(getApplicationContext(),new Tabuleiro(nivelJogoEnum));
        campoMinadoAdapter.setOnClickListener(view -> atualizarNumeroAcertos());
        gridView.setAdapter(campoMinadoAdapter);
        atualizaNumeroAcertos(0);
    }

    private void atualizaNumeroAcertos( Integer acertos){
        TextView textView = findViewById(R.id.textViewAcertos);
        textView.setText(String.format("Acertos %s",acertos));
    }
    private void showModalMessage(@StringRes int resId){
        Toast.makeText(getApplicationContext(),resId,Toast.LENGTH_LONG).show();
    }


}
