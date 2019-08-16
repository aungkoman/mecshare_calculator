package com.teamcs.mm.mecshare;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // declare views
    EditText amount,month;
    Button calculate,clear;
    TextView total_profit_textView;
    List<Data> DataList=new ArrayList<Data>();
    // declare data
    // amount, month, profit
    // list of data_set
    // total profit
    static int total_profit_int = 0;
    private class Data {
        private int amount;
        private int month;
        private int profit;
        // constructor
        void Data(int amount, int month){
            this.amount = amount;
            this.month = month;
            calculate_profit();
        }
        void Data(int amount, int month, int profit){
            this.amount = amount;
            this.month = month;
            this.profit = profit;
        }
        private void calculate_profit(){
            int profit = this.amount * this.month;
            this.profit = profit;
        }
        // getter
        // setter
    }

    /* this is stackvoerflow example */
    /*
    List<MarkerCustom> myList=new ArrayList<MarkerCustom>();
    MarkerCustom entry1=new MarkerCustom(myInt, myString, myBitmap);
    MarkerCustom entry2=new MarkerCustom(myInt, myString, myBitmap);

    myList.add(entry1);
    myList.add(entry2);
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.hide();

        /*
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        */

        // initialize view
        amount = (EditText) findViewById(R.id.amount);
        month = (EditText) findViewById(R.id.month);
        calculate = (Button) findViewById(R.id.calculate);
        calculate.setEnabled(false);
        clear = (Button) findViewById(R.id.clear);
        clear.setEnabled(true);
        total_profit_textView = (TextView) findViewById(R.id.total_profit);

        // attach onClick listner
        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //show_text("claculate is clicked");
                // validate two input
                try{
                    if(amount.getText().toString().isEmpty()){
                        Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
                        amount.startAnimation(shake);
                        amount.requestFocus();
                        show_snack(getResources().getString(R.string.enter_amount));
                        return;
                    }
                    int amount_int = Integer.parseInt(amount.getText().toString());
                    int month_int = Integer.parseInt(month.getText().toString());
                    if(month_int < 0 || month_int > 12){
                        show_snack(getResources().getString(R.string.enter_month));
                        return;
                    }
                    int profit_int = (amount_int * month_int * 30 ) / (12 * 100);
                    total_profit_int = profit_int;
                    show_snack("စုစုေပါင္း အျမတ္ေငြ :  "+total_profit_int+"  က်ပ္");
                    total_profit_textView.setText(Html.fromHtml("စုစုေပါင္း အျမတ္ေငြ : <font color='#EE0000'>"+total_profit_int+"</font> က်ပ္"));
                    // hide soft keyboard
                    /*
                    try {
                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    */
                }catch(Exception e){}

            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //show_text("clear is clicked");
                amount.setText("");
                month.setText("");
                amount.requestFocus();
                show_snack(getResources().getString(R.string.cleared));

            }
        });

        amount.addTextChangedListener(new TextValidator(amount) {
            @Override public void validate(TextView textView, String text) {
                // Validation code here
            }
        });

        month.addTextChangedListener(new TextValidator(month) {
            @Override public void validate(TextView textView, String text) {
                // Validation code here
                try{
                    int month_int = Integer.parseInt(text);
                    if(month_int <0 || month_int > 12){
                        month.setTextColor(Color.BLUE);
                        month.setText("");
                        Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
                        month.startAnimation(shake);
                        show_snack(getResources().getString(R.string.enter_month));
                        // disable calculate
                        calculate.setEnabled(false);
                    }
                    else{
                        // it is valid month
                        // show button activ
                        calculate.setEnabled(true);
                    }
                } catch (Exception e){
                    //show_text(e.getMessage());
                }


            }
        });
    }

    // for text input validation , we use textWatcher
    // stackoverflow again
    public abstract class TextValidator implements TextWatcher {
        private final TextView textView;

        public TextValidator(TextView textView) {
            this.textView = textView;
        }

        public abstract void validate(TextView textView, String text);

        @Override
        final public void afterTextChanged(Editable s) {
            String text = textView.getText().toString();
            validate(textView, text);
        }

        @Override
        final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */ }

        @Override
        final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care */ }
    }
    /* text validation usage */
    /*
       editText.addTextChangedListener(new TextValidator(editText) {
            @Override public void validate(TextView textView, String text) {
                // Validation code here
            }
        });
     */
    // show text
    public void show_text(String str){
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    }
    public void show_snack(String str){
        Snackbar.make(findViewById(android.R.id.content), str, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private boolean _doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {

        if (_doubleBackToExitPressedOnce)
        {
            super.onBackPressed();
            return;
        }
        this._doubleBackToExitPressedOnce = true;
        //Toast.makeText(this, getResources().getString(R.string.exit_greeting), Toast.LENGTH_SHORT).show();
        Snackbar.make(getWindow().getDecorView(), getResources().getString(R.string.exit_greeting), Snackbar.LENGTH_LONG).show();

        //WV.loadUrl("javascript:android_hello()");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                _doubleBackToExitPressedOnce = false;
            }
        }, 2000);
        /*
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            haha();
            return true;
        }
        if (id == R.id.action_how_calculate) {
            Intent intent = new Intent(this, how_calculate.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void haha(){
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.dialog_title))
                .setMessage(getResources().getString(R.string.dialog_message))
                .setPositiveButton(getResources().getString(R.string.dialog_ok_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("MainActivity", "Sending atomic bombs to Jupiter");
                        }
                })
                /*
                .setNegativeButton(getResources().getString(R.string.dialog_cancel_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("MainActivity", "Aborting mission...");
                        Toast.makeText(getApplicationContext(),"cancel clicked",Toast.LENGTH_SHORT).show();
                    }
                })
                */
                .show();
    }
}
