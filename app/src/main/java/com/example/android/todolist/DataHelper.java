package com.example.android.todolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class DataHelper {

    public static final String STORE = "itemstore.dat";

    public static void writeItem(ArrayList<String> items, Context context)
    {
        try {
            FileOutputStream  fos =  context.openFileOutput(STORE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<String> readItem(Context context){
        ArrayList<String> itemsList = null;
        try {
            FileInputStream fis = context.openFileInput(STORE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemsList = (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {

            itemsList = new ArrayList<>();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemsList;

    }
}
