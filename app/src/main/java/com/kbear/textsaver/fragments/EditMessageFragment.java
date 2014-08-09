package com.kbear.textsaver.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.analytics.HitBuilders;
import com.kbear.textsaver.R;
import com.kbear.textsaver.activities.MainActivity;
import com.kbear.textsaver.activities.TextSaverApplication;
import com.kbear.textsaver.utils.MessageService;
import com.kbear.textsaver.utils.TrackingConstants;

/**
 * Created by allen on 8/9/14.
 */
public class EditMessageFragment extends Fragment {

    MainActivity mActivity;
    private EditText mEditText;
    private String mEditMessage = "";

    public static final String EDIT_MESSAGE = "EditTextFragment - Edit";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (MainActivity) activity;
    }

    public static EditMessageFragment newInstance(String s) {
        EditMessageFragment fragment = new EditMessageFragment();
        Bundle args = new Bundle();
        args.putString(EDIT_MESSAGE, s);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mEditMessage = getArguments().getString(EDIT_MESSAGE);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.edittext_fragment, container, false);

        mEditText = (EditText) v.findViewById(R.id.edit_message_ET);
        mEditText.setText(mEditMessage);
        mEditText.setSelection(mEditText.getText().length());

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.edit_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.save_edit:
                if (mEditText.getText().length() > 0) {
                    ((TextSaverApplication)mActivity.getApplication()).getTracker().send(new HitBuilders.EventBuilder()
                            .setAction(TrackingConstants.EDIT_KEY)
                            .build());
                    MessageService.saveMessage(mEditMessage, mEditText.getText().toString());
                    mActivity.showToast(getString(R.string.text_saved));
                    mActivity.getFragmentManager().popBackStackImmediate();
                } else {
                    mActivity.showToast(getString(R.string.empty_text));
                }
                break;
            case R.id.delete_edit:
                ((TextSaverApplication)mActivity.getApplication()).getTracker().send(new HitBuilders.EventBuilder()
                        .setAction(TrackingConstants.DELETE_KEY)
                        .build());
                MessageService.deleteMessage(mEditMessage);
                mActivity.showToast(getString(R.string.text_deleted));
                mActivity.getFragmentManager().popBackStackImmediate();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
