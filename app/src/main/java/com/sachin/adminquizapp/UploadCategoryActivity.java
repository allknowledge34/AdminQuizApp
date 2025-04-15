package com.sachin.adminquizapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UploadCategoryActivity extends AppCompatActivity {
    ActivityUploadCategoryBinding binding;
    FirebaseDatabase database;
    FirebaseStorage storage;
    Dialog loadingDialog;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding ActivityUploadCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        database= FirebaseDatabase.getInstance();
        Storage= FirebaseStorage.getInstance();

        loadingDialog= new Dialog(context:this);
        loadingDialog.setContentView(R.layout.Loading dialog);
        LoadingDialog.setCancelable(false);
        binding, fetchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent();
                intent.setAction (Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, requestCode: 1);
            }

            }

        });
        binding.btnUploadCat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                String categoryNane =binding.edtCategory.getText().toString();
                if (categoryName.isEmpty()){
                    binding.edtCategory.setError("Enter category name");

                }else if (inageuri==null) {
                    Toast.makeText(context: UploadCategoryActivity. this, text: "Select category image", Toast.LENGTH_SHORT).show();


                }
                else{
                    UploadData(categoryName, imageUri);


                }

            }

        });


    }

    private void uploadData(String categoryName, Uri imageUri) {

        loadingDialog.show();
        final StorageReference reference = storage.getReference().child(pathString: "categoryImage")
                .child(pathString: new Date().getTime()+"");
        reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask. TaskSnapshot>() {
            @Override
            public void onSuccess (UploadTask. TaskSnapshot taskSnapshot) {
                
            }
       });
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (requestCode==1){
                imageUri= data.getData();
                binding.categoryImage.setImageURI(imageUri);

            }
        }

    }





}