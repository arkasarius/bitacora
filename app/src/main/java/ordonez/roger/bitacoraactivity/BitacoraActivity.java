package ordonez.roger.bitacoraactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BitacoraActivity extends AppCompatActivity {
    private ArrayList<BitacoraItem> items;
    private ListView list;
    private AdapterBitacoraItem adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitacora);
        items = new ArrayList<>();
        //PopulateItem("soc jo","12:25","12/12/2018");

        PopulateItem("text", GetCurrentTime(),GetCurrentDiaMesAny());
        list = findViewById(R.id.EditableID);
        adapter = new AdapterBitacoraItem(this,R.layout.activity_bitacor_item, items);
        list.setAdapter(adapter);
        //TODO:afegir items a la llista
        //TODO: omplir el adapter amb la llista
        //TODO: crear el activity nou per editar
        //TODO: funcionalitats
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
}
