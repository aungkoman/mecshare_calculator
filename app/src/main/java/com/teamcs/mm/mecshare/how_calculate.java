package com.teamcs.mm.mecshare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class how_calculate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_calculate);
        TextView tv = (TextView) findViewById(R.id.how_calculate);
        tv.setText(Html.fromHtml("<p> ထည့္ဝင္ေငြ (ေငြသြင္းထားသည့္) လေပၚ မူတည္ျပီး တစ္လခ်င္းစီ ခြဲျခား တြက္ခ်က္ပါသည္။ </p>\n" +
                "\n" +
                "<p> ထပ္မံထည့္ဝင္ေငြအား ေငြသြင္းသည့္လ အတြက္မွ စတင္ျပီး အျမတ္ေငြ တြက္ခ်က္ပါသည္။ </p>\n" +
                "\n" +
                "<p> တစ္ႏွစ္စာ ၁၂ လ အနက္ ေငြထည့္ဝင္သည့္ လမွ ႏွစ္မကုန္မီက်န္သည့္ လအေရအတြကို အျမတ္ေငြ တြက္ခ်က္ပါသည္။ </p>\n" +
                "\n" +
                "<p> တစ္လအတြင္း မည္သည့္ရက္တြင္ ေငြထည့္ဝင္ေစကာမူ ၄င္းလအတြက္မွ စတင္ျပီး အျမတ္ေငြ တြက္ခ်က္ပါသည္ </p>\n" +
                "\n"));
    }
}
