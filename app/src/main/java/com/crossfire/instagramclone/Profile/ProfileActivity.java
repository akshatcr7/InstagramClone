package com.crossfire.instagramclone.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.crossfire.instagramclone.R;
import com.crossfire.instagramclone.Utils.BottomNavigationViewHelper;
import com.crossfire.instagramclone.Utils.GridImageAdapter;
import com.crossfire.instagramclone.Utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

/**
 * @author Akshat Pandey
 * @version 1.0
 * @date 27-09-2017
 */

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 4;

    private ProgressBar mProgressBar;
    private ImageView profilePhoto;

    private Context mContext = ProfileActivity.this;
    private static final int NUM_GRID_COLUMNS = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();

/*        setupBottomNavigationView();
        setupToolbar();
        setupActivityWidgets();
        setProfileImage();

        tempGridSetup();*/
    }

    private void init(){

        Log.d(TAG, "init: Inflating Fragment " + getString(R.string.profile_fragment));

        ProfileFragment fragment = new ProfileFragment();
        FragmentTransaction trasaction = ProfileActivity.this.getSupportFragmentManager().beginTransaction();
        trasaction.replace(R.id.container,fragment);
        trasaction.addToBackStack(getString(R.string.profile_fragment));
        trasaction.commit();
    }

   /* private void tempGridSetup(){
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("https://static.pexels.com/photos/34950/pexels-photo.jpg");
        imgURLs.add("https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg");
        imgURLs.add("https://www.formula1.com/content/fom-website/en/latest/features/2017/8/gallery--the-best-images-from-belgium/_jcr_content/featureContent/manual_gallery_2/image6.img.1024.medium.jpg/1503832637685.jpg");
        imgURLs.add("https://www.w3schools.com/css/trolltunga.jpg");
        imgURLs.add("https://static.pexels.com/photos/158607/cairn-fog-mystical-background-158607.jpeg");
        imgURLs.add("https://www.bmw.com/content/dam/bmw/common/all-models/4-series/gran-coupe/2017/images-and-videos/images/BMW-4-series-gran-coupe-images-and-videos-1920x1200-11.jpg.asset.1487328156349.jpg");
        imgURLs.add("https://i.pinimg.com/736x/8b/ee/83/8bee831542fca26a82f9687dc13a0c0e--galaxy-images-pink-galaxy.jpg");
        imgURLs.add("https://thecompleteherbalguide.com/wp-content/uploads/2016/08/wellness2.jpg");
        imgURLs.add("http://www.fitgirlcode.com/wp-content/uploads/2015/04/foodlist9.jpg");
        imgURLs.add("https://www.irishtimes.com/polopoly_fs/1.2482460.1451671625!/image/image.jpg_gen/derivatives/landscape_685/image.jpg");
        imgURLs.add("https://cdn.thinglink.me/api/image/741337156915560448/1240/10/scaletowidth");
        imgURLs.add("http://www.gettyimages.com/gi-resources/images/frontdoor/creative/LonelyPlanetRM/Slide_1.jpg");

        setupImageGrid(imgURLs);
    }

    private void setupImageGrid(ArrayList<String> imgURLs){
        GridView gridView = (GridView) findViewById(R.id.gridView);

        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth/NUM_GRID_COLUMNS;
        gridView.setColumnWidth(imageWidth);

        GridImageAdapter adapter = new GridImageAdapter(mContext,R.layout.layout_grid_imageview,"",imgURLs);
        gridView.setAdapter(adapter);
    }

    private void setProfileImage() {

        Log.d(TAG, "setProfileImage: Setting up the profile Image");
        String imgURL="www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge/public/article_images/2016/08/ac-lloyd.jpg?itok=bb72IeLf";
        UniversalImageLoader.setImage(imgURL,profilePhoto,mProgressBar,"https://");
    }

    private void setupActivityWidgets() {
        mProgressBar = (ProgressBar) findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        profilePhoto= (ImageView) findViewById(R.id.profile_photo);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        ImageView profileMenu = (ImageView) findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Navigating to Accounts Settings Activity");
                Intent intent = new Intent(mContext, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });

    }

    *//**
     * Bottom NavigationView Setup
     *//*

    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up Bottom Navigation View Bar");

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
*/
}
