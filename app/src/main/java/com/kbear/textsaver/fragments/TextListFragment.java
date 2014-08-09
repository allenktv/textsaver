package com.kbear.textsaver.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.kbear.textsaver.activities.MainActivity;
import com.kbear.textsaver.R;
import com.kbear.textsaver.services.TextService;

/**
 * Created by allen on 8/9/14.
 */
public class TextListFragment extends Fragment {
    MainActivity mActivity;
    ListView mListView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_texlistfragment, container, false);
        final EditText addTextET = (EditText)v.findViewById(R.id.add_text_ET);
        mListView = (ListView)v.findViewById(android.R.id.list);
        Button sendButton = (Button)v.findViewById(R.id.send_button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addTextET.getText().length() > 0) {
                    TextService.saveText(null, null);
                } else {
                    Toast.makeText(mActivity, R.string.empty_text, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}
