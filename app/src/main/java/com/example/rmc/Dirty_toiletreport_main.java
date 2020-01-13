package com.example.rmc;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class Dirty_toiletreport_main extends AppCompatActivity { //Waste_pickupreport_main
    EditText et_name,et_number,et_location,et_discription;
    public static Bitmap bitmap;
    Boolean imageSet = true;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dirty_toiletreport_main);
        et_name = findViewById(R.id.et_name);
        et_number = findViewById(R.id.et_number);
        et_location = findViewById(R.id.et_location);
        et_discription = findViewById(R.id.et_discription);
        preferences = getSharedPreferences("ssiprajkot", MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void getTheImage(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imageSet = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showImg(View view) {
        startActivity(new Intent(getApplicationContext(),Showimg_toiletreport.class));
    }

    public void senDataServer(View view) {

        if (et_name.getText().toString().trim().isEmpty()) {
            et_name.setError("Enter Name first");
            et_name.requestFocus();
            return;
        }
        if (et_number.getText().toString().trim().isEmpty()) {
            et_number.setError("Enter Number first");
            et_number.requestFocus();
            return;
        }
        if (et_location.getText().toString().trim().isEmpty()) {
            et_location.setError("Enter Location first");
            et_location.requestFocus();
            return;
        }
        if (et_discription.getText().toString().trim().isEmpty()) {
            et_discription.setError("Enter Discription first");
            et_discription.requestFocus();
            return;
        }
        if(imageSet)
        {
            Toast.makeText(getApplicationContext(),"Please select image", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getApplicationContext(), "Wait Please..", Toast.LENGTH_SHORT).show();
        if(!isConnected())
        {
            Toast.makeText(getApplicationContext(),"Not Connected to Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        uploadDataToServer();

    }
    public void clearForm()
    {
        et_name.setText("");
        et_number.setText("");
        et_location.setText("");
        et_discription.setText("");
        imageSet = true;
    }
    public void cancleForm(View view) {
        clearForm();
    }



    //for image upload

    public boolean isConnected()  {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void uploadDataToServer() {
        String UPLOAD_URL = "http://dirtytoilet.tonysolutions.co/insertdata.php";
        System.out.println("thisthisthis : "+UPLOAD_URL);
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            System.out.println("msgmsgmsg :"+obj.getString("message"));
                            clearForm();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println("erererer ");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name",et_name.getText().toString());
                params.put("number",et_number.getText().toString());
                params.put("location",et_location.getText().toString());
                params.put("discription",et_discription.getText().toString());
                params.put("email",preferences.getString("uemail", "sejsinh01@gmail.com"));
                return params;
            }
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }
}
