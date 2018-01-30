package com.example.galen.db_contacts.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.galen.db_contacts.Contact;

import java.util.ArrayList;

public class ContactDAO {
    private SQLiteDatabase db;

    public ContactDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Contact Contact) {
        ContentValues values = new ContentValues();
        values.put(ContactsTable.COLUMN_NAME, Contact.getName());
        values.put(ContactsTable.COLUMN_EMAIL, Contact.getEmail());
        values.put(ContactsTable.COLUMN_PHONE, Contact.getPhone());
        values.put(ContactsTable.COLUMN_DEPT, Contact.getDepartment());
        return db.insert(ContactsTable.TABLENAME, null, values);
    }

    public boolean update(Contact Contact) {
        ContentValues values = new ContentValues();
        values.put(ContactsTable.COLUMN_NAME, Contact.getName());
        values.put(ContactsTable.COLUMN_EMAIL, Contact.getEmail());
        values.put(ContactsTable.COLUMN_PHONE, Contact.getPhone());
        values.put(ContactsTable.COLUMN_DEPT, Contact.getDepartment());
        return db.update(ContactsTable.TABLENAME, values,
                ContactsTable.COLUMN_ID + "=?", new String[]{String.valueOf(Contact.getId())}) > 0;
    }

    public boolean delete(Contact Contact) {
        return db.delete(ContactsTable.TABLENAME,
                ContactsTable.COLUMN_ID + "=?", new String[]{String.valueOf(Contact.getId())}) > 0;
    }

    public Contact get(long id) {
        Contact contact = null;
        Cursor cursor = db.query(true, ContactsTable.TABLENAME, new String[]{ContactsTable.COLUMN_ID,
                        ContactsTable.COLUMN_NAME, ContactsTable.COLUMN_PHONE, ContactsTable.COLUMN_EMAIL,
                        ContactsTable.COLUMN_DEPT},
                ContactsTable.COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            contact = buildContactFromCursor(cursor);
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }

        return contact;
    }

    public ArrayList<Contact> getAll() {
        ArrayList<Contact> Contacts = new ArrayList<>();

        Cursor cursor = db.query(ContactsTable.TABLENAME, new String[]{ContactsTable.COLUMN_ID,
                ContactsTable.COLUMN_NAME, ContactsTable.COLUMN_PHONE, ContactsTable.COLUMN_EMAIL,
                ContactsTable.COLUMN_DEPT}, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Contact Contact = buildContactFromCursor(cursor);
                if (Contact != null) {
                    Contacts.add(Contact);
                }
            } while (cursor.moveToNext());

            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
        return Contacts;
    }

    public ArrayList<Contact> getFiltered(String filter) {
        ArrayList<Contact> Contacts = new ArrayList<>();

        Cursor cursor = db.query(ContactsTable.TABLENAME, new String[]{ContactsTable.COLUMN_ID,
                        ContactsTable.COLUMN_NAME, ContactsTable.COLUMN_PHONE, ContactsTable.COLUMN_EMAIL,
                        ContactsTable.COLUMN_DEPT},
                ContactsTable.COLUMN_NAME + " like ?", new String[]{ "%" + filter + "%" },
                null, null, ContactsTable.COLUMN_NAME+" COLLATE NOCASE asc");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Contact Contact = buildContactFromCursor(cursor);
                if (Contact != null) {
                    Contacts.add(Contact);
                }
            } while (cursor.moveToNext());

            if (!cursor.isClosed()) {


                cursor.close();
            }
        }
        return Contacts;
    }

    public Contact buildContactFromCursor(Cursor cursor) {
        Contact contact = null;

        if (cursor != null) {
            contact = new Contact();
            contact.setId(cursor.getLong(0));
            contact.setName(cursor.getString(1));
            contact.setPhone(cursor.getString(2));
            contact.setEmail(cursor.getString(3));
            contact.setDepartment(cursor.getString(4));
        }
        return contact;
    }
}
