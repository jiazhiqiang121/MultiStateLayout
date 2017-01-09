package cn.refactor.multistatelayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import cn.refactor.multistatelayout.MultiStateLayout;

public class MainActivity extends AppCompatActivity {
    private MultiStateLayout mStateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void setupViews() {
        mStateLayout = (MultiStateLayout) findViewById(R.id.multi_state_layout);
        // setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_content:
                        mStateLayout.setState(MultiStateLayout.State.CONTENT);
                        break;
                    case R.id.menu_empty:
                        mStateLayout.setState(MultiStateLayout.State.EMPTY);
                        break;
                    case R.id.menu_loading:
                        mStateLayout.setState(MultiStateLayout.State.LOADING);
                        break;
                    case R.id.menu_error:
                        mStateLayout.setState(MultiStateLayout.State.ERROR);
                        break;
                    case R.id.menu_network_error:
                        mStateLayout.setState(MultiStateLayout.State.NETWORK_ERROR);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        View networkErrorView = mStateLayout.getNetworkErrorView();
        if (null != mStateLayout.getNetworkErrorView()) {
            networkErrorView.findViewById(R.id.btn_reload).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, getString(R.string.reload), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public void gotoSecond(View v) {
        startActivity(new Intent(this, SecondActivity.class));
    }

}
