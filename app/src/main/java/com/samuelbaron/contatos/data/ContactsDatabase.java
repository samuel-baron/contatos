package com.samuelbaron.contatos.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {
    public abstract ContactsDAO contactsDAO();

    private static volatile ContactsDatabase INSTANCE;

    static ContactsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ContactsDatabase.class, "contacts_database")
                            .addCallback(sRoomDatabaseCallback) //POPULATE DB WITH SAMPLE DATA
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final ContactsDAO mDao;

        PopulateDbAsync(ContactsDatabase db) {
            mDao = db.contactsDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // verify if db is empty
            if (mDao.getAnyContact().length > 0) {
                return null;
            }
            return null;
        }
    }
}
