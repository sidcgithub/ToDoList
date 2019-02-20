package com.example.android.todolist;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.Espresso;

import static org.junit.Assert.*;

public class ToDoActivityTest {

    @Rule
    public ActivityTestRule<ToDoActivity> mActivityTestRule = new ActivityTestRule<ToDoActivity>(ToDoActivity.class);
    private ToDoActivity mActivity = null;


    @Before
    public void setUp() throws Exception {

        mActivity = mActivityTestRule.getActivity();
    }


    @Test
    public void addItemScenario()
    {
        Button b = mActivity.findViewById(R.id.addItem);
        View itemList = mActivity.findViewById(R.id.itemList);
        assertNotNull(b);
        assertNotNull(itemList);

    }


    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}