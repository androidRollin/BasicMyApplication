package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText edtTxtName,edtTxtEmail,edtTxtPassword,edtTxtPassRepeat;
    private Button btnPickImage, btnRegister;
    private TextView txtWarnName, txtWarnEmail, txtWarnPassword, txtWarnPassRepeat;
    private Spinner spinnerCountry;
    private RadioGroup rgGender;
    private CheckBox cbAgreement;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Yet to talk about", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRegister();
            }
        }));
    }

    private void initRegister()
    {
        Log.d(TAG, "initRegister: Started");

        if(validateData())
        {
            if(cbAgreement.isChecked())
            {
                showSnackBar();
            }
            else
            {
                Toast.makeText(this, "You need to agree to the license agreement", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSnackBar()
    {
        Log.d(TAG, "initRegister: Started");
        txtWarnName.setVisibility(View.GONE);
        txtWarnEmail.setVisibility(View.GONE);
        txtWarnPassword.setVisibility(View.GONE);
        txtWarnPassRepeat.setVisibility((View.GONE));

        String name = edtTxtName.getText().toString();
        String email = edtTxtEmail.getText().toString();
        String country = spinnerCountry.getSelectedItem().toString();
        String gender = "";
        switch( rgGender.getCheckedRadioButtonId())
        {
            case R.id.rbMale:
                gender = "Male";
                break;
            case R.id.rbFemale:
                gender = "Female";
                break;
            case R.id.rbOther:
                gender = "Other";
            default:
                gender = "Unknown";
        }

        String snackText = "Name32131: " + name + "\n" +
                "Email: " + email + "\n" +
                "Gender: " + gender + "\n" +
                "Country: " + country + "\n" +
                "Password: " + edtTxtPassword.getText() + "\n" +
                "C-Password: " + edtTxtPassRepeat.getText();

        Log.d(TAG, "showSnackBar: Snack Bar Text: " + snackText );
        Snackbar.make(parent, snackText,Snackbar.LENGTH_SHORT)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edtTxtName.setText("");
                        edtTxtEmail.setText("");
                        edtTxtPassword.setText("");
                        edtTxtPassword.setText("");
                        edtTxtPassRepeat.setText("");
                        cbAgreement.setChecked(false);
                    }
                }).show();
    }
    private boolean validateData ()
    {
        boolean error1,error2,error3,error4,error5;
        error1 = error2 = error3 = error4 = error5 = false;
        Log.d(TAG, "validateData: started");
        if(edtTxtName.getText().toString().equals(""))
        {
            txtWarnName.setVisibility(View.VISIBLE);
            txtWarnName.setText("Enter your name");
            error1 = true;
        }
        if(edtTxtEmail.getText().toString().equals(""))
        {
            txtWarnEmail.setVisibility(View.VISIBLE);
            txtWarnEmail.setText("Enter your email");
            error2 = true;
        }
        if(edtTxtPassword.getText().toString().equals(""))
        {
            txtWarnPassword.setVisibility(View.VISIBLE);
            txtWarnPassword.setText("Enter your password");
            error3 = true;
        }
        if(edtTxtPassRepeat.getText().toString().equals(""))
        {
            txtWarnPassRepeat.setVisibility(View.VISIBLE);
            txtWarnPassRepeat.setText("Enter re-entered password");
            error4 = true;
        }
        if(!edtTxtPassword.getText().toString().equals(edtTxtPassRepeat.getText().toString())) {
            txtWarnPassRepeat.setVisibility(View.VISIBLE);
            txtWarnPassRepeat.setText("Password and re-entered password are not the same");
            error5 = true;
        }
        if(error1 == true || error2 == true || error3 == true || error4 == true || error5 == true)
            return false;
        else
            return true;
    }

    private void initViews() {
        Log.d(TAG, "initViews Started");
        edtTxtName =findViewById(R.id.edtTxtName);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        edtTxtPassRepeat = findViewById(R.id.edtTxtPassRepeat);

        btnPickImage = findViewById(R.id.btnPickImage);
        btnRegister = findViewById(R.id.btnRegister);

        txtWarnName = findViewById(R.id.txtWarnName);
        txtWarnEmail = findViewById(R.id.txtWarnEmail);
        txtWarnPassword = findViewById(R.id.txtWarnPassword);
        txtWarnPassRepeat =findViewById(R.id.txtWarnRepeatPass);

        spinnerCountry = findViewById(R.id.spinnerCountry);
        rgGender = findViewById(R.id.rgGender);
        cbAgreement = findViewById(R.id.cbAgreement);
        parent = findViewById(R.id.parent);


    }
}