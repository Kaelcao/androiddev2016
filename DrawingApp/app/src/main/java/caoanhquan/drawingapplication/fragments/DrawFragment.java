package caoanhquan.drawingapplication.fragments;


import android.app.Dialog;
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
                final Dialog brushDialog = new Dialog(getContext());
                brushDialog.setTitle("Eraser size:");
                brushDialog.setContentView(R.layout.brush_chooser);
                ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);
                smallBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDrawingView.setErase(true);
                        mDrawingView.setBrushSize(smallBrush);
                        brushDialog.dismiss();
                    }
                });
                ImageButton mediumBtn = (ImageButton) brushDialog.findViewById(R.id.medium_brush);
                mediumBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDrawingView.setErase(true);
                        mDrawingView.setBrushSize(mediumBrush);
                        brushDialog.dismiss();
                    }
                });
                ImageButton largeBtn = (ImageButton) brushDialog.findViewById(R.id.large_brush);
                largeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDrawingView.setErase(true);
                        mDrawingView.setBrushSize(largeBrush);
                        brushDialog.dismiss();
                    }
                });
                brushDialog.show();
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
                final Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);

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
                final Dialog brushDialog = new Dialog(getContext());
                brushDialog.setTitle("Brush size:");
                brushDialog.setContentView(R.layout.brush_chooser);
                ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);
                smallBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDrawingView.setBrushSize(smallBrush);
                        mDrawingView.setLastBrushSize(smallBrush);
                        mDrawingView.setErase(false);
                        brushDialog.dismiss();
                    }
                });
                ImageButton mediumBtn = (ImageButton) brushDialog.findViewById(R.id.medium_brush);
                mediumBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDrawingView.setBrushSize(mediumBrush);
                        mDrawingView.setLastBrushSize(mediumBrush);
                        mDrawingView.setErase(false);
                        brushDialog.dismiss();
                    }
                });

                ImageButton largeBtn = (ImageButton) brushDialog.findViewById(R.id.large_brush);
                largeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDrawingView.setBrushSize(largeBrush);
                        mDrawingView.setLastBrushSize(largeBrush);
                        mDrawingView.setErase(false);
                        brushDialog.dismiss();
                    }
                });
                brushDialog.show();
            }


        });

        mNewButton = (ImageButton) view.findViewById(R.id.new_btn);
        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	AlertDialog.Builder newDialog = new AlertDialog.Builder(getContext());
                newDialog.setTitle("New drawing");
                newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
                newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mDrawingView.startNew();
                        dialog.dismiss();
                    }
                });
                newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                newDialog.show();
            }
        });

        mSaveButton = (ImageButton) view.findViewById(R.id.save_btn);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	   AlertDialog.Builder saveDialog = new AlertDialog.Builder(getContext());
                saveDialog.setTitle("Save drawing");
                saveDialog.setMessage("Save drawing to device Gallery?");
                saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        //save
//                        mDrawingView.setDrawingCacheEnabled(true);
//                        String imgSaved = MediaStore.Images.Media.insertImage(
//                                getActivity().getContentResolver(), mDrawingView.getDrawingCache(),
//                                UUID.randomUUID().toString() + ".png", "drawing");
//                        if (imgSaved != null) {
//                            Toast savedToast = Toast.makeText(getContext(),
//                                    "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
//                            savedToast.show();
//                        } else {
//                            Toast unsavedToast = Toast.makeText(getContext(),
//                                    "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
//                            unsavedToast.show();
//                        }
//                        mDrawingView.destroyDrawingCache();
                        takeScreenshot();
                    }
                });
                saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                saveDialog.show();
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

   private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
//            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + UUID.randomUUID().toString() + ".jpg";
            String mPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/" + UUID.randomUUID().toString() + ".jpg";

            // create bitmap screen capture
            mDrawingView.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(mDrawingView.getDrawingCache());
            mDrawingView.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_LONG).show();
            openScreenshot(imageFile);
            mDrawingView.destroyDrawingCache();
            galleryAddPic(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }

    private void galleryAddPic(File f) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }


    @Override
    public void onClick(View view) {
        paintClicked(view);
    }
}
