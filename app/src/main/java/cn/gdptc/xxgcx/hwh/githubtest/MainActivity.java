package cn.gdptc.xxgcx.hwh.githubtest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {
    private List<Account> list;
    private AccountDao dao;
    private EditText nameET;
    private EditText balanceET;
    private MyAdapter adapter;
    private ListView accountLV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        dao=new AccountDao(this);
        list=dao.queryAll();
        adapter=new MyAdapter();
        accountLV.setAdapter((ListAdapter)adapter);
    }

    private void initView() {
        accountLV=findViewById(R.id.accountLV);
        nameET=findViewById(R.id.nameET);
        balanceET=findViewById(R.id.balanceET);
        accountLV.setOnItemClickListener(new MyOnItemClickListener());
    }


    public void add(View v) {
        String name=nameET.getText().toString().trim();
        String balance=balanceET.getText().toString().trim();

    }


    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Account a= (Account) parent.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(),a.toString(),
                    Toast.LENGTH_SHORT);
        }
    }
}
