package com.samuelbaron.contatos.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactsRepository {
    private ContactsDAO mContactsDAO;
    private LiveData<List<Contact>> mAllContacts;

    public ContactsRepository(Application application) {
        ContactsDatabase db = ContactsDatabase.getDatabase(application);
        mContactsDAO = db.contactsDAO();
        mAllContacts = mContactsDAO.getAllContacts();
    }

    /***********************************************
     GET ALL CONTACTS
     ***********************************************/
    public LiveData<List<Contact>> getAllContacts() {
        return mAllContacts;
    }

    /***********************************************
     GET CONTACT BY ID
     ***********************************************/
    public LiveData<Contact> getContactById(int id) {
        return mContactsDAO.getContactById(id);
    }

    /***********************************************
     INSERT CONTACT TASKS
     ***********************************************/
    public void insert(Contact contact) {
        new insertAsyncTask(mContactsDAO).execute(contact);
    }

    public static class insertAsyncTask extends AsyncTask<Contact, Void, Void> {
        private ContactsDAO mAsyncTaskDAO;

        insertAsyncTask(ContactsDAO dao) {
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(final Contact... params) {
            mAsyncTaskDAO.insert(params[0]);
            return null;
        }
    }

    /***********************************************
     UPDATE CONTACT TASKS
     ***********************************************/
    public void update(Contact contact) {
        new updateAsyncTask(mContactsDAO).execute(contact);
    }

    private static class updateAsyncTask extends AsyncTask<Contact, Void, Void> {
        private ContactsDAO mAsyncTaskDAO;

        updateAsyncTask(ContactsDAO dao) {
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(final Contact... params) {
            mAsyncTaskDAO.update(params[0]);
            return null;
        }
    }

    /***********************************************
     DELETE CONTACT TASKS
     ***********************************************/
    public void delete(Contact contact) {
        new deleteAsyncTask(mContactsDAO).execute(contact);
    }

    private static class deleteAsyncTask extends AsyncTask<Contact, Void, Void> {
        private ContactsDAO mAsyncTaskDAO;

        deleteAsyncTask(ContactsDAO dao) {
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(final Contact... params) {
            mAsyncTaskDAO.delete(params[0]);
            return null;
        }
    }

    /***********************************************
     GET ANY CONTACT
     ***********************************************/
    public int[] getAnyContact() {
        return mContactsDAO.getAnyContact();
    }
}
