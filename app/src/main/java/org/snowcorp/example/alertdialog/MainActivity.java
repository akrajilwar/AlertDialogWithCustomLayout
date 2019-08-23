package org.snowcorp.example.alertdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
 * Created by Akshay Raj on 23/8/19 at 18:39.
 * akshay@snowcorp.org
 * www.snowcorp.org
 */

public class MainActivity extends AppCompatActivity {
    private MaterialButton btnSimpleDialog, btnCustomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSimpleDialog = findViewById(R.id.button_simple_dialog);
        btnCustomDialog = findViewById(R.id.button_custom_dialog);

        init();
    }

    private void init() {
        btnSimpleDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAlertDialog();
            }
        });

        btnCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCustomDialog();
            }
        });
    }

    private void launchAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Alert Dialog")
                .setMessage("Are you sure?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Toast.makeText(MainActivity.this, "Request submit.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        builder.show();
    }

    private void launchCustomDialog() {
        View customLayout = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_dialog, null);

        final TextInputLayout editMessage = customLayout.findViewById(R.id.edit_message);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setView(customLayout)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String message = Objects.requireNonNull(editMessage.getEditText()).getText().toString();

                        // Dismiss Dialog
                        dialogInterface.dismiss();

                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        builder.show();
    }
}
