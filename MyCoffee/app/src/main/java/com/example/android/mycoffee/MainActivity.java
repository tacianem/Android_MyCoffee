package com.example.android.mycoffee;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

//CONTROL class

public class MainActivity extends AppCompatActivity {

    private Order order = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        CheckBox creamBox = (CheckBox) this.findViewById(R.id.cream);
        boolean creamCheck = creamBox.isChecked();

        CheckBox chocBox = (CheckBox) this.findViewById(R.id.chocolate);
        boolean chocCheck = chocBox.isChecked();

        TextView nameText = (TextView) this.findViewById(R.id.name);
        String name = nameText.getText().toString();
        String subject = this.getString(R.string.order_subject, name);
        String orderName = this.getString(R.string.order_name, name);
        String title = this.getString(R.string.order_title);
        String quant = this.getString(R.string.order_quantity, order.getQuantity());
        String price = this.getString(R.string.order_price, order.getPrice(creamCheck,chocCheck));
        String thanks = this.getString(R.string.order_thanks);
        String creamChoc = this.getString(R.string.order_cream_chocolate);
        String cream = this.getString(R.string.order_cream);
        String choc = this.getString(R.string.order_chocolate);

        String orderBase = title + "\n\n" + orderName;
        String order = "\n" + quant + "\n"+ price + "\n" + thanks;

        if (creamCheck && chocCheck) {
            this.displayMessage(subject, orderBase + "\n" + creamChoc + order);
            return;
        }

        if (creamCheck) {
            this.displayMessage(subject, orderBase + "\n" + cream + order);
            return;
        }

        if (chocCheck) {
            this.displayMessage(subject, orderBase + "\n" + choc + order);
            return;
        }

        this.displayMessage(subject, orderBase + order);
    }

    public void increment(View view) {
        int quantity = this.order.getQuantity();
        if (quantity == 100) {
            Toast.makeText(this, R.string.increment, Toast.LENGTH_LONG).show();
            return;
        }
        displayQuantity(this.order.increment());
    }

    public void decrement(View view) {
        int quantity = this.order.getQuantity();
        if (quantity == 1) {
            Toast.makeText(this, R.string.decrement, Toast.LENGTH_LONG).show();
            return;
        }
        displayQuantity(this.order.decrement());
    }

    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantityValue);
        quantityTextView.setText("" + quantity);
    }

    private void displayMessage(String subject, String body) {
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:")); //only email apps should handle this
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, body);

        if (email.resolveActivity(getPackageManager()) != null) {
            startActivity(email);
        }
        else {
            Toast.makeText(this, R.string.email, Toast.LENGTH_LONG).show();
        }
    }

}