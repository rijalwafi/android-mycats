package com.reg.home.kucingjoko.TopCatFood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.reg.home.kucingjoko.R;
import com.reg.home.kucingjoko.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTopActivity extends AppCompatActivity {
    private static final String URL_TOP="https://ejobb.000webhostapp.com/topFood.php";
    private List<ListTopConfig> listTopConfigList;
    RecyclerView recyclerView;
    SessionManager sessionManager;
    String getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_top);
        recyclerView=findViewById(R.id.RecyclerTop);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sessionManager=new SessionManager(this);
        sessionManager.getUserDetail();
        HashMap<String,String> user=sessionManager.getUserDetail();
        getId=user.get(SessionManager.ID_USER);
        listTopConfigList=new ArrayList<>();
        loadTopCat();
    }

    private void loadTopCat() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("loading..");
        progressDialog.show();
        StringRequest str=new StringRequest(Request.Method.POST, URL_TOP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        listTopConfigList.add(new ListTopConfig(
                                jsonObject.getString("cat_type"),
                                jsonObject.getString("amount")
                        ));
                    }
                    ListTopAdapter adapter = new ListTopAdapter(ListTopActivity.this, listTopConfigList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ListTopActivity.this, "String Error " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListTopActivity.this,"String Error "+error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>params=new HashMap<>();
                params.put("id_user",getId);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(str);
    }
}
