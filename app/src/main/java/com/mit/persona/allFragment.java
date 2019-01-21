package com.mit.persona;



        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.ViewFlipper;

        import java.util.ArrayList;
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

    private RecyclerView myRecycleView0;
    private RecyclerView myRecycleView1;
    private RecyclerView myRecycleView2;
    private RecyclerView myRecycleView3;
    private RecyclerView myRecycleView4;
    private RecyclerView myRecycleView5;
    private RecyclerView myRecycleView6;
    private RecyclerView myRecycleView7;
    private RecyclerView myRecycleView8;
    private RecyclerView myRecycleView9;
    private RecyclerView myRecycleView10;
    private RecyclerView myRecycleView11;
    private RecyclerView myRecycleView12;


    private List<Events> artnCreative = Persona.myAppDatabase.myDao().getEvents("Arts and Creative Event");
    private List<Events> danceMusic = Persona.myAppDatabase.myDao().getEvents("Dance and Music Event");
    private List<Events> cultural = Persona.myAppDatabase.myDao().getEvents("Cultural Events");
    private List<Events> architecture = Persona.myAppDatabase.myDao().getEvents("Architecture");
    private List<Events> manetIod = Persona.myAppDatabase.myDao().getEvents("MANET & Institute of Design");
    private List<Events> vedicSciences = Persona.myAppDatabase.myDao().getEvents("Vedic Sciences");
    private List<Events> management = Persona.myAppDatabase.myDao().getEvents("Management & Other Events");
    private List<Events> electronics = Persona.myAppDatabase.myDao().getEvents("Electronics Engineering");
    private List<Events> civil = Persona.myAppDatabase.myDao().getEvents("Civil Engineering");
    private List<Events> mechanical = Persona.myAppDatabase.myDao().getEvents("Mechanical Engineering");
    private List<Events> aerospace = Persona.myAppDatabase.myDao().getEvents("Aerospace Engineering");
    private List<Events> cse = Persona.myAppDatabase.myDao().getEvents("Computer & IT Engineering");
    private List<Events> foodTech = Persona.myAppDatabase.myDao().getEvents("Food Technology");

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private allFragment.OnFragmentInteractionListener mListener;


    int images[] = { R.drawable.slider1, R.drawable.slider2};



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

        LinearLayoutManager layoutManager0= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager1= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager4= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager5= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager6= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager7= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager8= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager9= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager10= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager11= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager12= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        myRecycleView0 = (RecyclerView) view.findViewById(R.id.artnCreativeEvents_Cards);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), artnCreative);
        myRecycleView0.setLayoutManager(layoutManager0);
        myRecycleView0.setAdapter(recyclerViewAdapter);

        myRecycleView1 = (RecyclerView) view.findViewById(R.id.danceMusic_Cards);
        RecyclerViewAdapter recyclerViewAdapter2 = new RecyclerViewAdapter(getContext(), danceMusic);
        myRecycleView1.setLayoutManager(layoutManager1);
        myRecycleView1.setAdapter(recyclerViewAdapter2);

        myRecycleView2 = (RecyclerView) view.findViewById(R.id.cultural_Cards);
        RecyclerViewAdapter recyclerViewAdapter3 = new RecyclerViewAdapter(getContext(), cultural);
        myRecycleView2.setLayoutManager(layoutManager2);
        myRecycleView2.setAdapter(recyclerViewAdapter3);

        myRecycleView3 = (RecyclerView) view.findViewById(R.id.Architecture_Cards);
        RecyclerViewAdapter recyclerViewAdapter4 = new RecyclerViewAdapter(getContext(), architecture);
        myRecycleView3.setLayoutManager(layoutManager3);
        myRecycleView3.setAdapter(recyclerViewAdapter4);

        myRecycleView4 = (RecyclerView) view.findViewById(R.id.manet_Cards);
        RecyclerViewAdapter recyclerViewAdapter5 = new RecyclerViewAdapter(getContext(), manetIod);
        myRecycleView4.setLayoutManager(layoutManager4);
        myRecycleView4.setAdapter(recyclerViewAdapter5);

        myRecycleView5 = (RecyclerView) view.findViewById(R.id.vedicSciences_Cards);
        RecyclerViewAdapter recyclerViewAdapter6 = new RecyclerViewAdapter(getContext(), vedicSciences);
        myRecycleView5.setLayoutManager(layoutManager5);
        myRecycleView5.setAdapter(recyclerViewAdapter6);

        myRecycleView6 = (RecyclerView) view.findViewById(R.id.management_cards);
        RecyclerViewAdapter recyclerViewAdapter7 = new RecyclerViewAdapter(getContext(), management);
        myRecycleView6.setLayoutManager(layoutManager6);
        myRecycleView6.setAdapter(recyclerViewAdapter7);

        myRecycleView7 = (RecyclerView) view.findViewById(R.id.electronics_Cards);
        RecyclerViewAdapter recyclerViewAdapter8 = new RecyclerViewAdapter(getContext(), electronics);
        myRecycleView7.setLayoutManager(layoutManager7);
        myRecycleView7.setAdapter(recyclerViewAdapter8);

        myRecycleView8 = (RecyclerView) view.findViewById(R.id.civil_Cards);
        RecyclerViewAdapter recyclerViewAdapter9 = new RecyclerViewAdapter(getContext(), civil);
        myRecycleView8.setLayoutManager(layoutManager8);
        myRecycleView8.setAdapter(recyclerViewAdapter9);

        myRecycleView9 = (RecyclerView) view.findViewById(R.id.mechanical_Cards);
        RecyclerViewAdapter recyclerViewAdapter10 = new RecyclerViewAdapter(getContext(), mechanical);
        myRecycleView9.setLayoutManager(layoutManager9);
        myRecycleView9.setAdapter(recyclerViewAdapter10);

        myRecycleView10 = (RecyclerView) view.findViewById(R.id.aerospace_Cards);
        RecyclerViewAdapter recyclerViewAdapter11 = new RecyclerViewAdapter(getContext(), aerospace);
        myRecycleView10.setLayoutManager(layoutManager10);
        myRecycleView10.setAdapter(recyclerViewAdapter11);

        myRecycleView11 = (RecyclerView) view.findViewById(R.id.cse_Cards);
        RecyclerViewAdapter recyclerViewAdapter12 = new RecyclerViewAdapter(getContext(), cse);
        myRecycleView11.setLayoutManager(layoutManager11);
        myRecycleView11.setAdapter(recyclerViewAdapter12);

        myRecycleView12 = (RecyclerView) view.findViewById(R.id.foodTech_Cards);
        RecyclerViewAdapter recyclerViewAdapter13 = new RecyclerViewAdapter(getContext(), foodTech);
        myRecycleView12.setLayoutManager(layoutManager12);
        myRecycleView12.setAdapter(recyclerViewAdapter13);


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
