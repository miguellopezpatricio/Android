package com.example.u2_student_miguel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnComprobar, btnVerPremios, btnCerrar;

    TextView txtMensajes; // se utilizará para mostrar diversos mensajes de la app
    TextView txtResultado; // se mostrará si ha ganado o perdido

    EditText txtEscribeNombre, txtIngresaCodigo; // se usarán para verificar datos

    ConstraintLayout layout; // propiedad para asignar una imagen nueva

    // array para imagenes
    int[]imagenes = new int[]{R.drawable.lollypop, R.drawable.cochazo, R.drawable.gominola, R.drawable.lego, R.drawable.pinunicornio};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // guardamos el constraintLayout en la propiedad creada
        layout = (ConstraintLayout)findViewById(R.id.constraintPrincipal);

        btnComprobar = (Button) findViewById(R.id.btnComprobar);
        btnVerPremios = (Button) findViewById(R.id.btnVerPremios);
        btnCerrar = (Button) findViewById(R.id.btnCerrar);

        txtEscribeNombre = (EditText)findViewById(R.id.txtEscribeNombre);
        txtIngresaCodigo = (EditText)findViewById(R.id.txtIngresaCodigo);

        txtMensajes = (TextView) findViewById(R.id.txtMensajes);
        txtResultado = (TextView) findViewById(R.id.txtResultado);


        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // primero comprobar que los campos tienen datos


                String nombre = txtEscribeNombre.getText().toString();
                String codigo = txtIngresaCodigo.getText().toString();

                if (nombre.isEmpty()) {


                    // hay que mostrar en el campo texto resultado un mensaje de error
                    // el campo nombre no puede quedar vacío
                    txtMensajes.setText(getText(R.string.nombreVacio));
                }

                // hay que mostrar en el campo texto resultado un mensaje de error
                // el campo codigo no puede quedar vacío
                if(codigo.isEmpty()){
                    txtMensajes.setText(getText(R.string.codigoVacio));

                }

                // VERIFICAMOS SI EL CODIGO ES EL CORRECTO PARA PREMIO
                if(!codigo.isEmpty() && !nombre.isEmpty()){
                    txtMensajes.setText("");
                    if(!codigo.equals(getResources().getString(R.string.codigo).toString())) {
                        txtResultado.setText(getResources().getString(R.string.perder, nombre));
                        txtResultado.setTextColor(getResources().getColor(R.color.red));
                        txtResultado.setTextSize(20);
                    }else
                        {
                        txtResultado.setText(getResources().getString(R.string.ganar, nombre));
                        txtResultado.setTextColor(getResources().getColor(R.color.green));
                        txtResultado.setTextSize(20);

                    }
                }


            }

        });



        // EVENTO CLICK BOTÓN MOSTRAR IMÁGENES RANDOM DE PREMIOS
        btnVerPremios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // tamaño del array de imágenes
                int longArray = imagenes.length;

                // generamos número aleatorio en relación al número máximo de elementos del array de imágenes
                Random random = new Random();
                int numRandom = random.nextInt(longArray);

                // Eliminamos mensaje RESULTADO
                txtResultado.setText("");
                // txtResultado.setVisibility(v.INVISIBLE);
                txtMensajes.setText("");
                // txtMensajes.setVisibility(v.INVISIBLE);


                // mostramos la imagen aleatoria
                layout.setBackground(ContextCompat.getDrawable(getApplicationContext(), imagenes[numRandom]));

            }
        });


        // CERRAR APLICACIÓN
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);

            }
        });
    }



}