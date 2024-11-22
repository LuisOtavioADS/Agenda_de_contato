package br.fepi.agendadecontatos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class CadastroContatoActivity extends AppCompatActivity {
    private EditText etNome;
    private EditText etTelefone;
    private EditText etEndereco;
    private Button btnSalvarContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        etEndereco = findViewById(R.id.etEndereco);
        btnSalvarContato = findViewById(R.id.btnSalvarContato);

        btnSalvarContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString();
                String telefone = etTelefone.getText().toString();
                String endereco = etEndereco.getText().toString();

                if (nome.isEmpty() || telefone.isEmpty() || endereco.isEmpty()) {
                    Toast.makeText(CadastroContatoActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Criar uma nova thread para a operação de inserção
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Contato contato = new Contato(nome, telefone, endereco);
                            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().contatoDao().insert(contato);
                            Log.d("CadastroContatoActivity", "Contato inserido: " + contato.getNome());

                            // Atualizar a UI na thread principal
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Intent resultIntent = new Intent();
                                    resultIntent.putExtra("contato", (Serializable) contato);
                                    setResult(RESULT_OK, resultIntent);
                                    finish();
                                }
                            });
                        }
                    }).start();
                }
            }
        });
    }
}