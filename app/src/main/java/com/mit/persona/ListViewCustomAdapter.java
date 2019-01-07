package com.mit.persona;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListViewCustomAdapter extends BaseAdapter {

    List<Table_Messages> messagesList;

    public Activity context;
    public LayoutInflater inflater;

    public ListViewCustomAdapter(Activity context, List<Table_Messages> messagesList) {
        super();

        this.context = context;
        this.messagesList = messagesList;

        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return messagesList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return messagesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static class ViewHolder
    {
        TextView txtViewTitle;
        TextView txtViewBody;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generatitemsed method stub

        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.message_card, null);

            holder.txtViewTitle = (TextView) convertView.findViewById(R.id.message_title);
            holder.txtViewBody = (TextView) convertView.findViewById(R.id.message_body);

            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();

        Table_Messages message = (Table_Messages) messagesList.get(position);

        holder.txtViewTitle.setText(message.getTitle());
        holder.txtViewBody.setText(message.getMessage());

        return convertView;
    }

}