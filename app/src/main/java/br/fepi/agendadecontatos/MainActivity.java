package br.fepi.agendadecontatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddContact = findViewById(R.id.btnAddContact);
        recyclerViewContacts = findViewById(R.id.recyclerViewContacts);

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
