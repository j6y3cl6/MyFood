package com.example.wuzihao.myfood;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private PagerTitleStrip pagerTitleStrip;
    private MyAdapter adapter;
    private List<View> list;
    private List<String> titlelist;
    private ArrayAdapter steplist;
    private WebView webView;
    private MyListener listener;
    private LocationManager lmgr;
    private double lat,lng;
    private EditText EN,ET,EA;
    private View view1,view3;
    private MyDBHelper helper;
    private SQLiteDatabase db;
    private ListView food_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);
        }

        helper = new MyDBHelper(this);
        db = helper.getWritableDatabase();



        listener = new MyListener();
        lmgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        lmgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);


        viewPager =(ViewPager)findViewById(R.id.viewrpager);
        pagerTitleStrip = (PagerTitleStrip)findViewById(R.id.pagertitle);

        list = new ArrayList<View>();
//        list.add(LayoutInflater.from(this).inflate(R.layout.view1, null));

        iniview1();


        list.add(view1);
        list.add(LayoutInflater.from(this).inflate(R.layout.view2, null));
        iniview3();

        list.add(view3);
//        spinner = (Spinner) view3.findViewById(R.id.My_spinner);
        titlelist = new ArrayList<String>();
        titlelist.add("第一页");
        titlelist.add("第二页");
        titlelist.add("第三页");
        adapter = new MyAdapter(titlelist, list);
        viewPager.setAdapter(adapter);

    }

    private void iniview1(){
        view1 = LayoutInflater.from(this).inflate(R.layout.view1,null);
        webView=(WebView)view1.findViewById(R.id.wv);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.requestFocus();
        webView.setWebViewClient(new MyWebViewClient());
    }

    private void iniview3(){
        view3 = LayoutInflater.from(this).inflate(R.layout.view3, null);
        EN = (EditText)view3.findViewById(R.id.ETname);
        ET = (EditText)view3.findViewById(R.id.ETtag);
        EA = (EditText)view3.findViewById(R.id.ETaddr);

        food_list = (ListView)view3.findViewById(R.id.food_list);
        ShowMenu();
    }

    public class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    public void TL(View V){
        webView.loadUrl("https://www.google.com.tw/maps/search/吃/@"+lat+","+lng+",16z");
        Toast.makeText(this,"真懶",Toast.LENGTH_SHORT).show();
    }
    public void FL(View V){
        webView.loadUrl("https://www.google.com.tw/maps/place/台中榮華街/16z");
        Toast.makeText(this,"假懶",Toast.LENGTH_SHORT).show();
    }


    private class MyListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            lat = location.getLatitude();
            lng = location.getLongitude();

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    private void ShowMenu(){


        Cursor c = db.query("foodMenu",null,null,null,null,null,null);
        c.moveToFirst();
        while (c.moveToNext()) {
            String name = c.getString(c.getColumnIndex("name"));
            String tag = c.getString(c.getColumnIndex("tag"));
            String addr = c.getString(c.getColumnIndex("addr"));
        }
    }

    public void InsertMeny(View V){
        MyFoodMenu myFoodMenu = new MyFoodMenu(
                EN.getText().toString(),
                ET.getText().toString(),
                EA.getText().toString());

        ContentValues values= new ContentValues();
        values.put("name",myFoodMenu.getName());
        values.put("tag",myFoodMenu.getTag());
        values.put("addr",myFoodMenu.getAddr());
        db.insert("foodMenu",null,values);

        ShowMenu();
    }

}

