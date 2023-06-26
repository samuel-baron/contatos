package com.samuelbaron.contatos.ui.main;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.samuelbaron.contatos.data.Contact;
import com.samuelbaron.contatos.data.ContactsRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<Contact>> mAllContacts;
    private ContactsRepository mRepository;

    public MainViewModel(Application application) {
        super(application);
        mRepository = new ContactsRepository(application);
        mAllContacts = mRepository.getAllContacts();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return mAllContacts;
    }
}
