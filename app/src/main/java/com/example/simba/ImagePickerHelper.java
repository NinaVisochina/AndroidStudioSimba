package com.example.simba;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import java.io.IOException;

public class ImagePickerHelper {

    private final Activity activity;
    private Bitmap selectedImageBitmap;

    public ImagePickerHelper(Activity activity) {
        this.activity = activity;
    }

    public void openImagePicker(ActivityResultLauncher<Intent> imagePickerLauncher) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        imagePickerLauncher.launch(intent);
    }

    public void handleImagePickerResult(Uri imageUri) {
        try {
            selectedImageBitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap getSelectedImageBitmap() {
        return selectedImageBitmap;
    }
}

