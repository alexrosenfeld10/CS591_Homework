package com.example.alexrosenfeld10.w6_p2;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */

//This will get inflated up top.
public class ControlFragment extends Fragment {


    private EditText edtSendMessage;
    private Button btnSendMessage;

    public ControlFragment() {  //todo, why?
        // Required empty public constructor
    }

//*** MESSAGE PASSING MECHANISM ***//
//Need to create an interface to ensure message passing works between fragments.
//This interface, as with all interfaces serves as a contract.  It guarantees
//Since the MainActivity will implement this, we are guaranteed to find a sendMessage
//routine there!
    public interface ControlFragmentListener {            //this is just an interface definition.
        public void sendMessage(String msg); //it could live in its own file.  placed here for convenience.
    }

    ControlFragmentListener CFL;  //Future reference to an object that implements ControlFragmentListener,
                                  //Question: Who holds the reference?  Answer: ____________
//*** MESSAGE PASSING MECHANISM ***//


    //onAttach gets called when fragment attaches to Main Activity.  This is the right time to instantiate
    //our ControlFragmentListener, why?  Because we know the Main Activity was successfully created and hooked.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        CFL = (ControlFragmentListener) context;  //context is a handle to the main activity, let's bind it to our interface.
    }


//NOTE:
//This old onAttach, still works, but is deprecated,
//better to use the newer one below, which passes a context object, which can also be typecast into an Activity Object.
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        CFL = (ControlFragmentListener) activity;
//    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_control, container, false);  //this needs to be separated from return statement,
                                                                                  //so we can refer to the views objects before passing view to Activity.

        edtSendMessage = (EditText) view.findViewById(R.id.edtSendMessage);
        btnSendMessage = (Button) view.findViewById(R.id.btnSendMessage);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CFL.sendMessage(edtSendMessage.getText().toString());  //CFL is a handle to our MainActivity, we are sending it our message text.
            }
        });

        return view;
    }

}
