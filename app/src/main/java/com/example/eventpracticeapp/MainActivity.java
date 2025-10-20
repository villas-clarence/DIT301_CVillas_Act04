package com.example.eventpracticeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAge;
    private Button buttonSubmit;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        initializeViews();
        
        // Set up event handling
        setupEventHandling();
    }

    private void initializeViews() {
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        textViewResult = findViewById(R.id.textViewResult);
    }

    private void setupEventHandling() {
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmitButtonClick();
            }
        });
    }

    private void handleSubmitButtonClick() {
        // Get input values
        String name = editTextName.getText().toString().trim();
        String ageString = editTextAge.getText().toString().trim();

        // Clear any previous error highlights
        clearErrorHighlights();

        // Validate input - Show Toast message if fields are empty
        if (isInputValid(name, ageString)) {
            // Use try-catch block to handle invalid inputs (e.g., non-numeric age)
            try {
                // Parse age and display result
                int age = Integer.parseInt(ageString);
                displayResult(name, age);
                
                // Show success toast
                showSuccessToast("Information submitted successfully!");
                
            } catch (NumberFormatException e) {
                // Handle invalid age input
                showErrorToast("Please enter a valid age number.");
                highlightErrorField(editTextAge);
                textViewResult.setText("Your result will appear here...");
            }
        }
    }

    private boolean isInputValid(String name, String ageString) {
        // Check if fields are empty - Show Toast message if fields are empty
        if (name.isEmpty() || ageString.isEmpty()) {
            showErrorToast("Please fill in all fields.");
            textViewResult.setText("Your result will appear here...");
            highlightEmptyFields(name, ageString);
            return false;
        }
        
        // Check if name contains only letters and spaces
        if (!name.matches("^[a-zA-Z\\s]+$")) {
            showErrorToast("Name should contain only letters and spaces.");
            textViewResult.setText("Your result will appear here...");
            highlightErrorField(editTextName);
            return false;
        }
        
        return true;
    }

    private void displayResult(String name, int age) {
        // Validate age range
        if (age < 0) {
            showErrorToast("Age cannot be negative. Please enter a valid age.");
            highlightErrorField(editTextAge);
            textViewResult.setText("Your result will appear here...");
            return;
        }
        
        if (age > 120) {
            showErrorToast("Please enter a realistic age (0-120).");
            highlightErrorField(editTextAge);
            textViewResult.setText("Your result will appear here...");
            return;
        }

        // Create simple result message
        String resultMessage = "Hello " + name + "!\n\n" +
                             "You are " + age + " years old.\n\n" +
                             "Age Category: " + getAgeCategory(age) + "\n\n" +
                             "Information validated successfully!";
        
        // Display result with success color
        textViewResult.setText(resultMessage);
        textViewResult.setTextColor(ContextCompat.getColor(this, R.color.success_color));
    }

    private void showErrorToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void showSuccessToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void highlightEmptyFields(String name, String ageString) {
        if (name.isEmpty()) {
            highlightErrorField(editTextName);
        }
        if (ageString.isEmpty()) {
            highlightErrorField(editTextAge);
        }
    }

    private void highlightErrorField(EditText field) {
        field.setBackgroundColor(ContextCompat.getColor(this, R.color.danger_color));
        field.setAlpha(0.7f);
        
        // Clear error highlight after 2 seconds
        field.postDelayed(new Runnable() {
            @Override
            public void run() {
                field.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.input_background));
                field.setAlpha(1.0f);
            }
        }, 2000);
    }

    private void clearErrorHighlights() {
        editTextName.setBackground(ContextCompat.getDrawable(this, R.drawable.input_background));
        editTextAge.setBackground(ContextCompat.getDrawable(this, R.drawable.input_background));
        editTextName.setAlpha(1.0f);
        editTextAge.setAlpha(1.0f);
    }

    private String getAgeCategory(int age) {
        if (age < 13) {
            return "Child";
        } else if (age < 20) {
            return "Teenager";
        } else if (age < 30) {
            return "Young Adult";
        } else if (age < 50) {
            return "Adult";
        } else if (age < 65) {
            return "Middle-aged";
        } else {
            return "Senior";
        }
    }
}
