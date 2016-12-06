package br.com.loewe.registrodeamecasambientais.IO;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

/**
 * Created by Érico de Souza Loewe on 05/12/2016.
 */
public class ImageIOFirebase {
    private final String BUCKET_URL = "gs://registro-de-amecas-ambientais.appspot.com";
    private String directoryName = "images";
    private StorageReference storageReference;

    public ImageIOFirebase() {
        this.storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL).child(directoryName);
    }

    public ImageIOFirebase(String directoryName) {
        this();
        this.directoryName = directoryName;
        this.storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL).child(this.directoryName);
    }

    public ImageIOFirebase setSubDirectory(String directory) {
        storageReference = storageReference.child(directory);
        return this;
    }

    public ImageIOFirebase setFileName(String fileName) {
        storageReference = storageReference.child(fileName);
        return this;
    }

    public void save(final Bitmap bitmap, final OnSaveImage promise) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = storageReference.putBytes(data);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                promise.onSuccess(bitmap, downloadUrl);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                promise.onFailure(bitmap, exception);
            }
        });
    }

    public void delete(String imageUrl) {
        FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl).delete();
    }

    public interface OnSaveImage {
        void onSuccess(Bitmap bitmap, Uri downloadUrl);

        void onFailure(Bitmap bitmap, Exception exception);
    }
}
