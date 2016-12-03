package br.com.loewe.registrodeamecasambientais.config;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Érico de Souza Loewe on 29/11/2016.
 */
public class FirebaseConnection {
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser user;
    private final FirebaseAuth firebaseAuth;
    private final Context context;

    public FirebaseConnection(Context context) {
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
        signIn();
    }

    private void signIn() {
        AuthCredential credential = EmailAuthProvider
                .getCredential("erico.loewe@gmail.com", "registro-de-ameacas-ambientais");

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("FirebaseConnection", "signInWithEmail:onComplete:" + task.isSuccessful());
                user = firebaseAuth.getCurrentUser();
            }
        });
    }

    private void signOut() {
        firebaseAuth.signOut();
    }

    public void open() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("FirebaseConnection", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("FirebaseConnection", "onAuthStateChanged:signed_out");
                }
            }
        };
        start();
    }

    public void close() {
        stop();
    }

    private void start() {
        firebaseAuth.addAuthStateListener(authStateListener);
        signIn();
    }

    private void stop() {
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
            signOut();
        }
    }
}
