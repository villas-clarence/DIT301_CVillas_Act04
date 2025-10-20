package com.example.eventpracticeapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAge;
    private Button buttonSubmit;
    private TextView textViewResult;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        setupEventHandling();
    }

    private void initializeViews() {
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        textViewResult = findViewById(R.id.textViewResult);
        rootView = findViewById(android.R.id.content);
    }

    private void setupEventHandling() {
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmitButtonClick();
            }
        });

        setupInputValidation();
    }

    private void setupInputValidation() {
        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clearFieldError(editTextName);
                
                if (s.length() > 0) {
                    if (!s.toString().matches("^[a-zA-Z\\s]+$")) {
                        showFieldError(editTextName, "Name should contain only letters and spaces");
                    } else if (s.length() < 2) {
                        showFieldError(editTextName, "Name should be at least 2 characters");
                    } else {
                        showFieldSuccess(editTextName, "Name looks good!");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editTextAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clearFieldError(editTextAge);

                if (s.length() > 0) {
                    try {
                        int age = Integer.parseInt(s.toString());
                        if (age < 0) {
                            showFieldError(editTextAge, "Age cannot be negative");
                        } else if (age > 120) {
                            showFieldError(editTextAge, "Please enter a realistic age (0-120)");
                        } else {
                            showFieldSuccess(editTextAge, "Age looks good!");
                        }
                    } catch (NumberFormatException e) {
                        showFieldError(editTextAge, "Please enter a valid number");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void handleSubmitButtonClick() {
        String name = editTextName.getText().toString().trim();
        String ageString = editTextAge.getText().toString().trim();

        clearErrorHighlights();

        if (isInputValid(name, ageString)) {
            // Use try-catch block to handle invalid inputs (e.g., non-numeric age)
            try {
                int age = Integer.parseInt(ageString);
                displayResult(name, age);
                
                // Show success feedback with both Toast and Snackbar
                showSuccessToast("Information submitted successfully!");
                showSnackbar("Data saved successfully!", false);
                
            } catch (NumberFormatException e) {
                showErrorToast("Please enter a valid age number.");
                showSnackbar("Invalid age format. Please enter a number.", true);
                highlightErrorField(editTextAge);
                textViewResult.setText("Your result will appear here...");
            }
        }
    }

    private boolean isInputValid(String name, String ageString) {
        if (name.isEmpty() || ageString.isEmpty()) {
            showErrorToast("Please fill in all fields.");
            showSnackbar("All fields are required!", true);
            textViewResult.setText("Your result will appear here...");
            highlightEmptyFields(name, ageString);
            return false;
        }
        
        if (!name.matches("^[a-zA-Z\\s]+$")) {
            showErrorToast("Name should contain only letters and spaces.");
            showSnackbar("Invalid name format. Use only letters and spaces.", true);
            textViewResult.setText("Your result will appear here...");
            highlightErrorField(editTextName);
            return false;
        }
        
        return true;
    }

    private void displayResult(String name, int age) {
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

        String resultMessage = "Hello " + name + "!\n\n" +
                             "You are " + age + " years old.\n\n" +
                             "Age Category: " + getAgeCategory(age) + "\n\n" +
                             "Information validated successfully!";

        textViewResult.setText(resultMessage);
        textViewResult.setTextColor(ContextCompat.getColor(this, R.color.success_color));
    }

    private void showErrorToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void showSuccessToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // Demonstrate instant feedback through Snackbar messages
    private void showSnackbar(String message, boolean isError) {
        Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
        
        if (isError) {
            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.danger_color));
        } else {
            snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.success_color));
        }
        
        snackbar.show();
    }

    private void showFieldError(EditText field, String message) {
        field.setBackgroundColor(ContextCompat.getColor(this, R.color.danger_color));
        field.setAlpha(0.7f);
        
        showSnackbar(message, true);
    }

    private void showFieldSuccess(EditText field, String message) {
        field.setBackgroundColor(ContextCompat.getColor(this, R.color.success_color));
        field.setAlpha(0.8f);
        
        showSnackbar(message, false);
    }

    private void clearFieldError(EditText field) {
        field.setBackground(ContextCompat.getDrawable(this, R.drawable.input_background));
        field.setAlpha(1.0f);
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
