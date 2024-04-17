package com.ssh.java_bmi_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.ssh.java_bmi_calculator.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {
    ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);

        Intent intent = getIntent();
        double height = intent.getDoubleExtra("height", 0);
        double weight = intent.getDoubleExtra("weight", 0);

        double bmi = calcluateBmi(height, weight);
        String bmiStatus = statusBmi(bmi);

        TextView tvHeight = binding.tvHeight;
        TextView tvWeight = binding.tvWeight;

        tvHeight.setText(String.valueOf(height));
        tvWeight.setText(String.valueOf(weight));

        TextView tvResult = binding.tvResult;
        tvResult.setText("당신의 체질량지수(BMI)는 " + bmi + "이며, " + bmiStatus + "입니다.");

        Button button = binding.button;

        button.setOnClickListener(v -> {
            Intent intentMove = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intentMove);
            finish();
        });
    }

    private double calcluateBmi(double height, double weight) {
        return Math.round(weight / ((height / 100) * (height / 100)));
    }

    private String statusBmi(double bmi) {
        String result = "저체중";

        if (bmi >= 35) {
            result = "고도 비만";
        } else if (bmi >= 30) {
            result = "2단계 비만";
        } else if (bmi >= 25) {
            result = "1단계 비만";
        } else if (bmi >= 23) {
            result = "과체중";
        } else if (bmi >= 18.5) {
            result = "정상";
        }
        return result;
    }
}