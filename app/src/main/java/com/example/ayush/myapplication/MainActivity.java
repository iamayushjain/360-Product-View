package com.example.ayush.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RelativeLayout parentLayout;
    ImageView imageView;
    private float x1, x2;
    static final int MIN_DISTANCE = 150;
    static final int SINGLE_MIN_DISTANCE = 50;
    static final int ROTATE_MIN_DISTANCE = 30;

    Integer[] images = {
            R.drawable.c1,
            R.drawable.c2,
            R.drawable.c3,
            R.drawable.c4,
            R.drawable.c5,
            R.drawable.c6,
            R.drawable.c7,
            R.drawable.c8,
            R.drawable.c9
    };
    int currentImageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        currentImageId = images.length / 2 - 1;
        imageView.setImageResource(images[currentImageId]);

    }

    public void init() {
        parentLayout = (RelativeLayout) findViewById(R.id.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);

        parentLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();

                        break;
                    case MotionEvent.ACTION_MOVE:
                        x2 = event.getX();
                        float deltaX = x2 - x1;
                        //Log.d("Values of Delta", "deltaX=" + deltaX);
                        if (deltaX > 0 && Math.abs(deltaX) > ROTATE_MIN_DISTANCE) {
//                            currentImageId = currentImageId - (int) (Math.abs(deltaX) / ROTATE_MIN_DISTANCE);
                            x1 = x2;
                            currentImageId--;
                            changeImage();
                        } else if (deltaX < 0 && Math.abs(deltaX) > ROTATE_MIN_DISTANCE) {
                            //                          currentImageId = currentImageId + (int) (Math.abs(deltaX) / ROTATE_MIN_DISTANCE);
                            x1 = x2;
                            currentImageId++;
                            changeImage();
                        }

                        break;
                }
                return true;
            }
        });
    }

    public void changeImage() {
        if (currentImageId < 0) {
            currentImageId = images.length - 1;

        } else if (currentImageId >= images.length) {
            currentImageId = 0;
        }
        imageView.setImageResource(images[currentImageId]);
        Log.d("Image Change", "Current value ID" + currentImageId);

    }
    /*void code()
    {
        if (deltaX > 0 && Math.abs(deltaX) > MIN_DISTANCE) {
            //Toast.makeText(MainActivity.this, "left2right swipe", Toast.LENGTH_SHORT).show();
            Log.d("Swipe Detected", "Current value ID" + currentImageId);
            currentImageId--;
            if (currentImageId < 0) {
                currentImageId = images.length - 1;

            } else if (currentImageId >= images.length) {
                currentImageId = 0;
            }
            imageView.setImageResource(images[currentImageId]);
            Log.d("Image Change", "Current value ID" + currentImageId);
        }
        else if (deltaX < 0 && Math.abs(deltaX) > MIN_DISTANCE) {
            // Toast.makeText(MainActivity.this, "left2right swipe", Toast.LENGTH_SHORT).show();
            Log.d("Swipe Detected", "Current value ID" + currentImageId);
            currentImageId++;
            if (currentImageId < 0) {
                currentImageId = images.length - 1;

            } else if (currentImageId >= images.length) {
                currentImageId = 0;
            }
            imageView.setImageResource(images[currentImageId]);
            Log.d("Image Change", "Current value ID" + currentImageId);
        }

    }
*/
}
