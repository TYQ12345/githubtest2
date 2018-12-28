package cn.gdptc.xxgcx.hwh.githubtest;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {
    private List<Account> list;
    private AccountDao dao;
    private EditText nameET;
    private EditText balanceET;
    private MyAdapter adapter = new MyAdapter();
    private ListView accountLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        dao = new AccountDao(this);
        list = dao.queryAll();
        accountLV.setAdapter((ListAdapter) adapter);
    }

    private void initView() {
        accountLV = findViewById(R.id.accountLV);
        nameET = findViewById(R.id.nameET);
        balanceET = findViewById(R.id.balanceET);
        accountLV.setOnItemClickListener(new MyOnItemClickListener());
    }


    public void add(View v) {
        String name = nameET.getText().toString().trim();
        String balance = balanceET.getText().toString().trim();
        Account a = new Account(name, balance.equals("") ? 0 :
                Integer.parseInt(balance));
        dao.insert(a);
        list.add(a);
        adapter.notifyDataSetChanged();
        accountLV.setSelection(accountLV.getCount() - 1);
        nameET.setText("");
        balanceET.setText("");
    }

    private void MyAdapter extends BaseAdapter{
        public int getCount(){
        return list.size();
        }
        private Object getItem ( int position){
            return list.get(position);
        }

        private Long getItemId( int position){
                return position;
        }
    }

    public View getView (int position,View convertView,ViewGroup parent){
        View item=convertView!=null?convertView:View.inflate(
                getApplicationContext(),R.layout.item,null);
        TextView idTV=item.findViewById(R.id.idTV);
        TextView nameTV=(TextView) item.findViewById(R.id.nameTV);
        TextView balanceTV=(TextView) item.findViewById(R.id.balanceTV);

        final Account a=list.get(position);

        idTV.setText(a.getId()+"");
        nameTV.setText(a.getName());
        balanceTV.setText(a.getBalance()+"");
        ImageView upIV=(ImageView) item.findViewById(R.id.upIV);
        ImageView downTV=(ImageView) item.findViewById(R.id.downIV);
        ImageView deleteIV=(ImageView) item.findViewById(R.id.deleteIT);

        upIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              a.setBalance(a.getBalance()+1);
              notifyDataSetChanged();
              dao.update(a);
            }
        });
        dowmIV.setOnClickListener(new View.OnClickListener(){
            public Void onClick(View v) {
                a.setBalance(a.getBalance() - 1);
                notifyDataSetChanged();
                dao.update(a);
            }
        });
        deleteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.content.DialogInterface.OnClickListener listener=
                        new android.content.DialogInterface.OnClickListener(){
                    public void  onClick(DialogInterface dialog,int which){
                        list.remove(a);
                        dao.delete(a.getId());
                        notifyDataSetChanged();
                    }
                };
                Builder builder=new Builder(MainActivity.this);
                builder.setTitle("确定要删除吗？");
                builder.setpositiveButton("确定",listener);
                builder.setNegativeButton("取消",null);

            }
        });
        return item;
    }





        private class MyOnItemClickListener implements AdapterView.OnItemClickListener {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Account a = (Account) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), a.toString(),
                        Toast.LENGTH_SHORT);
            }
        }
    }
