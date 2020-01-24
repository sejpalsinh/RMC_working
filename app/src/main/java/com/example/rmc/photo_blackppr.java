package com.example.rmc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class photo_blackppr extends AppCompatActivity {
    public static Bitmap bitmap;
    TextView tv;
    ImageView imgv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_blackppr);
        tv = findViewById(R.id.result);
        imgv = findViewById(R.id.img);
    }

    public void getImage(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 100);
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                img_5_crp(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void img_5_crp(Bitmap bit_5) {
        bit_5 = Bitmap.createBitmap(bit_5, 50,00,(bit_5.getWidth()-150),(bit_5.getHeight()-(bit_5.getHeight()/2)));
        Palette p = Palette.from(bit_5).generate();
        System.out.println("dddddddddddddddddddddddddd");
        tv.setVisibility(View.VISIBLE);
        Palette.Swatch vibrant = p.getDarkVibrantSwatch(); //getVibrantSwatch(); // //getDarkMutedSwatch();
        int titleColor=00;
        if(vibrant != null){
            titleColor  = vibrant.getRgb();

        }
        else
        {
            tv.setText("Not Adultered");
            System.out.println("111nononononnonon");
            return;
        }
        tv.setTextColor(titleColor);
        String hexColor = String.format("#%06X", (0xFFFFFF & titleColor));
        if(Color.blue(titleColor)<Color.red(titleColor) && Color.blue(titleColor)<Color.green(titleColor))
        {
            tv.setText("Adultered");
            System.out.println("yyyyyyyyyyy");
        }
        else
        {
            tv.setText("Not Adultered");
            System.out.println("nononononnonon");
        }
    }


    Bitmap  rot(Bitmap bmp_crp,int dgr) {
        Matrix matrix = new Matrix();
        matrix.postRotate(dgr);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bmp_crp, bmp_crp.getWidth(), bmp_crp.getHeight(), true);
        bmp_crp = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        return bmp_crp;
    }



}
