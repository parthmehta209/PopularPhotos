package pcmdroid.instagramclient;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PhotosActivity extends AppCompatActivity {

    private static final String TAG = PhotosActivity.class.getSimpleName();
    private static final String URL = "https://api.instagram.com/v1/media/popular?client_id=";
    private static final String CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";
    private ArrayList<InstagramPhoto> photoList;
    private InstagramPhotoAdapter photoAdapter;
    private ListView lvPhotos;
    private SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        photoList = new ArrayList<InstagramPhoto>();
        photoAdapter = new InstagramPhotoAdapter(this,photoList);
        lvPhotos = (ListView)findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(photoAdapter);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchPopularPhotos();
            }
        });
        fetchPopularPhotos();

    }

    private void fetchPopularPhotos() {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(this, URL + CLIENT_ID, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                photoList.removeAll(photoList);
                JSONArray photos = null;
                try {
                    photos = response.getJSONArray("data");
                    Log.d(TAG, "Received " + photos.length() + " photos in response");
                } catch (JSONException e) {
                    Log.d(TAG, "No photos array in reponse " + e);
                }

                for (int i = 0; i < photos.length(); i++) {
                    try {
                        JSONObject photoJson = photos.getJSONObject(i);
                        InstagramPhoto photo = new InstagramPhoto(
                                getUsername(photoJson),
                                getCaption(photoJson),
                                photoJson.getJSONObject("images").getJSONObject("standard_resolution").getString("url"),
                                photoJson.getJSONObject("user").getString("profile_picture"),
                                photoJson.getJSONObject("images").getJSONObject("standard_resolution").getInt("height"),
                                photoJson.getJSONObject("images").getJSONObject("standard_resolution").getInt("width"),
                                getLikeCount(photoJson)
                        );
                        photoList.add(photo);
                    } catch (JSONException e) {
                        Log.d(TAG, "" + e);
                        continue;
                    }
                }

                photoAdapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(TAG, "Error in fetching photos " + responseString);
            }
        });
    }

    private static String getUsername(JSONObject photoJson) {
        try {
            return photoJson.getJSONObject("user").getString("username");
        } catch (JSONException e) {
            Log.e(TAG,"" + e);
            return "";
        }
    }

    private static String getCaption(JSONObject photoJson) {
        try {
            return photoJson.getJSONObject("caption").getString("text");
        } catch (JSONException e) {
            Log.e(TAG,"" + e);
            return "";
        }
    }

    private static int getLikeCount(JSONObject photoJson) {
        try {
            return photoJson.getJSONObject("likes").getInt("count");
        } catch (JSONException e) {
            Log.e(TAG,"" + e);
            return 0;
        }
    }

}
