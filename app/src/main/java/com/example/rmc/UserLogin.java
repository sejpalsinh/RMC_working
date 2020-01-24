package com.example.rmc;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
    EditText mail,u_name,u_number;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private ProgressDialog progress = null;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        preferences = getSharedPreferences("ssiprajkot", MODE_PRIVATE);
        editor = preferences.edit();
        if(!preferences.getString("uemail", " ").equals(" "))
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        mail = findViewById(R.id.et_mail);
        u_name = findViewById(R.id.et_name);
        u_number = findViewById(R.id.et_number);

    }
    public void showLoadingDialog() {

        if (progress == null) {
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Please Wait");
        }
        progress.show();
    }
    public void dismissLoadingDialog() {

        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }

    }

    private void uploadUserdata() {
        if (mail.getText().toString().trim().isEmpty()) {
            mail.setError("Enter Mail address first");
            mail.requestFocus();
            return;
        }
        if (u_name.getText().toString().trim().isEmpty()) {
            u_name.setError("Enter Your name Please ");
            mail.requestFocus();
            return;
        }
        if (u_number.getText().toString().trim().isEmpty()) {
            u_number.setError("Enter Your Name Please");
            u_number.requestFocus();
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
                            dismissLoadingDialog();
                            JSONObject obj = new JSONObject(new String(response.data));
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            editor.putString("uemail",mail.getText().toString());
                            editor.putString("uname",u_name.getText().toString());
                            editor.putString("unumber",u_number.getText().toString());
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
                        dismissLoadingDialog();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println("ErErErErEr : "+error);
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
        showLoadingDialog();
        uploadUserdata();
    }
}
