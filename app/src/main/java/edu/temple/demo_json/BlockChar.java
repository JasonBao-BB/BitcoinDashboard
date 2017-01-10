package edu.temple.demo_json;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class BlockChar extends Fragment {

    WebView webView;
    Spinner spinner;
    String url = "http://chart.yahoo.com/z?s=BTCUSD=X&t=";

    public BlockChar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_block_char, container, false);


        webView = (WebView) v.findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        spinner = (Spinner) v.findViewById(R.id.dateSelector);
        String[] choosedType = {"1 min","5 min", "1 Day", "5 Day"};
        ArrayAdapter dateAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, choosedType);
        spinner.setAdapter(dateAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeType(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return v;
    }

    private void changeType(String string) {

        //Depending on what the user selected in the spinner '1d' or '5d' is added
        //to the website and then loaded
        switch (string) {
            case "1 Day":
                webView.loadUrl(url + "1d");
                break;
            case "5 Day":
                webView.loadUrl(url + "5d");
                break;

            case "1 min":
                webView.loadUrl(url + "1min");
                break;

            case "5 min":
                webView.loadUrl(url + "5min");
                break;

        } //End switch
    } //End getTimeInterval
} //End ChartFragment
