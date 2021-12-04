package com.example.rumah7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainRead extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private MyDatabase db;
    private List<Jaket> listJaket = new
            ArrayList<Jaket>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new MyDatabase(this);
        adapter_off = new CustomListAdapter(this, listJaket);
        mListView = (ListView) findViewById(R.id.list_jenis);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        listJaket.clear();
        List<Jaket> jaket = db.ReadJaket();
        for (Jaket mhs : jaket) {
            Jaket daftar = new Jaket();
            daftar.set_id(mhs.get_id());
            daftar.set_nama(mhs.get_nama());
            daftar.set_jenis(mhs.get_jenis());
            listJaket.add(daftar);
            if ((listJaket.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int
            i, long l) {
        Object o = mListView.getItemAtPosition(i);
        Jaket detailMhs = (Jaket)o;
        String Sid = detailMhs.get_id();
        String Snama = detailMhs.get_nama();
        String Sjenis = detailMhs.get_jenis();
        Intent goUpdel = new Intent(MainRead.this,
                com.example.rumah7.MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Ijenis", Sjenis);
        startActivity(goUpdel);
    }
    @Override
    protected void onResume() {
        super.onResume();
        listJaket.clear();
        mListView.setAdapter(adapter_off);
        List<Jaket> jaket = db.ReadJaket();
        for (Jaket jkt : jaket) {
            Jaket daftar = new Jaket();
            daftar.set_id(jkt.get_id());
            daftar.set_nama(jkt.get_nama());
            daftar.set_jenis(jkt.get_jenis());
            listJaket.add(daftar);
            if ((listJaket.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
}
