package com.mit.persona;


        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.AnimationUtils;
        import android.widget.ImageView;
        import android.widget.ViewFlipper;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedWriter;
        import java.io.DataOutputStream;
        import java.io.IOException;
        import java.io.OutputStream;
        import java.io.OutputStreamWriter;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class allFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public ViewFlipper v_flipper;
    View v;
    private RecyclerView myRecycleView;
    private List<Events> event = Persona.myAppDatabase.myDao().getEvents();



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private float initialX;


    private allFragment.OnFragmentInteractionListener mListener;


    int images[] = {R.drawable.slider0, R.drawable.slider1, R.drawable.slider2, R.drawable.slider3};



    public allFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all, container, false);
        v_flipper = (ViewFlipper) view.findViewById(R.id.slider);

        myRecycleView = (RecyclerView) view.findViewById(R.id.cards);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), event);
        myRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycleView.setAdapter(recyclerViewAdapter);


        for(int image: images) {
            flipperImages(image);
        }

        return view;
    }


    public void flipperImages(int image) {

        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(image);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(2000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);

    }

        public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




}
