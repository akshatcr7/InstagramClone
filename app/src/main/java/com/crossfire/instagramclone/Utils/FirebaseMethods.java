package com.crossfire.instagramclone.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.crossfire.instagramclone.R;
import com.crossfire.instagramclone.models.User;
import com.crossfire.instagramclone.models.UserAccountSettings;
import com.crossfire.instagramclone.models.UserSettings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * @author Akshat Pandey
 * @version 1.0
 * @date 23-10-2017
 */

public class FirebaseMethods {

    private static final String TAG = "FirebaseMethods";

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private Context mContext;
    private String userID;

    public FirebaseMethods(Context context) {
        mAuth = FirebaseAuth.getInstance();
        mContext = context;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        if (mAuth.getCurrentUser() != null) {
            userID = mAuth.getCurrentUser().getUid();
        }
    }

    /**public boolean checkIfUsernameExists(String username, DataSnapshot dataSnapshot) {
        Log.d(TAG, "checkIfUsernameExists: checking if the username already exists");

        User user = new User();

        for (DataSnapshot ds : dataSnapshot.child(userID).getChildren()) {
            user.setUsername(ds.getValue(User.class).getUsername());

            if (StringManipulation.expandUsername(user.getUsername()).equals(username)) {
                Log.d(TAG, "checkIfUsernameExists: Found a match.");
                return true;
            }
        }

        return false;
    }**/

    /**
     * Register a new email and password to Firebase Authentication.
     *
     * @param email
     * @param username
     * @param password
     */
    public void registerNewEmail(final String email, final String username, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(mContext, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        } else if (task.isSuccessful()) {
                            sendVerificationEmail();
                            Log.d(TAG, "onComplete: Authentication Successful.");
                            userID = mAuth.getCurrentUser().getUid();
                        }

                    }
                });
    }

    public void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(mContext, "Couldn't send Verification Email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void updateUsername(String username){
        Log.d(TAG, "updateUsername: Updating username to " + username);

        myRef.child(mContext.getString(R.string.db_name_users)).child(userID).child(mContext.getString(R.string.field_username))
                .setValue(username);

        myRef.child(mContext.getString(R.string.db_user_account_settings)).child(userID).child(mContext.getString(R.string.field_username))
                .setValue(username);
    }

    /**
     * Add information to the users node.
     * Add information to the users_account_setting node.
     *
     * @param email
     * @param username
     * @param description
     * @param website
     * @param profile_photo
     */
    public void addNewUser(String email, String username, String description, String website, String profile_photo) {

        User user = new User(userID, email, 1, StringManipulation.condenseUsername(username));

        myRef.child(mContext.getString(R.string.db_name_users))
                .child(userID)
                .setValue(user);

        UserAccountSettings settings = new UserAccountSettings(description, username, 0, 0, 0, profile_photo, StringManipulation.condenseUsername(username), website);

        myRef.child(mContext.getString(R.string.db_user_account_settings))
                .child(userID)
                .setValue(settings);
    }

    /**
     * Retrieves the user information from the firabase Database.
     *
     * @param dataSnapshot
     * @return
     */
    public UserSettings getUserSettings(DataSnapshot dataSnapshot) {
        Log.d(TAG, "getUserSettings: retrieving user settings from the database.");

        UserAccountSettings settings = new UserAccountSettings();
        User user = new User();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {

            if (ds.getKey().equals(mContext.getString(R.string.db_user_account_settings))) {
                Log.d(TAG, "getUserSettings: data snapshot " + ds);

                try {
                    settings.setDisplay_name(
                            ds.child(userID).getValue(UserAccountSettings.class).getDisplay_name()
                    );

                    settings.setDescription(
                            ds.child(userID).getValue(UserAccountSettings.class).getDescription()
                    );

                    settings.setFollowers(
                            ds.child(userID).getValue(UserAccountSettings.class).getFollowers()
                    );

                    settings.setFollowing(
                            ds.child(userID).getValue(UserAccountSettings.class).getFollowing()
                    );

                    settings.setPosts(
                            ds.child(userID).getValue(UserAccountSettings.class).getPosts()
                    );

                    settings.setProfile_photo(
                            ds.child(userID).getValue(UserAccountSettings.class).getProfile_photo()
                    );

                    settings.setUsername(
                            ds.child(userID).getValue(UserAccountSettings.class).getUsername()
                    );

                    settings.setWebsite(
                            ds.child(userID).getValue(UserAccountSettings.class).getWebsite()
                    );
                } catch (NullPointerException e) {
                    Log.e(TAG, "getUserSettings: Null pointer exception" + e.getMessage());
                }
            }

            if (ds.getKey().equals(mContext.getString(R.string.db_name_users))) {
                Log.d(TAG, "getUserSettings: data snapshot " + ds);

                user.setUser_id(
                        ds.child(userID).getValue(User.class).getUser_id()
                );

                user.setUsername(
                        ds.child(userID).getValue(User.class).getUsername()
                );

                user.setEmail(
                        ds.child(userID).getValue(User.class).getEmail()
                );

                user.setPhone_number(
                        ds.child(userID).getValue(User.class).getPhone_number()
                );
            }
        }

        return new UserSettings(user, settings);
    }
}
