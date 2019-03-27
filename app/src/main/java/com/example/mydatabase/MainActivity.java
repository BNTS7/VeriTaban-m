package com.example.mydatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hata yakalınırsa programın devam etmesini saglıyor
        try {
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Engineers",MODE_PRIVATE,null);
            //SQL SORGUSU YAZIYORUZ.EGER engineers tablo yoksa olustur
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS engineers ( name VARCHAR , age INT(2))");
            //ilk girdi
            //myDatabase.execSQL("INSERT INTO engineers (name, age) VALUES ('Busra', 25)");
            //myDatabase.execSQL("INSERT INTO engineers (name, age) VALUES ('Sezer', 26)");
           // myDatabase.execSQL("INSERT INTO engineers (name, age) VALUES ('Beyza', 22)");
           // myDatabase.execSQL("INSERT INTO engineers (name, age) VALUES ('Berra', 20)");

            //data silme
            //myDatabase.execSQL("DELETE FROM engineers WHERE name LIKE '%ra' ");

            //veri güncelleme
            myDatabase.execSQL("UPDATE engineers SET age = 30 WHERE name = 'Beyza'");
            
            //verileri toplamak için
            Cursor cursor = myDatabase.rawQuery("SELECT * FROM engineers",null);

            //filtreleme
            //Cursor cursor = myDatabase.rawQuery("SELECT * FROM engineers WHERE age >24 ",null);

            //aynı anda birden fazla filtreleme
            // Cursor cursor = myDatabase.rawQuery("SELECT * FROM engineers WHERE age >24 AND name = 'Busra' ",null);

            //icinde a harfi bulunanları getir
            // Cursor cursor = myDatabase.rawQuery("SELECT * FROM engineers WHERE name LIKE '%a%' ",null);



            //kaçıncı kolon ve sutunda oldunu buluyor
            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");

            //cursor ile tek tek alanları dolasıp verileri getiriyor
            cursor.moveToFirst();//ilk anala cursoru tasıyoruz
            while (cursor != null){

                System.out.println("Name: "+ cursor.getString(nameIx));
                System.out.println("Age: " + cursor.getInt(ageIx));

                cursor.moveToNext();//bi sonraki satıra gec
            }

        }catch (Exception e){
            e.printStackTrace();//hatayı monitorde göster
        }


    }
}
