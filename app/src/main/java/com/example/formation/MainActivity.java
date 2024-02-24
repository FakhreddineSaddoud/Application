package com.example.formation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btAdd, btSub, btMul, btDiv;
    EditText editTextN1, editTextN2;
    TextView textView;
    int num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAdd =findViewById(R.id.btn_add);
        btSub =findViewById(R.id.btn_sous);
        btMul =findViewById(R.id.btn_mult);
        btDiv =findViewById(R.id.btn_div);
        editTextN1 =findViewById(R.id.num1);
        editTextN2 =findViewById(R.id.num2);
        textView = findViewById(R.id.Res);

        btAdd.setOnClickListener(this);
        btSub.setOnClickListener(this);
        btMul.setOnClickListener(this);
        btDiv.setOnClickListener(this);
    }

    public int getIntFromEditText(EditText editText){
        if(editText.getText().toString().equals("")){
            Toast.makeText(this, "Entrer la valeur!!!", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return Integer.parseInt(editText.getText().toString());
    }

    @Override
    public void onClick(View view) {

        num1 = getIntFromEditText(editTextN1);
        num2 = getIntFromEditText(editTextN2);

        switch(view.getId()){

            case R.id.btn_add:
                textView.setText("Résultat =" + (num1 + num2));
                break;

            case R.id.btn_sous:
                textView.setText("Résultat =" + (num1 - num2));
                break;

            case R.id.btn_mult:
                textView.setText("Résultat =" + (num1 * num2));
                break;

            case R.id.btn_div:
                textView.setText("Résultat =" + ((float)num1 / (float) num2));
                break;

        }
    }
}