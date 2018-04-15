package recyclerapi.jyoti.com.myrecyclerapi;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import recyclerapi.jyoti.com.myrecyclerapi.adapter.MainActivityAdapter;
import recyclerapi.jyoti.com.myrecyclerapi.model.MainActivityModel;

/**
 * Created By : Jyoti on 15 April 2018 (Sunday)
 * Functionality : This class will show the recycler view with API call
 * Nevigation :
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView recycler_view;
    private RecyclerView.Adapter adapter;
    private ArrayList<MainActivityModel> arrayList = new ArrayList<MainActivityModel>();
    SwipeRefreshLayout swipeRefreshLayout;
    private String CODE_URL = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=1b6e1cc9168842d8a1fb4ac44a921765";

    // private ArrayList<FamilyListingDataProvider> arrayList = new ArrayList<FamilyListingDataProvider>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUIControls();

        setAdapter();

    }//end od onCreate()

    private void initUIControls() {
        recycler_view = findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.simpleSwipeRefreshLayout);
    }//end of initUIControls

    private void setAdapter() {

        callWebService();
        adapter = new MainActivityAdapter(this, arrayList);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(adapter);

    }//end of setAdapter

    private void callWebService() {

        StringRequest request = new StringRequest(Request.Method.GET, CODE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("MainActivity", response);

                setData(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("", "onErrorResponse: " + error.getLocalizedMessage());
                Toast.makeText(getApplicationContext(), "Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });
        //AppController.getInstance().addToRequestQueues(request, "MainActivty");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void setData(String response) {
        try {
            if (swipeRefreshLayout.isRefreshing())
                Toast.makeText(this, "Refresh Complete", Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);

            JSONObject jsonObject = new JSONObject(response);

            JSONArray jsonArray = jsonObject.getJSONArray("articles");

            for (int i = 0; i <= jsonArray.length(); i++) {

                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                MainActivityModel mainActivityModel = new MainActivityModel();

                mainActivityModel.setTitle(jsonObject1.optString("title"));
                mainActivityModel.setDesc(jsonObject1.optString("description"));
                mainActivityModel.setPublish(jsonObject1.optString("publishedAt"));
                mainActivityModel.setUrlReadMore(jsonObject1.optString("url"));
                mainActivityModel.setImgURL(jsonObject1.optString("urlToImage"));

                arrayList.add(mainActivityModel);
                adapter.notifyDataSetChanged();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}//end of MainActivity
