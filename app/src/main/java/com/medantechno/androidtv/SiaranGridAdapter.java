package com.medantechno.androidtv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by dinaskominfokab.pakpakbharat on 22/08/18.
 */

public class SiaranGridAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelSiaran> modelSiaranList;

    public SiaranGridAdapter(Activity activity, List<ModelSiaran> modelSiaranList)
    {
        this.activity = activity;
        this.modelSiaranList = modelSiaranList;
    }



    @Override
    public int getCount() {
        return modelSiaranList.size();
    }

    @Override
    public Object getItem(int location) {
        return modelSiaranList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) convertView = inflater.inflate(R.layout.adapter_grid_siaran, null);


        //ImageView logo = (ImageView) convertView.findViewById(R.id.logo);
        TextView nama  = (TextView) convertView.findViewById(R.id.nama);

        final ModelSiaran mSiaran = modelSiaranList.get(position);

        Context context = parent.getContext();

        /*
        try {
            Picasso.with(context).load(mSiaran.getLogo()).placeholder(R.drawable.noimage).into(logo);
        }catch (Exception e)
        {
            Log.d("load gambar",mSiaran.getLogo()+"----"+e.toString());
        }
        */

        nama.setText(mSiaran.getNama());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(mSiaran.getLink());

                Intent intent = new Intent(view.getContext(), MainActivity.class);
                intent.putExtra("link",mSiaran.getLink());
                view.getContext().startActivity(intent);
            }
        });
        return convertView;

    }
}
