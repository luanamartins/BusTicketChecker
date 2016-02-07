package busticketchecker.fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import checker.ticker.bus.basic.R;
import busticketchecker.database.SQLite.repository.CardRepo;
import busticketchecker.database.dao.BusCardDAO;

public class CardAdderFragment extends Fragment implements View.OnClickListener
{

    private String cardName;
    private String cardType;
    private Context context;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_card_adder, container, false);
        context = inflater.getContext();
        ((Button) view.findViewById(R.id.addNewCardButton)).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v)
    {
        EditText newCardNameEditText = (EditText) view.findViewById(R.id.newCardNameEditText);
        String newCardName = newCardNameEditText.getText().toString();

        EditText newCardTypeEditText = (EditText) view.findViewById(R.id.newCardTypeEditText);
        String newCardType = newCardTypeEditText.getText().toString();

        CardRepo repo = new CardRepo(context);
        BusCardDAO card = new BusCardDAO(newCardName, newCardType);
        repo.insert(card);

        Toast.makeText(context, "OK", Toast.LENGTH_SHORT);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new CardListerFragment();

        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();

    }

}
