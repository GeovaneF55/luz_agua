package pucminas.com.br.luz_agua.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import pucminas.com.br.luz_agua.R;

public class AuthController {
    // Sign in request code
    public static final int RC_SIGN_IN = 123;

    private AppCompatActivity mActivity;
    private FirebaseUser mUser;

    public AuthController(AppCompatActivity activity) {
        mActivity = activity;
    }

    public FirebaseAuth.AuthStateListener auth() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        // Not signed in
        if (user == null) {
            startSignIn();
        }

        return new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    mUser = user;
                }
            }
        };
    }

    public FirebaseUser getUser() {
        return mUser;
    }

    public void addAuthStateListener(FirebaseAuth.AuthStateListener authStateListener) {
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
    }

    public void removeAuthStateListener(FirebaseAuth.AuthStateListener authStateListener) {
        FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
    }

    public void handleSignIn(int resultCode, Intent data) {
        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (resultCode == AppCompatActivity.RESULT_OK) {
            // Successfully signed in
            Snackbar.make(mActivity.findViewById(R.id.content_main),
                    mActivity.getString(R.string.welcome),
                    Snackbar.LENGTH_LONG)
                .show();
        } else if (response != null) {
            if (Objects.requireNonNull(response.getError()).getErrorCode() == ErrorCodes.NO_NETWORK) {
                // No internet connection
                Snackbar.make(mActivity.findViewById(R.id.content_main),
                        mActivity.getString(R.string.no_internet_connection),
                        Snackbar.LENGTH_LONG)
                        .show();
            } else {
                // Errors
                Snackbar.make(mActivity.findViewById(R.id.content_main),
                        "Error: " + response.getError(),
                        Snackbar.LENGTH_LONG)
                        .show();
            }
        }
    }

    public void handleSignOut() {
        AuthUI.getInstance()
                .signOut(mActivity)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mActivity.recreate();
                    }
                });
    }

    private void startSignIn() {
        // List of available providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        // Start sign-in activity
        mActivity.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false)
                        .setTheme(R.style.GreenTheme)
                        .build(),
                RC_SIGN_IN
        );
    }
}
