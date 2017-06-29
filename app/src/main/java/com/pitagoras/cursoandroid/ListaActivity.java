package com.pitagoras.cursoandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListaActivity extends AppCompatActivity {

    private MobileServiceClient mClient;
    private MobileServiceTable<Nome> nomeTable;
    private ArrayList<Nome> nomes = new ArrayList<>();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        try {
            mClient = new MobileServiceClient("https://pitagoras.azurewebsites.net", this);
            nomeTable = mClient.getTable("nomes", Nome.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.lista);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getNomes();
    }

    private void getNomes(){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    nomes = nomeTable.execute().get();
                } catch (InterruptedException | ExecutionException | MobileServiceException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mRecyclerView.setAdapter(new NomeAdapter(nomes));
            }
        }.execute();
    }
}
