package com.example.fianxeka.tescalc;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MainLogic logic = new MainLogic();
    TextView result, formula;
    private String strResult = "", strFormula="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //removenotifybar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.txtResult);
        formula = (TextView) findViewById(R.id.formula);
        if (savedInstanceState != null){
            strResult = savedInstanceState.getString("result");
            strFormula = savedInstanceState.getString("formula");
            result.setText(savedInstanceState.getString("result"));
            formula.setText(savedInstanceState.getString("formula"));
        }

    }

    //tohandle operator + : - x
    public void operatorClick(char opr){
        if (logic.cekOperator(strResult)){
            strResult = strResult.substring(0,strResult.length()-1)+opr;
            result.setText(strResult);
        }
        else{
            result.setText(strResult+=opr);
        }
    }

    public void showResult(){
        String newResult = logic.getResult(result.getText().toString());
        if (!logic.cantZero && newResult!="min"){
            formula.setText(strResult);
            strResult = newResult;
            result.setText(strResult);
        }else{
            if (newResult == "min"){
                showNotif("Equal Is Minus");
            }
            else{
                showNotif("Cannot Mod Zero Number");
                logic.cantZero = false;
            }

        }

    }
    public void showNotif(String Message){
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }
    //Tohandle Button Click
    public void btnCLick(View view) {
        switch (view.getId()) {
            case R.id.nol:
                if (!logic.cekOperator(strResult) && strResult!=""){
                    result.setText(strResult+="0");
                }
                break;
            case R.id.satu:
                result.setText(strResult+="1");
                break;
            case R.id.dua:
                result.setText(strResult+="2");
                break;
            case R.id.tiga:
                result.setText(strResult+="3");
                break;
            case R.id.empat:
                result.setText(strResult+="4");
                break;
            case R.id.lima:
                result.setText(strResult+="5");
                break;
            case R.id.enam:
                result.setText(strResult+="6");
                break;
            case R.id.tujuh:
                result.setText(strResult+="7");
                break;
            case R.id.delapan:
                result.setText(strResult+="8");
                break;
            case R.id.sembilan:
                result.setText(strResult+="9");
                break;
            case R.id.hurufA:
                result.setText(strResult+="A");
                break;
            case R.id.hurufB:
                result.setText(strResult+="B");
                break;
            case R.id.hurufC:
                result.setText(strResult+="C");
                break;
            case R.id.hurufD:
                result.setText(strResult+="D");
                break;
            case R.id.hurufE:
                result.setText(strResult+="E");
                break;
            case R.id.hurufF:
                result.setText(strResult+="F");
                break;
            case R.id.hurufG:
                result.setText(strResult+="G");
                break;
            case R.id.hurufH:
                result.setText(strResult+="H");
                break;
            case R.id.plus:
                if (strResult!=""){
                    operatorClick('+');
                }
                break;
            case R.id.minus:
                if (strResult!=""){
                    operatorClick('-');
                }else{
                    showNotif("Only Positive Number");
                }
                break;
            case R.id.mod:
                if (strResult!=""){
                    operatorClick('%');
                }else{
                    showNotif("Please, Input The Number");
                }
                break;
            case R.id.kali:
                if (strResult!=""){
                    operatorClick('x');
                }else{
                    showNotif("Please, Input The Number");
                }
                break;
            case R.id.equal:
                if (strResult.length() >= 3 && !logic.cekOperator(strResult)){
                    showResult();
                }
                break;
            case R.id.clear:
                result.setText(strResult = "");
                formula.setText(strFormula = "");
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("result", (String) result.getText());
        savedInstanceState.putString("formula", (String) formula.getText());
    }


    /*@Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        strResult = savedInstanceState.getString("result");
        strFormula = savedInstanceState.getString("formula");
        result.setText(savedInstanceState.getString("result"));
        formula.setText(savedInstanceState.getString("formula"));
    }*/


}
