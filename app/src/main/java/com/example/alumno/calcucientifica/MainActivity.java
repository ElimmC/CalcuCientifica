package com.example.alumno.calcucientifica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView screen;
    Button[] nms = new Button[10];
    Button[] ops = new Button[11];
    Double resultado=0.0;
    boolean nuevaOperacion = true;
    int cach=-1;
    String operacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = findViewById(R.id.textView);
        nms[0] = findViewById(R.id.btn0);
        nms[1] = findViewById(R.id.button);
        nms[2] = findViewById(R.id.button2);
        nms[3] = findViewById(R.id.button3);
        nms[4] = findViewById(R.id.button4);
        nms[5] = findViewById(R.id.button5);
        nms[6] = findViewById(R.id.button6);
        nms[7] = findViewById(R.id.button7);
        nms[8] = findViewById(R.id.button8);
        nms[9] = findViewById(R.id.button9);
        for(int i=0;i<10;i++){
            nms[i].setOnClickListener(this);
        }
        ops[0] = findViewById(R.id.btnCE);
        ops[1] = findViewById(R.id.btnMas);
        ops[2] = findViewById(R.id.btnMul);
        ops[3] = findViewById(R.id.btnRes);
        ops[4] = findViewById(R.id.btnDiv);
        ops[5] = findViewById(R.id.btnSen);
        ops[6] = findViewById(R.id.btnCos);
        ops[7] = findViewById(R.id.btnTan);
        ops[8] = findViewById(R.id.btnRoo);
        ops[9] = findViewById(R.id.btnPow);
        ops[10] = findViewById(R.id.btnEqu);

        for(int i=0;i<11;i++){
            ops[i].setOnClickListener(this);
        }
    }
    /**
     * Gestiona las pulsaciones de teclas numéricas
     *
     * @param digito
     *            tecla pulsada
     */
    private void numeroPulsado(String digito) {
        if (screen.getText().equals("0")||nuevaOperacion) {
            screen.setText(digito);
        } else {
            screen.setText(screen.getText() + digito);
        }
        nuevaOperacion = false;
    }

    /**
     * Gestiona el gestiona las pulsaciones de teclas de operación
     *
     * @param tecla
     */
    private void operacionPulsado(String tecla) {
        if (tecla.equals("=")) {
            calcularResultado();
        } else if (tecla.equals("CE")) {
            resultado = 0.0;
            screen.setText("0");
            nuevaOperacion = true;
        } else {
            operacion = tecla;
            if ((resultado > 0) && !nuevaOperacion) {
                calcularResultado();
            } else {
                resultado = new Double(screen.getText().toString());
            }
        }

        nuevaOperacion = true;
    }
    private void calcularResultado() {
        double res0 = new Double(screen.getText().toString());
        if (operacion.equals("+")) {
            resultado += res0;
        } else if (operacion.equals("-")) {
            resultado -= res0;
        } else if (operacion.equals("/")) {
            if(res0!=0)
                resultado /= res0;
            else
                screen.setText("Math error xP");
        } else if (operacion.equals("x")) {
            resultado *= res0;
        }else if (operacion.equals("^")) {
            //Calcula el resultado a la potencia seleccionada
            resultado = Math.pow(resultado,res0);
        }else if (operacion.equals("^1/2")) {
            //calcula la raiz cuadrada
            resultado = Math.sqrt(res0);
        }else if (operacion.equals("Cos")) {
            //calcula el coseno en grados
            resultado = (Math.cos(Math.toRadians(res0)));
        }else if (operacion.equals("Sen")) {
            //calcula el seno en grados
            resultado = Math.sin(Math.toRadians(res0));
        }else if (operacion.equals("Tan")) {
            //calcula la tangente en grados
            resultado = Math.tan(Math.toRadians(res0));
        }else if (operacion.equals("ln")) {
            //calcula el logaritmo natural
            if(res0>0)
                resultado = Math.log(res0);
            else
                screen.setText("Math error xP");
        }
        if(!screen.getText().equals("Math error xP"))
            screen.setText("" + resultado);
        operacion = "";

    }

    @Override
    public void onClick(View view) {
        for (int i=0;i<10;i++){
            if(nms[i]==view){
                numeroPulsado(nms[i].getText().toString());
            }
        }
        for(int j=0;j<11;j++){
            if(ops[j]==view){
                operacionPulsado(ops[j].getText().toString());
            }
        }
    }
}
