package com.wafaqimohtasib.mohstasibapp.ui.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.wafaqimohtasib.mohstasibapp.R;
import com.wafaqimohtasib.mohstasibapp.utils.Helpers;

public class DashboardAdapter extends BaseAdapter {

    private Context context;
    private String titlesText[];

    public DashboardAdapter(Context context, String[] titlesText) {
        this.context = context;
        this.titlesText=titlesText;
    }

    @Override
    public int getCount() {
        return titlesText.length;
    }

    @Override
    public Object getItem(int position) {
        return titlesText[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.gv_dashboard, null);
        }
        TextView tvTitle = convertView.findViewById(R.id.tv_heading);
        CardView cardItem = convertView.findViewById(R.id.carditem);

        tvTitle.setText(titlesText[position]);
        if(Helpers.getPreferenceValues(context,context.getResources().getString(R.string.user_type)).equals("admin"))
        {
            if(titlesText[position].equals(context.getResources().getString(R.string.complaints_status)))
            {
                cardItem.setVisibility(View.VISIBLE);
            }
            else
            {
                cardItem.setVisibility(View.GONE);
            }
        }
        else
        {
            cardItem.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}

