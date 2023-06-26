package com.samuelbaron.contatos.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactsDAO {
    @Query("SELECT * FROM contacts_table ORDER BY name ASC")
    LiveData<List<Contact>> getAllContacts();

    @Query("SELECT * FROM contacts_table WHERE id = :id")
    LiveData<Contact> getContactById(int id);

    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("DELETE FROM contacts_table")
    void deleteAllContacts();

    @Query("SELECT * FROM contacts_table LIMIT 1")
    int[] getAnyContact();
}
