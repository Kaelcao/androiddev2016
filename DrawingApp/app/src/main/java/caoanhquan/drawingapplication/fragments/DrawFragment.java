package caoanhquan.drawingapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import caoanhquan.drawingapplication.R;
import caoanhquan.drawingapplication.customviews.DrawingView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrawFragment extends Fragment implements View.OnClickListener {

    private DrawingView mDrawingView;
    private ImageButton mCurrPaint, mLibrary, mDrawButton, mEraseButton, mNewButton, mSaveButton;
    private float smallBrush, mediumBrush, largeBrush;

    public DrawFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_draw, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEraseButton = (ImageButton) view.findViewById(R.id.erase_btn);
        mEraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mDrawingView = (DrawingView) view.findViewById(R.id.drawing);

        LinearLayout paintLayout = (LinearLayout) view.findViewById(R.id.paint_colors);
        mCurrPaint = (ImageButton) paintLayout.getChildAt(0);
        for (int i = 0; i < paintLayout.getChildCount(); i++) {
            ImageButton imageBtn = (ImageButton) paintLayout.getChildAt(i);
            imageBtn.setOnClickListener(this);
        }
        LinearLayout bottomColorLayout = (LinearLayout) view.findViewById(R.id.bottom_color);
        for (int i = 0; i < bottomColorLayout.getChildCount(); i++) {
            ImageButton imageBtn = (ImageButton) bottomColorLayout.getChildAt(i);
            imageBtn.setOnClickListener(this);
        }
        mCurrPaint.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.paint_pressed));

        mLibrary = (ImageButton) view.findViewById(R.id.library);
        mLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);

        mDrawingView.setBrushSize(mediumBrush);

        mDrawButton = (ImageButton) view.findViewById(R.id.draw_btn);
        mDrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }


        });

        mNewButton = (ImageButton) view.findViewById(R.id.new_btn);
        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mSaveButton = (ImageButton) view.findViewById(R.id.save_btn);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void paintClicked(View view) {
        if (view != mCurrPaint) {
            ImageButton imgView = (ImageButton) view;
            String color = view.getTag().toString();
            mDrawingView.setColor(color);
            imgView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.paint_pressed));
            mCurrPaint.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.paint));
            mCurrPaint = (ImageButton) view;
        }
        mDrawingView.setErase(false);
        mDrawingView.setBrushSize(mDrawingView.getLastBrushSize());

    }

    @Override
    public void onClick(View view) {
        paintClicked(view);
    }
}
