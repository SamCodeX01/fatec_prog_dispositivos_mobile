package com.example.tarefa_06_app_agenda_de_contatos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btSalvar, btApagar, btConsultar;
    EditText edNome, edCelular, edEmail;
    TextView txtId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btSalvar = findViewById(R.id.btSalvar);
        btApagar = findViewById(R.id.btApagar);
        btConsultar = findViewById(R.id.btConsultar);
        edNome = findViewById(R.id.edNome);
        edCelular = findViewById(R.id.edCelular);
        edEmail = findViewById(R.id.edEmail);
        txtId = findViewById(R.id.txtId);

        // Inicialmente esconder o ID
        txtId.setVisibility(View.GONE);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edNome.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Digite um nome", Toast.LENGTH_SHORT).show();
                    return;
                }

                Contato c = new Contato();
                c.setNome(edNome.getText().toString().trim());
                c.setCelular(edCelular.getText().toString().trim());
                c.setEmail(edEmail.getText().toString().trim());

                ContatoDAO dao = new ContatoDAO(MainActivity.this);
                long resultado = dao.salvarContato(c);

                if (resultado != -1) {
                    Toast.makeText(MainActivity.this, "Contato gravado com sucesso! ID: " + resultado, Toast.LENGTH_SHORT).show();
                    // Mostrar o ID salvo
                    txtId.setText(String.valueOf(resultado));
                    txtId.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao gravar contato", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edNome.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Digite um nome para consultar", Toast.LENGTH_SHORT).show();
                    return;
                }

                Contato c;
                c = new Contato();
                ContatoDAO dao;

//                ContatoDAO dao = new ContatoDAO(MainActivity.this);
                dao = new ContatoDAO(MainActivity.this);
                c = dao.consultarContatoPorNome(edNome.getText().toString().trim());

                if (c != null && c.getId() > 0) {
                    txtId.setText(String.valueOf(c.getId()));
                    txtId.setVisibility(View.VISIBLE);
                    edNome.setText(c.getNome());
                    edCelular.setText(c.getCelular());
                    edEmail.setText(c.getEmail());
                    Toast.makeText(MainActivity.this, "Contato encontrado!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Contato não cadastrado", Toast.LENGTH_SHORT).show();
                    limparCampos();
                }
            }
        });

        btApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtId.getText().toString().isEmpty() || txtId.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Consulte um contato primeiro", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    ContatoDAO dao = new ContatoDAO(MainActivity.this);
                    boolean sucesso = dao.excluirContato(Integer.parseInt(txtId.getText().toString()));
                    if (sucesso) {
                        Toast.makeText(MainActivity.this, "Contato Excluído com sucesso", Toast.LENGTH_SHORT).show();
                        limparCampos();
                    } else {
                        Toast.makeText(MainActivity.this, "Erro ao excluir contato", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "ID inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void clearCampos(View v){
        limparCampos();
    }
    public void limparCampos(){
        txtId.setText("");
        txtId.setVisibility(View.GONE);
        edNome.setText("");
        edCelular.setText("");
        edEmail.setText("");
    }
}