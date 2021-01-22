package com.sng.assignment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.sng.assignment.model.Item;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityAddItem extends AppCompatActivity {
    EditText et_label, et_description;
    Button btn_add_image,btn_add_item;
    ImageView iv_item;
    int REQUEST_CODE_PICK_IMAGE = 102;
    Item item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        item = new Item();
        et_label=(EditText)findViewById(R.id.et_label);
        et_description=(EditText)findViewById(R.id.et_description);
        btn_add_image=(Button)findViewById(R.id.btn_add_image);
        btn_add_item=(Button)findViewById(R.id.btn_add_item);
        iv_item=(ImageView)findViewById(R.id.iv_item);

        btn_add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE_PICK_IMAGE);
            }
        });
        btn_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setLable(et_label.getText().toString());
                item.setDescription(et_description.getText().toString());
                Intent intent=new Intent();
                intent.putExtra("ITEM",item);
                setResult(200,intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null) {
            if (requestCode == REQUEST_CODE_PICK_IMAGE) {
                Uri selectedImageUri = data.getData();
                item.setImg_path(selectedImageUri.toString());
                Picasso.get().load(selectedImageUri).into(iv_item);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
