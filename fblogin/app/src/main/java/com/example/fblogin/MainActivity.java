package com.example.fblogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button orderBtn = findViewById(R.id.button);
        CheckBox check1 = findViewById(R.id.checkBox1);
        CheckBox check2 = findViewById(R.id.checkBox2);
        CheckBox check3 = findViewById(R.id.checkBox3);

        int PizzPrice = 200;
        int CoffeePrice = 60;
        int BurgerPrice = 120;
        int Bill;

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String result = "Selected Items";
                if(check1.isChecked()){
                    result += "\nPizza : Rs." + PizzPrice + "\n";
                }
                if(check2.isChecked()){
                    result += "\nCoffee : Rs." + CoffeePrice + "\n";
                }
                if(check3.isChecked()){
                    result += "\nBurger : Rs." + BurgerPrice + "\n";
                }

//                Toast toast=Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG);
//
//                toast.show();

                Context context = getApplicationContext();

                LayoutInflater inflater = getLayoutInflater();

                View toastRoot = inflater.inflate(R.layout.activity_main, null);
                EditText tv = toastRoot.findViewById(R.id.msg);
                tv.setWidth(200);
                tv.setHeight(100);

                Toast toast = new Toast(context);

                toast.setView(toastRoot);
                toast.show();
//      toast.setText("I am toast");
                toast.setDuration(Toast.LENGTH_LONG);


            }
        });




    }
}