package com.example.jetsu.justjava;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.jetsu.justjava.R.string.chocolate;

public class MainActivity extends AppCompatActivity {

    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void submitOrder(View view) {


        int price = 5;

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        boolean creamCheck = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean chocolateCheck = chocolateCheckBox.isChecked();

        if(creamCheck)
            price++;

        if(chocolateCheck)
            price = price +2;


        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name =  nameField.getText().toString();


        String priceMessage = createOrderSummary(price,creamCheck,chocolateCheck, name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject, name));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if(intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);


 /**
        //for checking out example intent

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("geo:147.6, -123.3"));

        if(i.getIntent().resolveActivity(getPackageManager()) != null)
            startActivity(i);
 */
    }









    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(Integer.toString(number));
    }


    public void increment(View view) {


        if(quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity++;
        display(quantity);
    }

    public void decrement(View view) {


        if(quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity--;
        display(quantity);
    }



    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String name){

        int thisPrice = quantity*price;

        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage = priceMessage + "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream);
        priceMessage = priceMessage + "\n" + getString(R.string.order_summary_chocolate, addChocolate);
        priceMessage = priceMessage + "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage = priceMessage + "\n" + getString(R.string.order_summary_price, thisPrice);
        priceMessage = priceMessage + "\n" + getString(R.string.thank_you);
        return priceMessage;
    }


}
