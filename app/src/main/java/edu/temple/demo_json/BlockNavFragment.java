package edu.temple.demo_json;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlockNavFragment extends Fragment {

    Button preBlock;
    Button nextBlock;
    Button enterBlock;
    Button newBlock;
    EditText editText;
    FragmentManager fm;
    BlockFragment receiver;
    ArrayList<BlockFragment> arrayList;
    int currentIndex;

    public BlockNavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_block_nav, container, false);
        preBlock = (Button) v.findViewById(R.id.previousBlock);
        nextBlock = (Button) v.findViewById(R.id.newBlock);
        enterBlock = (Button) v.findViewById(R.id.enterBlock);
        newBlock = (Button) v.findViewById(R.id.newBlock);
        editText = (EditText) v.findViewById(R.id.blockInput);
        arrayList = new ArrayList<>();
        fm = getFragmentManager();

        preBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIndex > 0) {
                    currentIndex--;
                    fm.beginTransaction().replace(R.id.Sub_Layout,arrayList.get(currentIndex)).commit();
                    fm.executePendingTransactions();
                    receiver = arrayList.get(currentIndex);
                }
            }
        });

        nextBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIndex < arrayList.size()-1) {
                    currentIndex++;
                    fm.beginTransaction().replace(R.id.Sub_Layout,arrayList.get(currentIndex)).commit();

                    fm.executePendingTransactions();
                    receiver = arrayList.get(currentIndex);

                }
            }
        });

        newBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receiver = new BlockFragment();
                arrayList.add(receiver);
                currentIndex = arrayList.size()-1;
                fm.beginTransaction().replace(R.id.Sub_Layout,arrayList.get(currentIndex)).commit();
                fm.executePendingTransactions();
            }
        });

        enterBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editText.getText().toString();
                int num = Integer.parseInt(input);
                receiver = arrayList.get(currentIndex);
                receiver.retrieveBlockData(num);
            }
        });


        return v;
    }

}
