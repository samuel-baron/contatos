package com.samuelbaron.contatos.ui.main;

import static com.samuelbaron.contatos.MainActivity.CONTACT_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samuelbaron.contatos.R;
import com.samuelbaron.contatos.data.Contact;

public class DetailFragment extends Fragment {
    private int contactId;
    private DetailViewModel detailViewModel;
    private TextView nameTextView;
    private TextView phoneTextView;
    private TextView emailTextView;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        setHasOptionsMenu(true);

        nameTextView = view.findViewById(R.id.nameTextView);
        phoneTextView = view.findViewById(R.id.phoneTextView);
        emailTextView = view.findViewById(R.id.emailTextView);

        Bundle arguments = getArguments();
        if (arguments != null) {
            contactId = arguments.getInt(CONTACT_ID);
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_details_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                editContact();
                return true;
            case R.id.action_delete:
                deleteContact();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void editContact() {
        Bundle arguments = new Bundle();
        arguments.putInt(CONTACT_ID, contactId);
        Navigation.findNavController(getView()).navigate(R.id.action_detailFragment_to_addEditFragment, arguments);
    }

    private void deleteContact() {
        detailViewModel.delete();
        Navigation.findNavController(getView()).popBackStack();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        detailViewModel.getContactById(contactId).observe(getViewLifecycleOwner(), new Observer<Contact>() {
            @Override
            public void onChanged(Contact contact) {
                nameTextView.setText(contact.getName());
                phoneTextView.setText(contact.getPhone());
                emailTextView.setText(contact.getEmail());
            }
        });
    }
}