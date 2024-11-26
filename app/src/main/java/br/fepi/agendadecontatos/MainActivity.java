package br.fepi.agendadecontatos;

import android.content.Intent;
import android.graphics.Color; // Importando a classe Color para usar cores
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView; // Importando TextView

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAddContact;
    private RecyclerView recyclerViewContacts;
    private ContatoAdapter contatoAdapter;
    private List<Contato> contatoList;
    private TextView textViewChangeColor; // Definindo o TextView para mudar a cor

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddContact = findViewById(R.id.btnAddContact);
        recyclerViewContacts = findViewById(R.id.recyclerViewContacts);
        textViewChangeColor = findViewById(R.id.textView2); // Inicializando o TextView

        contatoList = new ArrayList<>();
        contatoAdapter = new ContatoAdapter(contatoList);
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContacts.setAdapter(contatoAdapter);

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroContatoActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        // Mudando a cor do TextView assim que a Activity for criada
        changeTextColor(textViewChangeColor, Color.BLACK);
    }

    // Função para mudar a cor do TextView
    private void changeTextColor(TextView textView, int color) {
        // Usando o SpannableString para mudar a cor do texto
        String text = textView.getText().toString(); // Pegando o texto atual do TextView
        SpannableString spannableString = new SpannableString(text);

        // Aplicando a cor ao texto inteiro ou uma parte dele
        spannableString.setSpan(new ForegroundColorSpan(color), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Definindo o novo texto com a cor alterada
        textView.setText(spannableString);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Contato contato = (Contato) data.getSerializableExtra("contato");
            contatoList.add(contato);
            contatoAdapter.notifyDataSetChanged();
        }
    }
}