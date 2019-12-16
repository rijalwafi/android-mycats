package com.reg.home.kucingjoko;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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


public class RegisterActivity extends AppCompatActivity {
    private EditText edt_username,edt_password1,edt_password2,edt_address;
    private Button btn_register;
    private static  String URL_REGISTER = "https://ejobb.000webhostapp.com/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edt_username=findViewById(R.id.username_register);
        edt_password1=findViewById(R.id.passwordRegister1);
        edt_password2=findViewById(R.id.passwordRegister2);
        edt_address=findViewById(R.id.edt_address_register);
        btn_register=findViewById(R.id.btn_register_akun);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String username=edt_username.getText().toString().trim();
                String password=edt_password1.getText().toString().trim();
                String mEdt_password2=edt_password2.getText().toString().trim();
                 String address=edt_address.getText().toString().trim();
                if (TextUtils.isEmpty(username)){
                    edt_username.setError("Please fill your Username");
                    edt_username.requestFocus();
                    return;
                }
                if  (TextUtils.isEmpty(password)){
                    edt_password1.setError("Please fill your Password");
                    edt_password1.requestFocus();

                    return;
                }
                if (TextUtils.isEmpty(mEdt_password2)){
                    edt_password2.setError("Please fill your Password");
                    edt_password2.requestFocus();
                    return;
                }
                if (!mEdt_password2.equals(password)){
                    edt_password2.setError("The password you entered is different");
                    edt_password2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(address)){
                    edt_address.setError("please fill your adress");
                }else {
                    Register(username,password,address);
                }
            }
        });
    }

    private void Register(final String username,final String password,final String address) {

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        StringRequest str =new StringRequest(Request.Method.POST,URL_REGISTER,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jobj = new JSONObject(response);
                    String success = jobj.getString("success");
                    if (success.equals("1")) {
                        Toast.makeText(RegisterActivity.this, "Register Berhasil", Toast.LENGTH_LONG).show();
                        Intent a = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(a);
                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "Register Failed" + e.toString(), Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this,"Check Your Conection"+error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>user =new HashMap<>();
                user.put("username",username);
                user.put("password",password);
                user.put("address",address);
                return user;
            }
        };
        RequestQueue mRequestQueue= Volley.newRequestQueue(this);
        mRequestQueue.add(str);
    }
}
