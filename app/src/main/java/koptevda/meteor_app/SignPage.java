package koptevda.meteor_app;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignPage extends AppItem {

    private EditText login_text, password_text;

    public static boolean SIGNED = false;

    public SignPage() {
        fragment_name = "Sign in";
        MainActivity.CURRENT_PAGE = MainActivity.PAGE_TYPE.PAGE_SIGN_IN;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.content_sign, container, false);

        // Initialize elements
        login_text = (EditText)rootView.findViewById(R.id.login_text);
        password_text = (EditText)rootView.findViewById(R.id.password_text);

        // Set listener for show-password-checkbox
        ((CheckBox)rootView.findViewById(R.id.show_password_chb))
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (!b) {
                            password_text.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        }
                        else {
                            password_text.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        }

                        Log.v("MY_APP", b ? "Password is visible" : "Password is hidden");
                    }
                });

        // Set listener to "Sign in" button
        ((Button)rootView.findViewById(R.id.sign_in_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password_text == null || password_text.length() == 0 ||
                        login_text == null || login_text.length() == 0) {
                    Toast.makeText(getContext(), "Please, fill all information", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    // Write some code for signing-in here
                    // Lets just imitate some wait-time, to let user realise, what happened
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Say hello to user
                    Toast.makeText(getContext(), "Hello, " + login_text.getText(), Toast.LENGTH_SHORT)
                            .show();
                    // Jump to main page (just for example)
                    // This is very strange way to change fragment...
                    SIGNED = true;
                    //item.setVisibility(View.INVISIBLE);
                    jump_to(R.id.main_page, new MainPage());
                }
            }
        });
        return rootView;
    }
}
