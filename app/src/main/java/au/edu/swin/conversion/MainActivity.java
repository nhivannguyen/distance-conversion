package au.edu.swin.conversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getInputNum();
    }

    private void getInputNum() {
        Button convertBut = (Button) findViewById(R.id.button);
        convertBut.setOnClickListener(convertButClick);
    }

    private Button.OnClickListener convertButClick = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            EditText convertNum = (EditText) findViewById(R.id.convertNum);
            double toConvert = Integer.parseInt(convertNum.getText().toString());
            calculateConvert(toConvert);
        }
    };

    private void calculateConvert(double toConvert) {
        CheckBox inch = (CheckBox) findViewById(R.id.inch);
        CheckBox foot = (CheckBox) findViewById(R.id.foot);
        CheckBox mile = (CheckBox) findViewById(R.id.mile);
        CheckBox met  = (CheckBox) findViewById(R.id.met);
        CheckBox cm   = (CheckBox) findViewById(R.id.cm);
        double result = 1;

        if ( (!(inch.isChecked() || foot.isChecked() || mile.isChecked()))
            || (inch.isChecked() && foot.isChecked() && mile.isChecked())
            || (inch.isChecked() && foot.isChecked())
            || (inch.isChecked() && mile.isChecked())
            || (foot.isChecked() && mile.isChecked())) {
            inch.setChecked(true);
            foot.setChecked(false);
            mile.setChecked(false);
        }
        if (cm.isChecked() && met.isChecked()) {
            cm.setChecked(true);
            met.setChecked(false);
        }
        if (inch.isChecked()) {
            result = toConvert * 2.54;
        }
        if (foot.isChecked()) {
            result = toConvert * 12 * 2.54;
        }
        if (mile.isChecked()) {
            result = toConvert * 5280 * 12 * 2.54;
        }
        if (met.isChecked()) {
            result = result / 100;
        }
        TextView displayResult = (TextView) findViewById(R.id.result);
        displayResult.setText(Double.toString(result));
    }
}
