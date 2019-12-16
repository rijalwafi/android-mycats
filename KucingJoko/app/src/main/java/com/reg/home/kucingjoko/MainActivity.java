package com.reg.home.kucingjoko;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.reg.home.kucingjoko.CountCat.ListCountCat;
import com.reg.home.kucingjoko.ListCatFile.ListCatsActivity;
import com.reg.home.kucingjoko.ShowFoodList.ShowFood;
import com.reg.home.kucingjoko.TopCatFood.ListTopActivity;

public class MainActivity extends AppCompatActivity {
    private CardView mCvList,mCvAdd,mCvType,mCvFood,mCvTop,mLogout;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCvList=findViewById(R.id.cv_catList);
        mCvAdd=findViewById(R.id.cv_catAdd);
        mCvType=findViewById(R.id.cv_catType);
        mCvFood=findViewById(R.id.cv_catFood);
        mCvTop=findViewById(R.id.cv_catTop);
        mLogout=findViewById(R.id.cv_Logout);

        sessionManager=new SessionManager(this);
        sessionManager.checkLogin();
        mCvList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(MainActivity.this,ListCatsActivity.class);
                startActivity(a);
            }
        });
        mCvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(MainActivity.this,AddCatActivity.class);
                startActivity(a);
            }
        });
        mCvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(MainActivity.this,ListCountCat.class);
                startActivity(a);
            }
        });
        mCvFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(MainActivity.this, ShowFood.class);
                startActivity(a);
            }
        });
        mCvTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(MainActivity.this, ListTopActivity.class);
                startActivity(a);
            }
        });
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

    }

    private void back() {
        new AlertDialog.Builder(this)
                .setMessage("Apa anda yakin ingin logout?")
                .setCancelable(false).setPositiveButton("IYA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sessionManager.logout();
            }
        }).setNegativeButton("Tidak",null).show();
    }

}
