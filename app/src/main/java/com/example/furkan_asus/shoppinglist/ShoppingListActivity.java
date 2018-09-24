package com.example.furkan_asus.shoppinglist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {


    private ListView mListView;
    private ArrayAdapter mAdapter;
    private List<String> mItems;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing the lists and adapter
        mListView = findViewById(R.id.list_view);
        mItems = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mItems);
        mListView.setAdapter(mAdapter);
        mEditText = findViewById(R.id.edit_text);

        mItems.add("Pizza");
        mItems.add("icecream");



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });


        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mItems.remove(position);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });


        updateUI();
    }

    private void addItem(){
        String text = mEditText.getText().toString();
        String newItem = new String(text);

        // A simple check checking if the text is empty, if so then it cannot be added and the user will be notified.
        if (!newItem.isEmpty()) {
            mItems.add(newItem);
            mEditText.setText("");
            updateUI();
            Toast.makeText(this, "Item added.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter a value in the text field.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(){
        if(mAdapter == null){
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mItems);
            mListView.setAdapter(mAdapter);
        } else{
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping_list, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        // Checking the item id of our menu item.
        if (item.getItemId() == R.id.action_delete_item) {

            // Deleting all items and notifying our list adapter of the changes.
            mItems.clear();
            updateUI();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
