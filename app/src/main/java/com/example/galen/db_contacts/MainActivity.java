package com.example.galen.db_contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

import com.example.galen.db_contacts.DB.DBDataManager;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ContactAdapter contactAdapter;
    ArrayList<Contact> contacts;
    EditText editTextFilter;
    DBDataManager myDBDataManager;
    final int CREATE_USER_RES = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDBDataManager = new DBDataManager(this);

        contacts = myDBDataManager.getContactDAO().getAll();

        listView = (ListView) findViewById(R.id.listView);
        contactAdapter = new ContactAdapter(this,R.layout.contact_item, contacts);
        listView.setAdapter(contactAdapter);


        editTextFilter = (EditText) findViewById(R.id.editTextFilter);

        findViewById(R.id.newContactButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
                startActivityForResult(intent, CREATE_USER_RES);
            }
        });

        findViewById(R.id.filterButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactAdapter.clear();
                contactAdapter.addAll(myDBDataManager.getContactDAO().getFiltered(editTextFilter.getText().toString()));

                contactAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                myDBDataManager.getContactDAO().delete(contactAdapter.getItem(i));
                contactAdapter.remove(contactAdapter.getItem(i));
                contactAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CREATE_USER_RES && resultCode == RESULT_OK){
            //refresh the list!!

            contactAdapter.clear();
            contactAdapter.addAll(myDBDataManager.getContactDAO().getAll());

            //contacts.clear();
            //contacts = dbDataManager.getcontactDAO().getAll();

            contactAdapter.notifyDataSetChanged();

        }

    }
}
