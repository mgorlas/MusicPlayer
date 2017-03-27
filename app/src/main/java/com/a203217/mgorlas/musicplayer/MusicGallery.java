package com.a203217.mgorlas.musicplayer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MusicGallery extends AppCompatActivity {

    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private int visibleItemCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_gallery);

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item, getData());
        gridView.setAdapter(gridAdapter);

        int position = getIntent().getIntExtra("position", 0);
        gridView.smoothScrollToPosition(position);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                    Intent intent = new Intent(MusicGallery.this, Player.class);
                    intent.putExtra("id", item.getId());
                    intent.putExtra("musicId", item.getMusicId());
                    intent.putExtra("position", position);
                    startActivity(intent);
                } catch(Exception exc)
                {
                    System.out.print(exc.getMessage());
                }

            }
        });

        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE && getVisibleItemCount() >8 ) {
                    View v = view.getChildAt(0);
                    int top = -v.getTop();
                    int height = v.getHeight();
                    if (top < height / 2) {
                        gridView.smoothScrollToPosition(view.getFirstVisiblePosition());
                    } else {
                        gridView.scrollBy(0, height - top);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                setVisibleItemCount(visibleItemCount);
            }
        });
    }

    public void setVisibleItemCount(int visibleItemCount){
        this.visibleItemCount = visibleItemCount;
    }

    public int getVisibleItemCount() {
        return visibleItemCount;
    }

    // Prepare some dummy data for gridview
    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        String[] imgs = getResources().getStringArray(R.array.images);
        String[] music = getResources().getStringArray(R.array.music);


        for (int i = 0; i < imgs.length; i++) {
            int id = getResources().getIdentifier(imgs[i], "drawable", getPackageName());
            int musicId = getResources().getIdentifier(music[i], "raw", getPackageName());

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
            Bitmap finalBitmap = ThumbnailUtils.extractThumbnail(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2);
            imageItems.add(new ImageItem(finalBitmap, id, musicId));
        }
        return imageItems;
    }
}
