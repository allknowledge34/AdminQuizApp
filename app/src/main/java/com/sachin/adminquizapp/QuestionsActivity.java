package com.sachin.adminquizapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.sachin.adminquizapp.Adapters.CategoryAdapter;
import com.sachin.adminquizapp.Adapters.QuestionAdapter;
import com.sachin.adminquizapp.Models.CategoryModel;
import com.sachin.adminquizapp.Models.QuestionsModel;
import com.sachin.adminquizapp.databinding.ActivityQuestionsBinding;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {

    ActivityQuestionsBinding binding;
    FirebaseDatabase database;
    FirebaseStorage storage;
    QuestionAdapter adapter;
    ArrayList<QuestionsModel> list;
    Dialog loadingDialog;
    private String catId,subCatId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionsBinding.inflate(getLayoutInflater());
        setStatusBarColor();
        setContentView(binding.getRoot());

        catId = getIntent().getStringExtra("catId");
        subCatId = getIntent().getStringExtra("subCatId");
        database= FirebaseDatabase.getInstance();

        list = new ArrayList<>();
        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.show();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvQuestions.setLayoutManager(layoutManager);

        adapter = new QuestionAdapter(this,list,catId,subCatId);
        binding.rvQuestions.setAdapter(adapter);
        database.getReference().child("categories").child(catId).child("subCategories").child(subCatId).child("questions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    list.clear();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                        QuestionsModel model = dataSnapshot.getValue(QuestionsModel.class);
                        model.setKey(dataSnapshot.getKey());
                        list.add(model);
                    }
                    adapter.notifyDataSetChanged();
                    loadingDialog.dismiss();
                }
                else {
                    loadingDialog.dismiss();
                    Toast.makeText(QuestionsActivity.this, "category not exits", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                loadingDialog.dismiss();
                Toast.makeText(QuestionsActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });{

        }

        binding.uploadCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionsActivity.this,UploadQuestionsActivity.class);
                intent.putExtra("catId",catId);
                intent.putExtra("subCatId",subCatId);
                startActivity(intent);
            }
        });
    }
    private void setStatusBarColor() {
        Window window = getWindow();
        int statusBarColor = ContextCompat.getColor(this, R.color.lightblue);
        window.setStatusBarColor(statusBarColor);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}