package du.zhou.com.du.business;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import du.zhou.com.du.R;


public class IndexFragment extends Fragment {

    public IndexFragment() {
        // Required empty public constructor
    }

    public static IndexFragment newInstance() {
        IndexFragment fragment = new IndexFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        XRecyclerView recyclerView = (XRecyclerView) view.findViewById(R.id.recyclerview);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
