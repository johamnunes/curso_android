package com.pitagoras.cursoandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private MobileServiceClient mClient;
    private MobileServiceTable<Nome> nomeTable;

    private EditText nomeEdit, sobrenomeEdit;
    private String nomeText, sobrenomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nomeEdit = (EditText) findViewById(R.id.nome_txt);
        sobrenomeEdit = (EditText) findViewById(R.id.sobrenome_txt);

        try {
            mClient = new MobileServiceClient("https://pitagoras.azurewebsites.net", this);
            nomeTable = mClient.getTable("nomes", Nome.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void onClickEnviar(View view){
        nomeText = nomeEdit.getText().toString();
        sobrenomeText = sobrenomeEdit.getText().toString();

        if(!nomeText.equals("") && !sobrenomeText.equals("")){
            final Nome nomeEnvio = new Nome();
            nomeEnvio.setNome(nomeText);
            nomeEnvio.setSobrenome(sobrenomeText);

            new AsyncTask<Void, Void, Void>(){

                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        nomeTable.insert(nomeEnvio).get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute();

        } else {
            Toast.makeText(this, R.string.sem_dados, Toast.LENGTH_LONG).show();
        }
    }

    public void onClickLista(View view){

    }
}
