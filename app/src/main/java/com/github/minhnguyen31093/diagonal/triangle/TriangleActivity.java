package com.github.minhnguyen31093.diagonal.triangle;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.minhnguyen31093.diagonal.R;

/**
 * Created by Minh Nguyen on 7/9/2016.
 */
public class TriangleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triangle);

        ListAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() { return 16; }

            @Override
            public Integer getItem(int position) { return position; }

            @Override
            public long getItemId(int position) { return position; }

            @Override
            public View getView(int position, View view, ViewGroup parent) {
                if (view == null) {
                    view = getLayoutInflater().inflate(R.layout.item_triangle, parent, false);
                }

                // determine whether it's a left or a right triangle
                TriangleFrameLayout.Align align =
                        (position & 1) == 0 ? TriangleFrameLayout.Align.LEFT : TriangleFrameLayout.Align.RIGHT;

                // setup the triangle
                TriangleFrameLayout triangleFrameLayout = (TriangleFrameLayout) view.findViewById(R.id.root_triangle);
                triangleFrameLayout.setTriangleAlignment(align);
                triangleFrameLayout.setBackgroundColor(Color.argb(255, 0, (int) (Math.random() * 256), (int) (Math.random() * 256)));

                // setup the example TextView
                TextView textView = (TextView) view.findViewById(R.id.item_text);
                textView.setText(getItem(position).toString());
                textView.setGravity((position & 1) == 0 ? Gravity.LEFT : Gravity.RIGHT);

                return view;
            }
        };

        // populate the list
        LinearLayout list = (LinearLayout) findViewById(R.id.list);
        for (int i = 0; i < adapter.getCount(); ++i) {
            final int position = i;
            // generate the item View
            View item = adapter.getView(position, null, list);
            list.addView(item);
            item.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "#" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
