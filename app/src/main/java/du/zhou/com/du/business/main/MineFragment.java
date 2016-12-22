package du.zhou.com.du.business.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import du.zhou.com.du.R;
import du.zhou.com.du.common.GlideRoundTransform;

/**
 */
public class MineFragment extends Fragment {
    public MineFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MineFragment.
     */
    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_face);
        Glide.with(getActivity()).load(R.mipmap.slide_1).transform(new GlideRoundTransform(getContext(),10)).into(imageView);
        return view;
    }

}
