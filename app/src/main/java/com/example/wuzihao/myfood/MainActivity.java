package com.example.wuzihao.myfood;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private PagerTitleStrip pagerTitleStrip;
    private MyAdapter adapter;
    private List<View> list;
    private List<String> titlelist;
    private Spinner spinner;
    private ArrayAdapter steplist;
    private String mapstep;

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



        viewPager =(ViewPager)findViewById(R.id.viewrpager);
        pagerTitleStrip = (PagerTitleStrip)findViewById(R.id.pagertitle);

        list = new ArrayList<View>();
        list.add(LayoutInflater.from(this).inflate(R.layout.view1, null));
        list.add(LayoutInflater.from(this).inflate(R.layout.view2, null));
        View view3 = LayoutInflater.from(this).inflate(R.layout.view3, null);
        list.add(view3);
        spinner = (Spinner) view3.findViewById(R.id.My_spinner);
        titlelist = new ArrayList<String>();
        titlelist.add("第一页");
        titlelist.add("第二页");
        titlelist.add("第三页");
        adapter = new MyAdapter(titlelist, list);
        viewPager.setAdapter(adapter);

        iniSpinner();
    }

    public void TL(View V){
        Toast.makeText(this,"真懶",Toast.LENGTH_SHORT).show();
    }
    public void FL(View V){
        Toast.makeText(this,"假懶",Toast.LENGTH_SHORT).show();
    }

    private void iniSpinner(){
        spinner = (Spinner)findViewById(R.id.My_spinner);

        steplist =new ArrayAdapter(this,android.R.layout.simple_spinner_item, new String[]{"1","2","3"});
        steplist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(steplist);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView adapterView, View view, int position, long id){
                mapstep = adapterView.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, "您選擇"+adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
            }
            public void onNothingSelected(AdapterView arg0) {
                Toast.makeText(MainActivity.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
            }
        });
    }


}

