package com.example.tarefa_06_app_agenda_de_contatos;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class ContatoDAO extends SQLiteOpenHelper{
    public static final String NOME_BANCO = "bdcontatos";
    public static final int VERSAO_BANCO = 1;
    public static final String TABELA_CONTATO = "contato";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_CELULAR = "celular";
    public static final String COLUNA_EMAIL = "email";

    public ContatoDAO(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase) {
        // CORREÇÃO: Adicionar espaços faltantes entre colunas e tipos
        sqliteDatabase.execSQL("CREATE TABLE " + TABELA_CONTATO + " (" +
                COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUNA_NOME + " TEXT NOT NULL, " +
                COLUNA_CELULAR + " TEXT, " +  // Permite valores nulos
                COLUNA_EMAIL + " TEXT)"       // Permite valores nulos
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, int i, int i1) {
        sqliteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA_CONTATO);
        onCreate(sqliteDatabase);
    }

    public long salvarContato(Contato c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(COLUNA_NOME, c.getNome());
        valores.put(COLUNA_CELULAR, c.getCelular());
        valores.put(COLUNA_EMAIL, c.getEmail());

        long id = db.insert(TABELA_CONTATO, null, valores);
        db.close();
        return id; // Retorna o ID do contato inserido ou -1 se erro
    }

    public void atualizarContato(Contato c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(COLUNA_NOME, c.getNome());
        valores.put(COLUNA_CELULAR, c.getCelular());
        valores.put(COLUNA_EMAIL, c.getEmail());

        String parametro[] = {String.valueOf(c.getId())};
        db.update(TABELA_CONTATO, valores, "id=?", parametro);
        db.close();
    }

    public boolean excluirContato(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String parametro[] = {String.valueOf(id)};
        int resultado = db.delete(TABELA_CONTATO, "id = ?", parametro);
        db.close();
        return resultado > 0; // Retorna true se excluiu algum registro
    }

    public Contato consultarContatoPorNome(String pnome) {
        Contato c = null;
        String parametro[] = {pnome};
        // CORREÇÃO: Array de colunas deve ter cada coluna como elemento separado
        String campos[] = {COLUNA_ID, COLUNA_NOME, COLUNA_CELULAR, COLUNA_EMAIL};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.query(TABELA_CONTATO,
                campos,
                "nome = ?",
                parametro,
                null,
                null,
                null,
                null
        );

        if (cr != null && cr.moveToFirst()) {
            c = new Contato();
            c.setId(cr.getInt(0));
            c.setNome(cr.getString(1));
            c.setCelular(cr.getString(2));
            c.setEmail(cr.getString(3));
            cr.close();
        }
        db.close();
        return c;
    }
}