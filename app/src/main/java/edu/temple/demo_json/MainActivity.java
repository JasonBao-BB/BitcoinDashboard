package edu.temple.demo_json;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;



public class MainActivity extends Activity {

    BlockNavFragment block;
    FragmentManager fm;
    BlockChar graph;
    PriceFragment priceFragment;
    ActionBar actionBar;
    BlockAddress blockAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getFragmentManager();
        actionBar = getActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentTransaction transaction = fm.beginTransaction();
        switch (item.getItemId()) {
            case R.id.newBlock:
                block = new BlockNavFragment();
                transaction.replace(R.id.fragment_block,block).commit();
                actionBar.setTitle("Block Info");
                return true;

            case R.id.price:
                priceFragment = new PriceFragment();
                transaction.replace(R.id.fragment_block, priceFragment).addToBackStack("1").commit();
                //fm.executePendingTransactions();
                priceFragment.getPriceData();
                actionBar.setTitle("Block Price");
                return true;

            case R.id.graph:
                graph = new BlockChar();
                transaction.replace(R.id.fragment_block,graph).commit();
                //fm.executePendingTransactions();
                actionBar.setTitle("Block Graph");
                return true;

            case R.id.address:
                blockAddress = new BlockAddress();
                transaction.replace(R.id.fragment_block,blockAddress).commit();
                actionBar.setTitle("Block Address");
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }
    }
}

