package com.reg.home.kucingjoko.CountCat;

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

public class ListCountCat extends AppCompatActivity {
    private List<CountCatConfig> countCatConfigs;
    private static final String URL_COUNT="https://ejobb.000webhostapp.com/countCat.php";
    SessionManager sessionManager;
    RecyclerView recyclerView;
    String getId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_count_cat);
        recyclerView=findViewById(R.id.RecylerCount);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        countCatConfigs=new ArrayList<>();

        sessionManager =new SessionManager(this);
        sessionManager.getUserDetail();
        HashMap<String,String>user=sessionManager.getUserDetail();
        getId=user.get(SessionManager.ID_USER);

        LoadCountCat();
    }

    private void LoadCountCat() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("loading..");
        progressDialog.show();

        StringRequest str=new StringRequest(Request.Method.POST, URL_COUNT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject countList = jsonArray.getJSONObject(i);
                        countCatConfigs.add(new CountCatConfig(
                                countList.getString("cat_type"),
                                countList.getString("cat_gender"),
                                countList.getString("number_of_cat")
                        ));
                    }

                    CountCatAdapter adapter = new CountCatAdapter(ListCountCat.this, countCatConfigs);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ListCountCat.this, "Error String " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListCountCat.this, "Check Connection" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>user=new HashMap<>();
                user.put("id_user",getId);
                return user;
            }
        };
        Volley.newRequestQueue(this).add(str);
    }
}
