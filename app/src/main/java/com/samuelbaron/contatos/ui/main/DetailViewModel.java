package com.samuelbaron.contatos.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.samuelbaron.contatos.data.Contact;
import com.samuelbaron.contatos.data.ContactsRepository;

public class DetailViewModel extends AndroidViewModel {
    private LiveData<Contact> mContact;
    private ContactsRepository mRepository;

    public DetailViewModel(Application application) {
        super(application);
        mRepository = new ContactsRepository(application);
    }

    public LiveData<Contact> getContactById(int id) {
        mContact = mRepository.getContactById(id);
        return mContact;
    }

    public void delete() {
        mRepository.delete(mContact.getValue());
    }
}