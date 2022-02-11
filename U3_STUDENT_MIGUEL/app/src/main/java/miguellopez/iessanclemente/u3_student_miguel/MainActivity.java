package miguellopez.iessanclemente.u3_student_miguel;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtColor = findViewById(R.id.textView);

        Spinner spinProvincias = findViewById(R.id.spinner);


        Toast toast = Toast.makeText(MainActivity.this,"", Toast.LENGTH_LONG);
        TextView viewToast = new TextView(MainActivity.this);

        /*
         *  LISTENER SOBRE BOTÓN
         */

        EditText txtUsuario = findViewById(R.id.editText);

        CheckBox chkClear = findViewById(R.id.checkBox);

        Button btnAddClear = findViewById(R.id.button);

        btnAddClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkClear.isChecked()){
                    // si no está marcado borrar texto edittext
                    txtColor.setText("");

                } else {
                    // añadir texto EditText a TextView
                    txtColor.append( getCapsSentences(txtUsuario.getText().toString()));



                }
            }
        });




        /*
         *  LISTENER SOBRE SPINNER
         */
        spinProvincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                if(spinProvincias.getSelectedItemId() == 0 || spinProvincias.getSelectedItemId() == 1 || spinProvincias.getSelectedItemId() == 2 || spinProvincias.getSelectedItemId() == 3  ){


                    viewToast.setText(R.string.text_toast_gal);
                    toast.setView(viewToast);
                    toast.show();

                } else if(spinProvincias.getSelectedItemId() == 4){

                    viewToast.setText(R.string.text_toast_no_gal);
                    toast.setView(viewToast);
                    toast.show();


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        /*
         *  LISTENER SOBRE IMAXE
         */

        ImageView img = findViewById(R.id.imageView);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewToast.setText(R.string.img_description);
                toast.setView(viewToast);
                toast.show();
            }
        });

        /*
         *  LISTENER SOBRE RADIOGROUP
         */

        RadioGroup radioColor = findViewById(R.id.radioGroup);

        radioColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int idRadioColor = radioColor.getCheckedRadioButtonId();

                if(idRadioColor == R.id.radioBtnRed){
                    // poner TextView en rojo
                    txtColor.setTextColor(Color.RED);

                } else if (idRadioColor == R.id.radioBtnBlue){
                    // poner TextView en azul
                    txtColor.setTextColor(Color.BLUE);

                }
            }
        });


        /*
         *  LISTENER PARA INTERRUPTOR DE CRONÓMETRO
         */

        Chronometer crono = findViewById(R.id.chronometer);
        Switch interruptor = findViewById(R.id.switchCrono);

        crono.setBase(SystemClock.elapsedRealtime());



        interruptor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(interruptor.isChecked()){
                    crono.setBase(SystemClock.elapsedRealtime());

                    crono.start();
                } else{
                    crono.stop();
                    crono.setBase(SystemClock.elapsedRealtime());
                }
            }
        });

        /*
         *  LISTENER PARA CRONÓMETRO
         */

        crono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long tiempoPasado = SystemClock.elapsedRealtime() - chronometer.getBase();
                int tiempoSeg = (int) tiempoPasado/1000;

                if(tiempoSeg == 15) finish();

            }
        });



    }


    /*
     * MÉTODO PARA PRIMEIRA LETRA DE CADA PALABRA EN MAIÚSCULAS
     */


    private String getCapsSentences(String tagName) {
        String[] splits = tagName.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splits.length; i++) {
            String eachWord = splits[i];
            if (i > 0 && eachWord.length() > 0) {
                sb.append(" ");
            }
            String cap = eachWord.substring(0, 1).toUpperCase()
                    + eachWord.substring(1);
            sb.append(cap);
        }
        return sb.toString();
    }
}