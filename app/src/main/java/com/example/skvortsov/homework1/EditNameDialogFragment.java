package com.example.skvortsov.homework1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.app.AlertDialog;

/*
public static class MyDialogFragment extends DialogFragment {
    int mNum;


static MyDialogFragment newInstance(int num) {
        MyDialogFragment f = new MyDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
        }

@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt("num");

        // Pick a style based on the num.
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        switch ((mNum-1)%6) {
        case 1: style = DialogFragment.STYLE_NO_TITLE; break;
        case 2: style = DialogFragment.STYLE_NO_FRAME; break;
        case 3: style = DialogFragment.STYLE_NO_INPUT; break;
        case 4: style = DialogFragment.STYLE_NORMAL; break;
        case 5: style = DialogFragment.STYLE_NORMAL; break;
        case 6: style = DialogFragment.STYLE_NO_TITLE; break;
        case 7: style = DialogFragment.STYLE_NO_FRAME; break;
        case 8: style = DialogFragment.STYLE_NORMAL; break;
        }
        switch ((mNum-1)%6) {
        case 4: theme = android.R.style.Theme_Holo; break;
        case 5: theme = android.R.style.Theme_Holo_Light_Dialog; break;
        case 6: theme = android.R.style.Theme_Holo_Light; break;
        case 7: theme = android.R.style.Theme_Holo_Light_Panel; break;
        case 8: theme = android.R.style.Theme_Holo_Light; break;
        }
        setStyle(style, theme);
        }

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        View tv = v.findViewById(R.id.text);
        ((TextView)tv).setText("Dialog #" + mNum + ": using style "
        + getNameForNum(mNum));

        // Watch for button clicks.
        Button button = (Button)v.findViewById(R.id.show);
        button.setOnClickListener(new OnClickListener() {
public void onClick(View v) {
        // When button is clicked, call up to owning activity.
        ((FragmentDialog)getActivity()).showDialog();
        }
        });

        return v;
        }
        }
 */

public class EditNameDialogFragment extends DialogFragment {
    int mNum;



    static EditNameDialogFragment newInstance(int num) {
        EditNameDialogFragment f = new EditNameDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt("num");

        // Watch for button clicks.

        // Pick a style based on the num.
        int style = DialogFragment.STYLE_NORMAL, theme = 0;

        switch ((mNum)%3) {
            case 0: style = DialogFragment.STYLE_NORMAL; break;
            case 1: style = DialogFragment.STYLE_NO_TITLE; break;
            case 2: style = DialogFragment.STYLE_NO_FRAME; break;
        }
        switch ((mNum)%6) {
            case 0: theme = android.R.style.Theme_Holo; break;
            case 1: theme = android.R.style.Theme_Holo_Light; break;
            case 2: theme = android.R.style.Theme_Holo_Dialog; break;
            case 3: theme = android.R.style.Theme_Holo_Light_Dialog; break;
            case 4: theme = android.R.style.Theme_Holo_Panel; break;
            case 5: theme = android.R.style.Theme_Holo_Light_Panel; break;

        }

        setStyle(style, theme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_name, container, false);
        getDialog().setTitle("Simple Dialog");

        View tv = v.findViewById(R.id.title);
        ((TextView)tv).setText("Dialog #" + mNum + ": using style "
                + mNum);

        Button button = (Button)v.findViewById(R.id.dismiss);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // When button is clicked, call up to owning activity.
                dismiss();
            }
        });

        return v;
    }
}

/*
public class EditNameDialogFragment extends DialogFragment {

    private EditText mEditText;

    public EditNameDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static EditNameDialogFragment newInstance(String title) {
        EditNameDialogFragment frag = new EditNameDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_name, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mEditText = (EditText) view.findViewById(R.id.txt_your_name);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }


} */