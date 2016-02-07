package busticketchecker.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import checker.ticker.bus.basic.R;
import busticketchecker.activities.CheckerActivity;

public class CardListerFragment extends Fragment
{
    private static final String ARG_SECTION_NUMBER = "section_number";

    public CardListerFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CardListerFragment newInstance(int sectionNumber) {
        CardListerFragment fragment = new CardListerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_drawer, container, false);

        final ListView list = (ListView) rootView.findViewById(R.id.listView2);
        final String[] values = new String[]{
                "Cartão 1",
                "Cartão 2",
        };

        final Context context = getActivity().getBaseContext();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,
                android.R.id.text1, values);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(context, CheckerActivity.class);
                intent.putExtra("cardName", values[position]);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        ((ApplicationActivity) activity).onSectionAttached(
//                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
