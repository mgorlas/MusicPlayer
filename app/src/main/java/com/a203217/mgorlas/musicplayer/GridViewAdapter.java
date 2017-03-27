package com.a203217.mgorlas.musicplayer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.Image;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by marze on 09.03.2017.
 */

public class GridViewAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        ImageItem item = (ImageItem) data.get(position);
        holder.image.setImageBitmap(item.getImage());

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight() - getStatusBarHeight() - getTitleBarHeight();
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width/2,height/4);
        holder.image.setLayoutParams(parms);


        return row;
    }

    public int getStatusBarHeight() {
        Rect r = new Rect();
        Window w = ((Activity) context).getWindow();
        w.getDecorView().getWindowVisibleDisplayFrame(r);
        return r.top;
    }
    public int getTitleBarHeight() {
        int viewTop = ((Activity) context).getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        return viewTop;
    }

    static class ViewHolder {
        ImageView image;

    }



}
