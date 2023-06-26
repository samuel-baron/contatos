package com.samuelbaron.contatos.ui.main;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.samuelbaron.contatos.data.Contact;
import com.samuelbaron.contatos.data.ContactsRepository;

public class AddEditViewModel extends AndroidViewModel {
    private LiveData<Contact> contact;
    private ContactsRepository mRepository;

    public AddEditViewModel(Application application) {
        super(application);
        mRepository = new ContactsRepository(application);
    }

    public LiveData<Contact> getContactById(int id) {
        contact = mRepository.getContactById(id);
        return contact;
    }

    public void insert(Contact contact) {
        mRepository.insert(contact);
    }

    public void update(Contact contact) {
        mRepository.update(contact);
    }
}
