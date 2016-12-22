package du.zhou.com.du.business.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import du.zhou.com.du.R;

/**
 */
public class SquareFragment extends Fragment {
    public SquareFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SquareFragment newInstance() {
        SquareFragment fragment = new SquareFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_square, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
