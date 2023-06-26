package com.samuelbaron.contatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.samuelbaron.contatos.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    public static final String CONTACT_ID = "CONTACT_ID"; //todo: pra que serve?
    public static final int NEW_CONTACT = -1; //todo: pra que serve?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}