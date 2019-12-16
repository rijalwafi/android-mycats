package com.reg.home.kucingjoko;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_user,edt_password;
    private Button btn_login,btn_register;
    private static String  URL_LOGIN="https://ejobb.000webhostapp.com/login.php";
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        sessionManager=new SessionManager(this);

        edt_user=findViewById(R.id.edt_username_login);
        edt_password=findViewById(R.id.edt_password_login);
        btn_login=findViewById(R.id.btn_login);
        btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regis=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(regis);

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edt_user.getText().toString().trim();
                String password=edt_password.getText().toString().trim();
                if (TextUtils.isEmpty(username)){
                    edt_user.setError("Please Enter your Username");
                    edt_user.requestFocus();
                   return; 
                }
                if (TextUtils.isEmpty(password)){
                    edt_password.setError("Please Enter Your password");
                    edt_password.requestFocus();
                }else {
                    Login(username,password);
                }
            }
        });


    }

    private void Login(final String username, final String password) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        StringRequest str =new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jarr = jsonObject.getJSONArray("login");
                    if (success.equals("1")) {
                        for (int i = 0; i < jarr.length(); i++) {
                            JSONObject object = jarr.getJSONObject(i);
                            String id_user = object.getString("id_user");
                            String username = object.getString("username");
                            String address = object.getString("address");

                            sessionManager.createSession(id_user, username, address);

                            Intent a = new Intent(LoginActivity.this, MainActivity.class);
                            a.putExtra("username", username);
                            a.putExtra("address", address);
                            startActivity(a);
                            finish();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Login Error " + e.toString(), Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this,"Check Your Connection"+error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>params=new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue req= Volley.newRequestQueue(this);
        req.add(str);
    }
}
