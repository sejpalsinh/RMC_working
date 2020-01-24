package com.example.rmc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class photo_chili extends AppCompatActivity {
    Bitmap bitmap;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_chili);
        tv = findViewById(R.id.result);
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
                img_2_crp(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    Bitmap  rot(Bitmap bmp_crp,int dgr) {
        Matrix matrix = new Matrix();
        matrix.postRotate(dgr);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bmp_crp, bmp_crp.getWidth(), bmp_crp.getHeight(), true);
        bmp_crp = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        return bmp_crp;
    }

    public void img_2_crp(Bitmap bit_2) {
        bit_2 = rot(bit_2,180);
        bit_2 = Bitmap.createBitmap(bit_2, 50,00,(bit_2.getWidth()-150),(bit_2.getHeight()-(bit_2.getHeight()/2)));
        bit_2 = rot(bit_2,180);
        Palette p = Palette.from(bit_2).generate();
        tv.setVisibility(View.VISIBLE);
        System.out.println("ccccccccccccccccccccccccc");
        Palette.Swatch vibrant = p.getDarkMutedSwatch(); //getDarkVibrantSwatch(); //getVibrantSwatch();
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
        if(Color.red(titleColor)>Color.green(titleColor) && Color.red(titleColor)>Color.blue(titleColor))
        {
            tv.setText("Adultered");
        }
        else
        {
            tv.setText("Not Adultered");
        }
        //tv.setText("RGB = "+Color.red(titleColor)+" "+Color.green(titleColor)+" "+Color.blue(titleColor));
    }


}
