package com.example.barvolumejava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barvolumejava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    Button calculateButton;
    EditText length, width, height;
    TextView result;
    Double volume;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //add binding configurations
        //initialize binding variable
        com.example.barvolumejava.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //match the variable with the xml id
        length = binding.edtLength;
        width = binding.edtWidth;
        height = binding.edtHeight;
        result = binding.tvResult;
        calculateButton = binding.btnCalculate;

        //add logic when button is being clicked
        calculateButton.setOnClickListener(v -> {
            if (length.length() == 0){
                length.setError("This filled is required!");
            }else if (width.length() == 0){
                width.setError("This filled is required");
            }else if (height.length() == 0){
                height.setError("This filled is required");
            }else {
                //convert each inputs into double data type
                volume = Double.parseDouble(length.getText().toString()) * Double.parseDouble(width.getText().toString()) * Double.parseDouble(height.getText().toString());

                //convert back to string data type
                result.setText(volume.toString().trim());
                Toast.makeText(MainActivity.this, "Calculation succeeded!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //to save the value of volume
        outState.putDouble("VOLUME_RESULT", volume);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //get the saved value from the instance state
        volume = savedInstanceState.getDouble("VOLUME_RESULT");
        result.setText(volume.toString());
    }
}