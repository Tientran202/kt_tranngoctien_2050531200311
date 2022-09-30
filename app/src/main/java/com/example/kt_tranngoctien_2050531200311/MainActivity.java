package com.example.kt_tranngoctien_2050531200311;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvMonAn ;
    ArrayList<monAn> arrayMonAn;
    monanadapter adapter;
    Button xoamon;
    int vitri = -1,vitrixoa=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        adapter = new monanadapter(this,R.layout.dongmonan,arrayMonAn);

        lvMonAn.setAdapter(adapter);

        lvMonAn.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                xacNhanXoa(i);
                return false;
            }
        });


        //xoa mon an
        xoamon =(Button) findViewById(R.id.xoa);
        xoamon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vitrixoa==-1)
                {
                    Toast.makeText(MainActivity.this, "Hãy giữ vào món cần xóa rồi nhấn xóa", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Đã xóa món "+arrayMonAn.get(vitrixoa).getTen(), Toast.LENGTH_SHORT).show();
                    arrayMonAn.remove(vitrixoa);
                    adapter.notifyDataSetChanged();
                    vitrixoa=-1;
                }

            }
        });

        lvMonAn.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Nhấn xóa để xóa món " + arrayMonAn.get(i).getTen(), Toast.LENGTH_SHORT).show();
                vitrixoa = i;
                return false;
            }
        });
    }

    public void anhXa(){
        lvMonAn = (ListView) findViewById(R.id.lv_monAn);
        arrayMonAn= new ArrayList<>();
        arrayMonAn.add(new monAn("Bún đậu mắm tôm","30k",R.drawable.bundau));
        arrayMonAn.add(new monAn("Bún thịt nướng","30k",R.drawable.bunthichnuong));
        arrayMonAn.add(new monAn("Chè","20k ",R.drawable.che));
        arrayMonAn.add(new monAn("Lẩu nướng","50k",R.drawable.launuong));
        arrayMonAn.add(new monAn("Lẩu thái","50k",R.drawable.lauthai));
        arrayMonAn.add(new monAn("Mì cay","50k",R.drawable.micay));
        arrayMonAn.add(new monAn("Phở","50k",R.drawable.pho));
        arrayMonAn.add(new monAn("Tiết canh","20k",R.drawable.tietcanh));

    }
    private void xacNhanXoa(int xoa){
        AlertDialog.Builder alerdialog = new AlertDialog.Builder(this);
        alerdialog.setTitle("Thông báo");
        alerdialog.setTitle("Bạn muốn xóa "+arrayMonAn.get(xoa).getMota()+" không!!");
        alerdialog.setPositiveButton("Xác nhận xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Đã xóa món "+ arrayMonAn.get(xoa).getTen(), Toast.LENGTH_SHORT).show();
                arrayMonAn.remove(xoa);
                adapter.notifyDataSetChanged();

            }
        });
        alerdialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alerdialog.setIcon(R.mipmap.ic_launcher);
        alerdialog.show();

    }
}