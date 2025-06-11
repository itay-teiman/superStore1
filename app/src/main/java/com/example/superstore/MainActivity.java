package com.example.superstore;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button b1;
    ListView l1;
    ArrayList<String> products;
    ArrayAdapter<String> adapter;

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

        b1 = findViewById(R.id.button);
        l1 = findViewById(R.id.Liste);
        products = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        l1.setAdapter(adapter);

        b1.setOnClickListener(view -> showAddProductDialog());
    }

    private void showAddProductDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("הוסף מוצר");

        final EditText input = new EditText(this);
        input.setHint("שם המוצר");
        builder.setView(input);

        builder.setPositiveButton("הוסף מוצר", (dialog, which) -> {
            String product = input.getText().toString().trim();
            if (!product.isEmpty()) {
                products.add(product);
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("ביטול", null);
        builder.show();
    }
}
