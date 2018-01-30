package com.example.galen.db_contacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.galen.db_contacts.DB.DBDataManager;

public class NewContactActivity extends AppCompatActivity {
    String name,
            phone,
            email,
            department;
    EditText nameEdit,
            phoneEdit,
            emailEdit;
    Button submitButton,
            cancelButton;
    RadioGroup myRadioGroup;
    RadioButton sisRadio,
            csRadio,
            bioRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        nameEdit = (EditText) findViewById(R.id.nameEdit);
        phoneEdit = (EditText) findViewById(R.id.phoneEdit);
        emailEdit = (EditText) findViewById(R.id.emailEdit);
        myRadioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);
        submitButton = (Button) findViewById(R.id.submitButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        sisRadio = (RadioButton) findViewById(R.id.sisRadio);
        csRadio = (RadioButton) findViewById(R.id.bioRadio);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameEdit.getText().toString();
                phone = phoneEdit.getText().toString();
                email = emailEdit.getText().toString();
                department = "SIS";

                if(myRadioGroup.getCheckedRadioButtonId() == R.id.sisRadio){
                    department = "SIS";
                } else if(myRadioGroup.getCheckedRadioButtonId() == R.id.csRadio) {
                    department = "CS";
                } else if(myRadioGroup.getCheckedRadioButtonId() == R.id.bioRadio) {
                    department = "BIO";
                }

                if(name == "") {
                    Toast.makeText(NewContactActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                } else if(email == "") {
                    Toast.makeText(NewContactActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                } else if(phone == "") {
                    Toast.makeText(NewContactActivity.this, "Enter phone", Toast.LENGTH_SHORT).show();
                } else {
                    Contact contact = new Contact(name, email, phone, department);

                    DBDataManager db = new DBDataManager(NewContactActivity.this);
                    db.getContactDAO().save(contact);

                    setResult(RESULT_OK);
                    finish();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
