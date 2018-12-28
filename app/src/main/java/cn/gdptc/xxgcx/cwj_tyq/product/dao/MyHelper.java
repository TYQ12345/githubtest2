package cn.gdptc.xxgcx.cwj_tyq.product.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//创建数据库
public class MyHelper extends SQLiteOpenHelper {
     public MyHelper(Context context){
         super(context,"itcast.db",null,2);
     }
    
     public void onCreate(SQLiteDatabase db){
         System.out.println("onCreate");
         db.execSQL("CREATE TABLE account (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                 "name VARCHAR(20)," +
                 "balance INTEGER)");
     }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("onUpgrade");
    }


}
