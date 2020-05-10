package com.example.memall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ShowAllCategoriesDialog.SelectCategory  {
    private static final String TAG = "MainActivity";
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private FrameLayout frameLayout;
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar
        ,R.string.drawer_open,R.string.drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new MainFragment());
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cart:
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.categories:
                ShowAllCategoriesDialog showAllCategoriesDialog = new ShowAllCategoriesDialog();
                showAllCategoriesDialog.show(getSupportFragmentManager(), "all categories");
                break;
            case R.id.about:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("About")
                        .setMessage("Developed by Saumya \n\t checkout lol.org for more\n\t information")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO: show url
                            }
                        });
                builder.create().show();
                break;
            case R.id.terms:
                AlertDialog.Builder termsBuilder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Terms of use")
                        .setMessage("No extra Terms\n Enjoy :)")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                termsBuilder.create().show();
                break;
            case R.id.licenses:
                LicensesDialog licensesDialog = new LicensesDialog();
                licensesDialog.show(getSupportFragmentManager(),"licences dialog");
                break;
            default:
                break;
        }
        return false;
    }

    private void initView(){
        Log.d(TAG, "initView: started");
        toolbar =  findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.navigationDrawer);
    }

    @Override
    public void onSelectCategoryResult(String category) {
        Log.d(TAG, "onSelectCategoryResult: category "+ category );
        Intent intent = new Intent(this, ShowItemsByCategoryActivity.class);
        intent.putExtra("category",category);
        startActivity(intent);
    }
}
