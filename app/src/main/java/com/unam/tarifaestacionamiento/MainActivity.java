package com.unam.tarifaestacionamiento;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button entradaHora, salidaHora, botonCalcular;
    EditText txtEntrada, txtSalida, editHora, editFraccion;
    TextView txtResultado;
    private int horas1, horas2, minutos1, minutos2;
    double primerahora, fraccion, he, me, hs, ms, resultado, temp, he1, hs1, mte, mts, minreal, cte, fracciones, temp2, temp3, fracciones2;
    int w, x, y, z, redondeo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entradaHora=(Button)findViewById(R.id.entradaHora);
        salidaHora=(Button)findViewById(R.id.salidaHora);
        txtEntrada=(EditText)findViewById(R.id.txtEntrada);
        txtSalida=(EditText)findViewById(R.id.txtSalida);
        editHora=(EditText)findViewById(R.id.editHora);
        editFraccion=(EditText)findViewById(R.id.editFraccion);
        botonCalcular=(Button)findViewById(R.id.botonCalcular);
        txtResultado=(TextView)findViewById(R.id.txtResultado);
        entradaHora.setOnClickListener(this);
        salidaHora.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==entradaHora) {
            final Calendar c= Calendar.getInstance();
            horas1=c.get(Calendar.HOUR_OF_DAY);
            minutos1=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    txtEntrada.setText(hourOfDay+":"+minute);
                    w=hourOfDay;
                    x=minute;
                }
            },horas1,minutos1,false);
            timePickerDialog.show();
        }
        if (v==salidaHora) {
            final Calendar c= Calendar.getInstance();
            horas2=c.get(Calendar.HOUR_OF_DAY);
            minutos2=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    txtSalida.setText(hourOfDay+":"+minute);
                    y=hourOfDay;
                    z=minute;
                }
            },horas2,minutos2,false);
            timePickerDialog.show();
        }
        botonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primerahora=Double.parseDouble(editHora.getText().toString());
                fraccion=Double.parseDouble(editFraccion.getText().toString());
                he=w;
                me=x;
                hs=y;
                ms=z;
                temp= hs-he;
                if (temp>=0){
                    he1=he*60;
                    hs1=hs*60;
                    mte=he1+me;
                    mts=hs1+ms;
                    minreal= mts-mte;

                    cte=15;
                    fracciones=minreal/cte;
                    temp2=minreal%cte;

                    if (temp2==0){
                        if (fracciones<=4){
                            resultado=primerahora;
                            txtResultado.setText("El total a pagar es: $"+resultado);
                        }
                        if (fracciones>4){
                            fracciones2=fracciones-4;
                            temp3= fracciones2 * fraccion;
                            resultado=primerahora+temp3;
                            txtResultado.setText("El total a pagar es: $"+resultado);
                        }
                    }
                    if (temp2!=0){
                        if (fracciones<=4){
                            resultado=primerahora;
                            txtResultado.setText("El total a pagar es: $"+resultado);
                        }
                        if (fracciones>4) {
                            redondeo=(int) fracciones;
                            fracciones2=redondeo-4;
                            temp3= fracciones2 * fraccion;
                            resultado=primerahora+temp3+fraccion;
                            txtResultado.setText("El total a pagar es: $"+resultado);
                        }
                    }
                }
                if (temp<0) {
                    he1=he*60;
                    hs1=hs*60;
                    mte=he1+me;
                    mts=hs1+ms;
                    minreal= (mts-mte)+1440;

                    cte=15;
                    fracciones=minreal/cte;
                    temp2=minreal%cte;

                    if (temp2==0){
                        if (fracciones<=4){
                            resultado=primerahora;
                            txtResultado.setText("El total a pagar es: $"+resultado);
                        }
                        if (fracciones>4){
                            fracciones2=fracciones-4;
                            temp3= fracciones2 * fraccion;
                            resultado=primerahora+temp3;
                            txtResultado.setText("El total a pagar es: $"+resultado);
                        }
                    }
                    if (temp2!=0){
                        if (fracciones<=4){
                            resultado=primerahora;
                            txtResultado.setText("El total a pagar es: $"+resultado);
                        }
                        if (fracciones>4) {
                            redondeo=(int) fracciones;
                            fracciones2=redondeo-4;
                            temp3= fracciones2 * fraccion;
                            resultado=primerahora+temp3+fraccion;
                            txtResultado.setText("El total a pagar es: $"+resultado);
                        }
                    }
                }
            }
        });
    }
}
