package com.example.jetsu.justjava;

import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity {

    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {


        displayPrice(quantity * 5);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(Integer.toString(number));

    }

    public void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("$ " + number);
    }

    public void increment(View view) {
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        quantity--;
        display(quantity);
    }
}
