package ordonez.roger.bitacoraactivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class BitacoraActivity extends AppCompatActivity {
    private static final String FILENAME = "data.txt";
    private static final int MAX_BYTES = 100000;
    private ArrayList<BitacoraItem> items;
    private ListView list;
    private AdapterBitacoraItem adapter;
    private Button buto;
    private TextView textEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bitacora);
        items = new ArrayList<>();
        //PopulateItem("soc jo","12:25","12/12/2018");

       // PopulateItem("text", GetCurrentTime(),GetCurrentDiaMesAny());
        //PopulateItem("hola que tal",GetCurrentTime(),GetCurrentDiaMesAny());

        if (!readItemList()) {
            Toast.makeText(this, "Afegeix continguts a la Bitacora", Toast.LENGTH_SHORT).show();
        }
        list = findViewById(R.id.EditableID);
        adapter = new AdapterBitacoraItem(this,R.layout.activity_bitacor_item, items);
        list.setAdapter(adapter);

        buto=findViewById(R.id.AddId);
        textEvent=findViewById(R.id.EditText_event);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tasca = items.get(position).getTasca();

                Intent intent = new Intent(parent.getContext(),EditTextActivity.class);
                intent.putExtra("InText",tasca);
                intent.putExtra("index",position);
                startActivityForResult(intent,0);

            }
        });
        list.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                String tasca = items.get(position).getTasca();
                AlertDialog.Builder alertbuilder = new AlertDialog.Builder(view.getContext());

                alertbuilder.setMessage("Vols esborrar: "+tasca );
                alertbuilder.setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        items.remove(position);
                        adapter.notifyDataSetChanged();
                        dialog.cancel();
                    }
                });
                alertbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertbuilder.create().show();


                return false;
            }
        });

    }

    public void PopulateItem(String tasca,String hora,String diamesany){
        items.add(new BitacoraItem(tasca,hora,diamesany));
    }
    public String GetCurrentTime(){
        String a="";
        Calendar calendar = new GregorianCalendar();
        a=Integer.toString(calendar.get(Calendar.HOUR_OF_DAY))+":"+Integer.toString(calendar.get(Calendar.MINUTE));
        return a;
    }
    public String GetCurrentDiaMesAny(){
        String a="";
        Calendar calendar = new GregorianCalendar();
        a=
                 Integer.toString(calendar.get(Calendar.DAY_OF_WEEK))
                         +"/"
                +Integer.toString(calendar.get(Calendar.MONTH)+1)
                         +"/"
                +Integer.toString(calendar.get(Calendar.YEAR))
        ;//day/month/year format
        return a;
    }


    public void AddEventButtonPressed(View view) {
        String T_event =  textEvent.getText().toString();
        if(!T_event.isEmpty()){
            PopulateItem(T_event,GetCurrentTime(),GetCurrentDiaMesAny());
            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(this, R.string.Alerta_no_event, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String message;
        if(resultCode==RESULT_OK){
            int pos =data.getIntExtra("index",-1);
            if(pos>-1) {
                String newText = data.getStringExtra("result");
                items.get(pos).setTasca(newText);
                adapter.notifyDataSetChanged();
            }
        }
        if(resultCode==23){
            //do nothing because cancelled
            Toast.makeText(this, R.string.cancelled, Toast.LENGTH_SHORT).show();
        }
    }
    private boolean readFile() {

        return true;
    }
    @Override
    protected void onStop() {
        super.onStop();
        writeItemList();
    }

    private void writeItemList() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            for (BitacoraItem item : items) {
                String line = String.format("%s;%s;%s\n", item.getTasca(), item.getHora(), item.getDiaMesAny());
                fos.write(line.getBytes());
            }
            fos.close();
        }
        catch (FileNotFoundException e) {

        }
        catch (IOException e) {
            Toast.makeText(this, "No puc escriure el fitxer", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean readItemList() {
        items = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            byte[] buffer = new byte[MAX_BYTES];
            int nread = fis.read(buffer);
            if (nread > 0) {
                String content = new String(buffer, 0, nread);
                String[] lines = content.split("\n");
                for (String line : lines) {
                    if (!line.isEmpty()) {
                        String[] parts = line.split(";");
                        PopulateItem(parts[0],parts[1],parts[2]);
                    }
                }
            }
            fis.close();
            return true;
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            Toast.makeText(this, "No puc llegir el fitxer", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
