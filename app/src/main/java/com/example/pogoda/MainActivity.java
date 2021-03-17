package com.example.pogoda;


import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Document doc;
    private Thread secThread;
    private Runnable runnable;

    private TextView titleForRv;
    private String titleForRvTxt;

    private AdapterLIst adapterLIst;

    private RecyclerView rv;

    private List<ListItemModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("TAG", "onCreate: " + titleForRvTxt );
        init();

//
    }

    private void getWeb() {
        try {

            doc = Jsoup.connect("https://www.akchabar.kg/ru/exchange-rates/dollar/").get();
            Elements updateDataInfo = doc.getElementsByClass("date_big ffo");
            titleForRvTxt = updateDataInfo.get(0).text();
//
//           titleForRv.setText(titleForRvTxt);
            Log.e("TAG", "onCreate: " + titleForRvTxt );
//            titleForRv.setText(titleForRvTxt);


//
//                    Log.e("TAG", "getWeb: " + updateDataInfo.get(0).text());

            Elements table = doc.getElementsByTag("tbody");
            Element our_table = table.get(0);
            for (int i = 0; i < our_table.childrenSize(); i++) {
                ListItemModel items = new ListItemModel();
                items.setNameBank(our_table.children().get(i).child(0).text());
                items.setBye(our_table.children().get(i).child(1).text());
                items.setSale(our_table.children().get(i).child(2).text());
                list.add(items);
            }

            runOnUiThread(() -> runOnUiThread(() ->
                    adapterLIst.notifyDataSetChanged()));


        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void init() {

        titleForRv = findViewById(R.id.titleForRv);
        rv = findViewById(R.id.rv);
        adapterLIst = new AdapterLIst(list);



        rv.setAdapter(adapterLIst);

        runnable = this::getWeb;

        secThread = new Thread(runnable);
        secThread.start();

    }
}