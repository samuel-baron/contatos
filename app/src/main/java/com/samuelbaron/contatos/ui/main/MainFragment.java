package com.samuelbaron.contatos.ui.main;

import static com.samuelbaron.contatos.MainActivity.CONTACT_ID;
import static com.samuelbaron.contatos.MainActivity.NEW_CONTACT;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.samuelbaron.contatos.R;
import com.samuelbaron.contatos.data.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private MainViewModel mViewModel;
    ContactsAdapter contactsAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        //recyclerView.addItemDecoration(new ItemDivider(getContext()));
        contactsAdapter = new ContactsAdapter(new ContactsAdapter.ContactClickListener() {
            @Override
            public void onClick(int contactId) {
                onContactSelected(contactId);
            }
        });
        recyclerView.setAdapter(contactsAdapter);

        FloatingActionButton addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddContact();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mViewModel.getAllContacts().observe(getViewLifecycleOwner(), contacts -> contactsAdapter.setContacts(contacts));
    }

    private void onAddContact() {
        Bundle arguments = new Bundle();
        arguments.putInt(CONTACT_ID, NEW_CONTACT);
        Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_addEditFragment, arguments);
    }

    private void onContactSelected(int contactId) {
        Bundle arguments = new Bundle();
        arguments.putInt(CONTACT_ID, contactId);
        Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_detailFragment, arguments);
    }

}