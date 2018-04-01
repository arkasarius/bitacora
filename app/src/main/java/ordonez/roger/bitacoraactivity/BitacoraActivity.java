package ordonez.roger.bitacoraactivity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BitacoraActivity extends AppCompatActivity {
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

        PopulateItem("text", GetCurrentTime(),GetCurrentDiaMesAny());
        PopulateItem("hola que tal",GetCurrentTime(),GetCurrentDiaMesAny());
        list = findViewById(R.id.EditableID);
        adapter = new AdapterBitacoraItem(this,R.layout.activity_bitacor_item, items);
        list.setAdapter(adapter);

        buto=findViewById(R.id.AddId);
        textEvent=findViewById(R.id.EditText_event);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tasca = items.get(position).getTasca();
                //TODO:editTextActivity

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
                +Integer.toString(calendar.get(Calendar.MONTH))
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
}
