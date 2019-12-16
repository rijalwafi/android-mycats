package com.reg.home.kucingjoko.ListCatFile;

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

public class ListCatsActivity extends AppCompatActivity {
    private static String URL_LIST_CAT="https://ejobb.000webhostapp.com/listCat.php";
    RecyclerView recyclerView;
    List<ListCatConfig> listCatConfigs;
    SessionManager sessionManager;
    String getId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cats);
        recyclerView=findViewById(R.id.recyclerListCat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listCatConfigs=new ArrayList<>();

        sessionManager=new SessionManager(this);
        sessionManager.getUserDetail();
        HashMap<String,String>user=sessionManager.getUserDetail();
        getId=user.get(SessionManager.ID_USER);

        loadListCat();
    }

    private void loadListCat() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        StringRequest str=new StringRequest(Request.Method.POST, URL_LIST_CAT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject listCat = jsonArray.getJSONObject(i);
                        listCatConfigs.add(new ListCatConfig(
                                listCat.getInt("id_user"),
                                listCat.getString("cat_name"),
                                listCat.getString("cat_gender"),
                                listCat.getString("cat_type"),
                                listCat.getString("cat_colour"),
                                listCat.getString("cat_food")
                        ));
                    }
                    ListCatAdapter adapter = new ListCatAdapter(ListCatsActivity.this, listCatConfigs);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ListCatsActivity.this, "String Error" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListCatsActivity.this,"Connection Error"+error.toString(),Toast.LENGTH_LONG).show();

            }
        })
        {
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
