package com.reg.home.kucingjoko.ShowFoodList;

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

public class ShowFood extends AppCompatActivity {
    private static final String URL_COUNT_FOOD="https://ejobb.000webhostapp.com/countFood.php";
    private List<ShowFoodConfig> showFoodConfigList;
    private String getId;
    SessionManager mSessionManager;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food);
        mRecyclerView=findViewById(R.id.recyclerShowFood);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        showFoodConfigList=new ArrayList<>();

        mSessionManager=new SessionManager(this);
        mSessionManager.getUserDetail();
        HashMap<String,String>user=mSessionManager.getUserDetail();
        getId=user.get(SessionManager.ID_USER);

        showFoodAmount();



    }

    private void showFoodAmount() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_COUNT_FOOD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject jobj = jsonArray.getJSONObject(a);
                        showFoodConfigList.add(new ShowFoodConfig(
                                jobj.getString("cat_type"),
                                jobj.getString("cat_food"),
                                jobj.getString("amount")
                        ));
                    }
                    ShowFoodAdapter adapter = new ShowFoodAdapter(ShowFood.this, showFoodConfigList);
                    mRecyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                    Toast.makeText(ShowFood.this, "Error String " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ShowFood.this, "Check Your Connection " + error.toString(), Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>params=new HashMap<>();
                params.put("id_user",getId);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
