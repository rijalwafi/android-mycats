package com.reg.home.kucingjoko;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceDataStore;
import android.preference.PreferenceGroup;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddCatActivity extends AppCompatActivity {
    private EditText mEdt_cat_name,mEdt_cat_gender,mEdt_cat_type,mEdt_cat_color,mEdt_cat_food;
    private Button mBtn_add_cat;
    private static final String URL_ADD_CAT="http://192.168.137.1/mycats/addCat.php";
    SessionManager sessionManager;
    String getId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cat);
        mEdt_cat_name=findViewById(R.id.edt_cat_name);
        mEdt_cat_gender=findViewById(R.id.edt_cat_gender);
        mEdt_cat_type=findViewById(R.id.edt_cat_type);
        mEdt_cat_color=findViewById(R.id.edt_cat_color);
        mEdt_cat_food=findViewById(R.id.edt_cat_food);
        mBtn_add_cat=findViewById(R.id.btn_add_cat);
        sessionManager=new SessionManager(this);
        sessionManager.getUserDetail();

        HashMap<String,String>user=sessionManager.getUserDetail();
        getId=user.get(SessionManager.ID_USER);

        mBtn_add_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cat_name=mEdt_cat_name.getText().toString().trim();
                String cat_gender=mEdt_cat_gender.getText().toString().trim();
                String cat_type=mEdt_cat_type.getText().toString().trim();
                String cat_colour=mEdt_cat_color.getText().toString().trim();
                String cat_food=mEdt_cat_food.getText().toString().trim();
                if (TextUtils.isEmpty(cat_name)){
                    mEdt_cat_name.setError("Please Enter Your Cat Name");
                    mEdt_cat_name.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(cat_gender)){
                    mEdt_cat_gender.setError("Please Enter Your Cat Gender");
                    mEdt_cat_gender.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(cat_type)){
                    mEdt_cat_type.setError("Please Enter Yoyr Cat type");
                    mEdt_cat_type.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(cat_colour)){
                    mEdt_cat_color.setError("Please Enter Your Cat Colour");
                    mEdt_cat_food.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(cat_food)){
                    mEdt_cat_food.setError("Please Enter Your Cat Food");
                    mEdt_cat_food.requestFocus();
                }else{
                    AddCat(cat_name,cat_gender,cat_type,cat_colour,cat_food);
                }
                
            }
        });
    }

    private void AddCat(final String cat_name,final String cat_gender,final String cat_type,
                        final String cat_colour,final String cat_food) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest str=new StringRequest(Request.Method.POST, URL_ADD_CAT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {
                        Intent a = new Intent(AddCatActivity.this, MessageAddCat.class);
                        startActivity(a);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(AddCatActivity.this, "Ada data yang salah" + e.toString(), Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddCatActivity.this, "Error Connection" + error.toString(), Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>params=new HashMap<>();
                params.put("cat_name",cat_name);
                params.put("id_user",getId);
                params.put("cat_gender",cat_gender);
                params.put("cat_type",cat_type);
                params.put("cat_colour",cat_colour);
                params.put("cat_food",cat_food);
                return params;
            }
        };
        RequestQueue req= Volley.newRequestQueue(this);
        req.add(str);
    }
}
