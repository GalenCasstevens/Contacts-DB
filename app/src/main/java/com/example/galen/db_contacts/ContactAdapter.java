package com.example.galen.db_contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Galen on 11/20/17.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {
    public ContactAdapter(Context context, int resource, List<Contact> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact Contact = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);

        TextView nameText = convertView.findViewById(R.id.nameText);
        TextView emailText = convertView.findViewById(R.id.emailText);
        TextView phoneText = convertView.findViewById(R.id.phoneText);
        TextView departmentText = convertView.findViewById(R.id.departmentText);

        nameText.setText(Contact.getName());
        emailText.setText(Contact.getEmail());
        phoneText.setText(Contact.getPhone());
        departmentText.setText(Contact.getDepartment());

        return convertView;
    }
}
