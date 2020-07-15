package ultramodern.activity.besha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class Budget extends AppCompatActivity implements View.OnClickListener {

    Button addbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        addbutton= findViewById(R.id.addbudget);
        addbutton.setOnClickListener(this);

        BottomNavigationView bottomnav = findViewById(R.id.bottomnav);
        ViewPager viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        BudgetTabsAdapter tabsAdapter = new BudgetTabsAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(tabsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.wallet:
                        Toast.makeText(getApplicationContext(),"My Wallet",Toast.LENGTH_LONG).show();
                        break;

                    case R.id.piechart:
                        Intent intent = new Intent(getApplicationContext(),Budget.class);
                        startActivity(intent);
                        break;

                    case R.id.groups:
                        Toast.makeText(getApplicationContext(), "Groups", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });


    }

    @Override
    public void onClick(View view) {
        if (view==addbutton){
            Intent intent=new Intent(getApplicationContext(),NewBudget.class);
            startActivity(intent);
        }

    }
}