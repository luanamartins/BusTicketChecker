package busticketchecker.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import busticketchecker.activities.CheckerActivity;
import busticketchecker.database.SQLite.repository.CardRepo;
import busticketchecker.database.dao.BusCardDAO;
import busticketchecker.fragments.adapter.CustomList;
import checker.ticker.bus.basic.R;

public class CardListerFragment extends Fragment
{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private  ArrayList<BusCardDAO> listOfCards;

    public CardListerFragment()
    {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CardListerFragment newInstance(int sectionNumber)
    {
        CardListerFragment fragment = new CardListerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_main_drawer, container, false);

        final Context context = getActivity().getBaseContext();
        CardRepo repo = new CardRepo(context);
        ArrayList<HashMap<String, String>> listOfCardsFromDatabase = repo.getCardList();

        this.listOfCards = new ArrayList<>();
        HashMap<String, String> hashMap;
        BusCardDAO currentCard;
        for(int i = 0; i < listOfCardsFromDatabase.size(); i++){
            hashMap = listOfCardsFromDatabase.get(i);
            currentCard = new BusCardDAO();
            currentCard.setName(hashMap.get(BusCardDAO.KEY_NAME));
            currentCard.setType(hashMap.get(BusCardDAO.KEY_TYPE));
            currentCard.setAmount(Float.valueOf(hashMap.get(BusCardDAO.KEY_AMOUNT)));
            this.listOfCards.add(currentCard);
        }

        final ListView list = (ListView) rootView.findViewById(R.id.listView2);

        CustomList customAdapter = new CustomList(getActivity(), listOfCards);
        list.setAdapter(customAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(context, CheckerActivity.class);
                intent.putExtra("cardName", CardListerFragment.this.listOfCards.get(position).getName());
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
//        ((ApplicationActivity) activity).onSectionAttached(
//                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
