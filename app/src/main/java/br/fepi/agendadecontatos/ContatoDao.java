package br.fepi.agendadecontatos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContatoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contato contato);
    @Query("SELECT * FROM contatos")
    List<Contato> getAllContatos();
}
