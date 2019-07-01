package com.example.dell.myapp;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class Userlist extends ArrayAdapter<Users> {
    private Activity context;
    private List<Users> UserList;
    public Userlist(Activity context,List<Users> Userlist){
        super(context,R.layout.list_layout,Userlist);
        this.context=context;
        this.UserList=Userlist;
    }

    //@androidx.annotation.NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.list_layout,null,true);
        TextView textview1=(TextView)listViewItem.findViewById(R.id.textView1);
        TextView textview2=(TextView)listViewItem.findViewById(R.id.textView2);
        TextView textview3=(TextView)listViewItem.findViewById(R.id.textView3);
        TextView textview4=(TextView)listViewItem.findViewById(R.id.textView4);
        TextView textview5=(TextView)listViewItem.findViewById(R.id.textView5);
        //return super.getView(position, convertView, parent);
        Users user=UserList.get(position);
        textview1.setText(user.getUsername());
        textview2.setText(user.getUserfrom());
        textview3.setText(user.getUserto());
        textview4.setText(user.getUserdesc());
        textview5.setText(user.getUsermail());

        return listViewItem;
    }
}
