package com.azurespot.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorMain extends Activity {
	
	private float numberBefore;
	private String operator;
	private EditText screen;
	private ButtonClickListener btnclick = new ButtonClickListener();
			

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator_main);
		
		// initialize your text field
		screen = (EditText) findViewById(R.id.number_field);
		
		int idList[] = { R.id.button_add, R.id.button_c, R.id.button_dot,
				R.id.button_divide, R.id.button8, R.id.button_equals, R.id.button5,
				R.id.button4, R.id.button_multiply, R.id.button9, R.id.button1,
				R.id.button7, R.id.button6, R.id.button_subtract, R.id.button3,
				R.id.button2, R.id.button0 };
		
		// put listeners on each button
		for (int id : idList) {
			View v = (View) findViewById(id);
			v.setOnClickListener(btnclick);
		}
		
	} // end onCreate()
	
	// copies the number before the operator chosen, then sets text to 0
	public void mMath(String str) {
		numberBefore = Float.parseFloat(screen.getText().toString());
		operator = str;
		screen.setText("0");
	}
	
	// if nothing in the text field, set to empty string, then add each number pressed side-by-side
	public void getKeyBoardNum(String str) {
		String screenCurrent = screen.getText().toString();
		if (screenCurrent.equals("0"))
			screenCurrent = "";
		screenCurrent = screenCurrent + str;
		screen.setText(screenCurrent);
	}
	
	// captures text after the operator clicked, then performs math with before and after numbers
	public void mResult() {
		Float numberAfter = Float.parseFloat(screen.getText().toString());
		// initialize result
		Float result = 0f;
		if (operator.equals("+")) {
			result = numberBefore + numberAfter;
		} else if (operator.equals("-")) {
			result = numberBefore - numberAfter;
		} else if (operator.equals("/")) {
			result = numberBefore / numberAfter;
		} else if (operator.equals("*")) {
			result = numberBefore * numberAfter;
		}
		screen.setText(String.valueOf(result));
	}
	
	private class ButtonClickListener implements
	android.view.View.OnClickListener {

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button_c:
				screen.setText("0");
				numberBefore = 0;
				operator = "";
				break;
			case R.id.button_divide:
				mMath("/");
				break;
			case R.id.button_add:
				mMath("+");
				break;
			case R.id.button_subtract:
				mMath("-");
				break;
			case R.id.button_multiply:
				mMath("*");
				break;
			case R.id.button_equals:
				mResult();
				break;
			default:
				String numb = ((Button) v).getText().toString();
				getKeyBoardNum(numb);
				break;
			}
		}
	
	}
	
}
