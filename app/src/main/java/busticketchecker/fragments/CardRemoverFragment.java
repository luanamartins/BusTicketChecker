package busticketchecker.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import busticketchecker.database.SQLite.repository.CardRepo;
import busticketchecker.database.dao.BusCardDAO;
import checker.ticker.bus.basic.R;

public class CardRemoverFragment extends Fragment
{

    private ArrayList<String> listOfCards;
    private CardRepo cardRepository;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_main_drawer, container, false);
        setListOfCards(rootView);
        return rootView;
    }

    private void setListOfCards(View rootView)
    {
        final Context context = getActivity().getBaseContext();
        this.cardRepository = new CardRepo(context);

        ArrayList<HashMap<String, String>> listOfCards = cardRepository.getCardList();
        this.listOfCards = new ArrayList<>();
        int numberOfCardsOnDatabase = listOfCards.size();
        final ListView list = (ListView) rootView.findViewById(R.id.listView2);

        for (int i = 0; i < numberOfCardsOnDatabase; i++)
        {
            this.listOfCards.add(listOfCards.get(i).get(BusCardDAO.KEY_NAME));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, android.R.id.text1, this.listOfCards);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String remove = getString(R.string.remove);
                String card = getString(R.string.card).toLowerCase();

                String cardName = CardRemoverFragment.this.listOfCards.get(position);

                String title = remove + " " + card;
                String message = remove + " " + cardName + "?";

                showDialog(title, message, cardName);
            }
        });
    }

    private void showDialog(String title, String message, String cardName)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);

        String yes = getString(R.string.yes);
        String no = getString(R.string.no);
        final String card = cardName;
        builder.setPositiveButton(yes, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                cardRepository.delete(card);
                setListOfCards(rootView);
            }
        });
        builder.setNegativeButton(no, null);
        builder.show();
    }

}
