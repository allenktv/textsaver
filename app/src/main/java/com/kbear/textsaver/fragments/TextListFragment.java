package com.kbear.textsaver.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.kbear.textsaver.activities.MainActivity;
import com.kbear.textsaver.R;
import com.kbear.textsaver.utils.MessageService;
import com.kbear.textsaver.utils.ShareHelper;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by allen on 8/9/14.
 */
public class TextListFragment extends Fragment {
    MainActivity mActivity;
    ListView mListView;
    ArrayAdapter<String> mAdapter;

    ArrayList<String> mTexts;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (MainActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTexts = new ArrayList<String>();
        mAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_1, mTexts);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_texlistfragment, container, false);
        final EditText addTextET = (EditText)v.findViewById(R.id.add_text_ET);
        mListView = (ListView)v.findViewById(android.R.id.list);
        Button sendButton = (Button)v.findViewById(R.id.send_button);
        mListView.setAdapter(mAdapter);
        mListView.setEmptyView(v.findViewById(android.R.id.empty));

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addTextET.getText().length() > 0) {
                    addText(addTextET.getText().toString());
                    addTextET.getText().clear();
                } else {
                    showToast(getString(R.string.empty_text));
                }
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ShareHelper.copyToClipboard(mActivity, mTexts.get(i));
                showToast(getString(R.string.copied));
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                return false;
            }
        });

        loadAllTexts();

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_delete_all:
                MessageService.deleteAllMessages();
                mTexts.clear();
                mAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addText(String s) {
        mTexts.add(s);
        MessageService.saveAllMessages(new HashSet<String>(mTexts));
        mAdapter.notifyDataSetChanged();
    }

    private void loadAllTexts() {
        if (MessageService.getAllMessages() != null) {
            mTexts.addAll(MessageService.getAllMessages());
            mAdapter.notifyDataSetChanged();
        }
    }

    private void showToast(String string) {
        Toast toast = Toast.makeText(mActivity, string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }
}
