package br.fepi.agendadecontatos;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalheContatoActivity extends AppCompatActivity {
    private TextView tvDetalheNome;
    private TextView tvDetalheTelefone;
    private TextView tvDetalheEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_contato);

        tvDetalheNome = findViewById(R.id.tvDetalheNome);
        tvDetalheTelefone = findViewById(R.id.tvDetalheTelefone);
        tvDetalheEndereco = findViewById(R.id.tvDetalheEndereco);

        Contato contato = (Contato) getIntent().getSerializableExtra("contato");
        if (contato != null) {
            tvDetalheNome.setText(contato.getNome());
            tvDetalheTelefone.setText(contato.getTelefone());
            tvDetalheEndereco.setText(contato.getEndereco());
        }
    }
}
