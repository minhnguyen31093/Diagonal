package com.github.minhnguyen31093.diagonal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Minh Nguyen on 7/9/2016.
 */
public class DiagonalShapeActivity extends Activity {

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(DiagonalShapeActivity.this, (String) view.getTag(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagonal_shape);

        RelativeLayout backgroundLayout = (RelativeLayout) findViewById(R.id.backgroundLayout);
        DiagonalRelativeLayout diagonalRelativeLayout = (DiagonalRelativeLayout) findViewById(R.id.diagonalRelativeLayout);
        DiagonalRelativeLayout diagonalRelativeLayout1 = (DiagonalRelativeLayout) findViewById(R.id.diagonalRelativeLayout1);
        DiagonalRelativeLayout diagonalRelativeLayout2 = (DiagonalRelativeLayout) findViewById(R.id.diagonalRelativeLayout2);
//        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
//        DiagonalView diagonalView = (DiagonalView) findViewById(R.id.diagonalView);
        CircleImageView circleImageView = (CircleImageView) findViewById(R.id.circleImageView);

//        frameLayout.setTag("FrameLayout");
//        diagonalView.setTag("DiagonalView");
        circleImageView.setTag("CircleImageView");
        backgroundLayout.setTag("BackgroundLayout");
        diagonalRelativeLayout.setTag("DiagonalRelativeLayout");
        diagonalRelativeLayout1.setTag("DiagonalRelativeLayout1");
        diagonalRelativeLayout2.setTag("DiagonalRelativeLayout2");

//        frameLayout.setOnClickListener(onClickListener);
//        diagonalView.setOnClickListener(onClickListener);
        circleImageView.setOnClickListener(onClickListener);
        backgroundLayout.setOnClickListener(onClickListener);
        diagonalRelativeLayout.setOnClickListener(onClickListener);
        diagonalRelativeLayout1.setOnClickListener(onClickListener);
        diagonalRelativeLayout2.setOnClickListener(onClickListener);
    }
}
