package com.example.homework03_program01;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<User> listOfUsers;

    public UserListAdapter(Context c, ArrayList<User> ls)
    {
        context = c;
        listOfUsers = ls;
    }


    @Override
    public int getCount() {
        return listOfUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfUsers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.custom_cell, null);
        }

        //find the GUI elements in my custom cell
        TextView name = view.findViewById(R.id.tv_v_cc_name);
        TextView uName = view.findViewById(R.id.tv_v_cc_uName);

        //get the user at position i (i is passed to this function)
        User user = listOfUsers.get(i);

        //set the GUI for the custom_cell.xml
        name.setText(user.getfName() + " " + user.getlName());
        uName.setText(user.getuName());

        //return the view we created.
        return view;
    }
}
