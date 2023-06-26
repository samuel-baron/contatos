package com.samuelbaron.contatos.ui.main;

import static com.samuelbaron.contatos.MainActivity.CONTACT_ID;
import static com.samuelbaron.contatos.MainActivity.NEW_CONTACT;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.samuelbaron.contatos.R;
import com.samuelbaron.contatos.data.Contact;

public class AddEditFragment extends Fragment {

    private int contactId;
    private boolean addingNewContact;
    private FloatingActionButton saveContactFAB;
    private AddEditViewModel addEditViewModel;
    private TextInputLayout nameTextInputLayout;
    private TextInputLayout phoneTextInputLayout;
    private TextInputLayout emailTextInputLayout;


    public static AddEditFragment newInstance() {
        return new AddEditFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_add_edit, container, false);

        nameTextInputLayout = view.findViewById(R.id.nameTextInputLayout);
        phoneTextInputLayout = view.findViewById(R.id.phoneTextInputLayout);
        emailTextInputLayout = view.findViewById(R.id.emailTextInputLayout);

        saveContactFAB = view.findViewById(R.id.saveButton);
        saveContactFAB.setOnClickListener(saveContactButtonClicked);

        Bundle arguments = getArguments();
        contactId = arguments.getInt(CONTACT_ID);


        if (contactId == NEW_CONTACT) {
            addingNewContact = true;
        } else {
            addingNewContact = false;
        }

        return view;
    }

    private final View.OnClickListener saveContactButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((InputMethodManager) getActivity().getSystemService(
                    Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    getView().getWindowToken(), 0);
            saveContact();
        }
    };

    private void saveContact() {
        String name = nameTextInputLayout.getEditText().getText().toString();
        String phone = phoneTextInputLayout.getEditText().getText().toString();
        String email = emailTextInputLayout.getEditText().getText().toString();

        if (addingNewContact) {
            Contact contact = new Contact(name, phone, email);
            addEditViewModel.insert(contact);
        } else {
            Contact contact = new Contact(contactId, name, phone, email);
            addEditViewModel.update(contact);
        }

        Navigation.findNavController(getView()).popBackStack();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addEditViewModel = new ViewModelProvider(this).get(AddEditViewModel.class);

        if (addingNewContact == false) {
            addEditViewModel.getContactById(contactId).observe(getViewLifecycleOwner(), new Observer<Contact>() {
                @Override
                public void onChanged(Contact contact) {
                    nameTextInputLayout.getEditText().setText(contact.getName());
                    phoneTextInputLayout.getEditText().setText(contact.getPhone());
                    emailTextInputLayout.getEditText().setText(contact.getEmail());
                }
            });
        }

    }

}