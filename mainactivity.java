package com.example.madhuri.myjavaapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    boolean wc;
    boolean hasChocolate,hasWhippedCream;
    // int pricePerCup = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //int quantity;
        // quantity = 5;
        // display(quantity);
        EditText Text =(EditText) findViewById(R.id.name_description_view);
        String name= Text.getText().toString();
        Log.v("MainActivity", "User Name " + name);
        CheckBox WhippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = WhippedCreamCheckBox.isChecked();
        Log.v("MainActivity", "has Whipped Cream " + hasWhippedCream);
        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = ChocolateCheckBox.isChecked();
        Log.v("MainActivity", "has Chocolate " + hasChocolate);
        int Price = calculatePrice(hasChocolate,hasWhippedCream);
        String priceMessage = createOrderSummary(name,Price,hasWhippedCream,hasChocolate);

        Intent intent= new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private String createOrderSummary(String name,int Price,boolean wc, boolean cc) {
        String msg = "Name :" + name + "\nAdd whipped cream ?" + wc + "\nAdd Chocolate ?" + cc + "\nQuantity : " + quantity + "\nTotal : Rs " + Price + "\nThank you !";
        return msg;
    }

    public void increment(View view) {
        if(quantity>=0  && quantity<100){
            quantity = quantity + 1;
            if(quantity==100)
                Toast.makeText(this, "You can't have more than 100 coffees!!!", Toast.LENGTH_SHORT).show();
        }

        displayQuantity(quantity);
        //displayPrice(quantity * 5);
    }

    public void decrement(View view) {
        if(quantity>0 && quantity<=100){
            quantity = quantity - 1;
            if(quantity==1)
                Toast.makeText(this, "You can't have less than 1 coffee!!!", Toast.LENGTH_SHORT).show();
        }
        displayQuantity(quantity);
        // displayPrice(quantity * 5);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }

    /**
     * Calculates the price of the order.
     *
     * param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(boolean hasChocolate,boolean hasWhippedCream) {
        int price;
        if(hasChocolate == true && hasWhippedCream == true)
            price= quantity *8;
        else if(hasChocolate == true)
            price= quantity *7;
        else if(hasWhippedCream == true)
            price= quantity *8;
        else
            price = quantity * 5;
        return price;
    }

    /**
     * This method displays the given price on the screen.
     */

}
