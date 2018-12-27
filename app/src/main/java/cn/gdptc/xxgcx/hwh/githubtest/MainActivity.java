package cn.gdptc.xxgcx.hwh.githubtest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

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


    }
}
