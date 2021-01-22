package com.sng.assignment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sng.assignment.adapter.ListAdapter;
import com.sng.assignment.model.Item;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityList extends AppCompatActivity implements mClickListener {
    RecyclerView rv_list;
    ImageView iv_add_item;
    ListAdapter listAdapter;
    List<Item> items;
    int REQUEST_CODE_ADD_ITEM = 101;
    int REQUEST_CODE_UPDTAE_ITEM = 102;
    final int MY_PERMISSIONS_REQUEST_READ_STORAGE = 102;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        rv_list = (RecyclerView) findViewById(R.id.rv_list);
        items = new ArrayList<>();
        iv_add_item = (ImageView) findViewById(R.id.iv_add_item);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new ListAdapter(this, items, this);
        rv_list.setAdapter(listAdapter);
        askPermission();
        iv_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(ActivityList.this, ActivityAddItem.class), REQUEST_CODE_ADD_ITEM);
            }
        });

    }

    void askPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_STORAGE);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant

            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null) {
            if (requestCode == REQUEST_CODE_ADD_ITEM && resultCode == 200) {
                Bundle bundle = data.getExtras();
                listAdapter.AddItem((Item) bundle.getSerializable("ITEM"));

            }
            if (requestCode == REQUEST_CODE_UPDTAE_ITEM && resultCode == 200) {
                Bundle bundle = data.getExtras();
                listAdapter.updateItem(bundle.getInt("POSITION"), (Item) bundle.getSerializable("ITEM"));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_STORAGE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {
                    askPermission();

                }
                return;
            }


        }
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(ActivityList.this, ActivityUpdateItem.class);
        intent.putExtra("POSITION", position);
        intent.putExtra("ITEM", items.get(position));
        startActivityForResult(intent, REQUEST_CODE_UPDTAE_ITEM);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelable("LIST", (Parcelable) items);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        items= (List<Item>) savedInstanceState.getParcelable("LIST");
    }
}
