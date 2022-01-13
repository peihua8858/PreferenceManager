package com.fz.premanger.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fz.premanager.PreferenceManager;
import com.fz.premanager.PreferenceManagerImpl;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_TEST_SAVE_OBJ_LIST = "test_save_obj_list";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvSave = findViewById(R.id.tv_save);
        TextView tvRead = findViewById(R.id.tv_read);
        MMKV.initialize(this);
        PreferenceManager.initManager(this, new PreferenceManagerImpl(MMKV.defaultMMKV()));
        tvSave.setOnClickListener(this::saveStringData);
        tvRead.setOnClickListener(this::readStringData);

    }

    private void readData(View view) {
//        List<CountryNew> result = PreferenceManager.getInstance().readArrayListParcelable(KEY_TEST_SAVE_OBJ_LIST, new TypeToken<List<CountryNew>>() {
//        }.getType());
        List<CountryNew> result = PreferenceManager.getInstance().readArrayListParcelable(KEY_TEST_SAVE_OBJ_LIST,new ArrayList<CountryNew>().getClass());
        Log.d("MainActivity", "读取结果：" + result.toString());
    }

    private void saveData(View view) {
        List<CountryNew> countryNewList = new ArrayList<>();
        CountryNew countryNew = new CountryNew();
        List<LanguageBean> languageBeans = new ArrayList<>();
        languageBeans.add(new LanguageBean());
        countryNew.support_lang = languageBeans;
        countryNewList.add(countryNew);
        boolean result = PreferenceManager.getInstance().saveArrayListParcelable(KEY_TEST_SAVE_OBJ_LIST, countryNewList);
        Log.d("MainActivity", "保存结果：" + result);
    }
    private void readStringData(View view) {
//        List<CountryNew> result = PreferenceManager.getInstance().readArrayListParcelable(KEY_TEST_SAVE_OBJ_LIST, new TypeToken<List<CountryNew>>() {
//        }.getType());
        List<String> result = PreferenceManager.getInstance().readArrayList(KEY_TEST_SAVE_OBJ_LIST+"_11",new ArrayList<String>().getClass());
        Log.d("MainActivity", "读取结果：" + result.toString());
    }
    private void saveStringData(View view) {
        List<String> countryNewList = new ArrayList<>();
        countryNewList.add("sssssss");
        countryNewList.add("bbbb");
        countryNewList.add("aaaaaaaaaa");
        countryNewList.add("ddddddddd");
        boolean result = PreferenceManager.getInstance().saveArrayList(KEY_TEST_SAVE_OBJ_LIST+"_11", countryNewList);
        Log.d("MainActivity", "保存结果：" + result);
    }
}
