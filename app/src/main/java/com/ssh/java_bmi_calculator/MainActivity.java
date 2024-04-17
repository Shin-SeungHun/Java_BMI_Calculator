package com.ssh.java_bmi_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.ssh.java_bmi_calculator.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
//    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

//        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        Log.d("main", "create");

        EditText etHeight = binding.etHeight;
        EditText etWeight = binding.etWeight;
        Button buttonCalculate = binding.buttonCalculate;

        buttonCalculate.setOnClickListener(v -> {
            String heightString = etHeight.getText().toString().trim();
            String weightString = etWeight.getText().toString().trim();

            if (heightString.isEmpty() || weightString.isEmpty()) {
                Toast.makeText(this, "키와 몸무게를 입력하세요", Toast.LENGTH_SHORT).show();
            } else {
                double height = Double.parseDouble(heightString);
                double weight = Double.parseDouble(weightString);

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("height", height);
                intent.putExtra("weight", weight);
                startActivity(intent);
            }
        });


    }
}
