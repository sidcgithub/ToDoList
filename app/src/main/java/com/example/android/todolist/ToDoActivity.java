package com.example.android.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ToDoActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {


    EditText enterItemText;
    Button addButton;
    ListView toDoList;
    public static ArrayList<String> items;
    public static ArrayAdapter<String> itemAdapter;
    public static int editPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);



        enterItemText = (EditText)findViewById(R.id.editTextEnterItem);
        addButton = (Button)findViewById(R.id.addItem);
        toDoList = (ListView)findViewById(R.id.itemList);

        if(DataHelper.readItem(this)==null)
        {
            items= new ArrayList<>();
        }
        else
        {
            items = DataHelper.readItem(this);
        }
        itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        toDoList.setAdapter(itemAdapter);

        addButton.setOnClickListener(this);
        toDoList.setOnItemClickListener(this);
        toDoList.setOnItemLongClickListener(this);








    }

    //By clicking the add button you add an item to the list

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.addItem:



                if(addButton.getText().equals("Add")) {
                    itemAdapter.add(enterItemText.getText().toString());

                    enterItemText.setText("");
                    DataHelper.writeItem(items, this);
                    Toast.makeText(this, "You added an item", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    items.set(editPosition, enterItemText.getText().toString());
                    itemAdapter.notifyDataSetChanged();
                    DataHelper.writeItem(items, this);
                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                    enterItemText.setText("");
                    addButton.setText("Add");
                }

            break;

        }

    }

    public static boolean exitDelete = true;
//On clicking item you confirm that it is complete by removing it from the list
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        final int  position = i;
        new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Do you really want to delete?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        items.remove(position);
                        itemAdapter.notifyDataSetChanged();
                        DataHelper.writeItem(items, ToDoActivity.this);
                        Toast.makeText(ToDoActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    }})
                .setNegativeButton(android.R.string.no, null).show();




    }
//You can edit an item by long pressing it
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        addButton.setText("Edit");
        enterItemText.setText(items.get(i));
        editPosition = i;
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar actions click
        switch (item.getItemId()) {

            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                Intent SignInPage = new Intent(ToDoActivity.this, SignInActivity.class);
                startActivity(SignInPage);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
