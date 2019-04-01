package com.example.status_bar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.XmlRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mengk on 2019/3/11
 * Describe :
 */
public class MyAdapter extends RecyclerView.Adapter {
    private Context context;

    public MyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_layout_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        int[] location = new int[2];
        viewHolder.myView2.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
//        Log.e("===z", "item " + position + "的x坐标 = " + x + "and y坐标 = " + y);
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        MyView2 myView2;

        public MyViewHolder(View itemView) {
            super(itemView);
            myView2 = itemView.findViewById(R.id.myview);
        }
    }
}
