package com.samuelbaron.contatos.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.samuelbaron.contatos.R;
import com.samuelbaron.contatos.data.Contact;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    // interface implementada por ContactsFragment para responder
    // quando o usuário toca em um item na RecyclerView
    public interface ContactClickListener {
        void onClick(int contactId);
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        private final MaterialCardView cardView;
        private final TextView nameTextView;
        private final TextView phoneTextView;
        private final TextView emailTextView;
        private int contactId;

        private ContactsViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(contactId);
                }
            });
        }

        public void setContactId(int contactId) {
            this.contactId = contactId;
        }
    }

    private List<Contact> mContacts;
    private final ContactClickListener clickListener;

    public ContactsAdapter(ContactClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_list_item_1, parent, false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        Contact current = mContacts.get(position);
        holder.nameTextView.setText(current.getName());
        holder.phoneTextView.setText(current.getPhone());
        holder.emailTextView.setText(current.getEmail());
        holder.setContactId(current.getId());
    }

    void setContacts(List<Contact> contacts) {
        mContacts = contacts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mContacts != null)
            return mContacts.size();
        else return 0;
    }
}
