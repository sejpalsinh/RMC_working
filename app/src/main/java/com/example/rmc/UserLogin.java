package com.example.rmc;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class UserLogin extends AppCompatActivity {

    EditText mail;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        preferences = getSharedPreferences("ssiprajkot", MODE_PRIVATE);
        editor = preferences.edit();
        mail = findViewById(R.id.et_mail);
    }
    private void uploadUserdata() {
        if (mail.getText().toString().trim().isEmpty()) {
            mail.setError("Enter Mail address first");
            mail.requestFocus();
            return;
        }
        if(!isConnected())
        {
            Toast.makeText(getApplicationContext(),"Not Connected to Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        String UPLOAD_URL = "http://tonysolutions.co/ssiprajkot_user_admin.php";
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            editor.putString("uemail",mail.getText().toString());
                            editor.commit();
                            System.out.println("msgmsgmsg :"+obj.getString("message"));
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("uemail",mail.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }
    public boolean isConnected()  {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void regist(View view) {
        uploadUserdata();
    }
}
