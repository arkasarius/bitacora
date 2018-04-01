package ordonez.roger.bitacoraactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import javax.xml.transform.Result;

public class EditTextActivity extends AppCompatActivity {
    private EditText editText;
    private String InText;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        Intent intent = getIntent();
        String message = intent.getStringExtra("InText");
        editText = findViewById(R.id.EditableText);
        editText.setText(message);
        InText=message;
        position = intent.getIntExtra("index",-1);
    }

    public void Faceptar(View view) {
        Intent data = new Intent(this,BitacoraActivity.class);
        String returnText= editText.getText().toString();
        data.putExtra("result" ,returnText);
        data.putExtra("index",position);
        setResult(RESULT_OK,data);
        finish();
    }

    public void FCancelar(View view) {
        Intent data = new Intent(this,BitacoraActivity.class);
        //data.putExtra("result" ,InText);
        //data.putExtra("index",position);
        setResult(23,data);//23
        finish();
    }
}
