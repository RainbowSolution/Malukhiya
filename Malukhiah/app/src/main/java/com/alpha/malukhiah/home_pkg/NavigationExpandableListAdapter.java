package com.alpha.malukhiah.home_pkg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpha.malukhiah.R;

import java.util.HashMap;
import java.util.List;


public class NavigationExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<MenuModel> listDataHeader;
    private HashMap<MenuModel, List<MenuModel>> listDataChild;
    private String login;
   public static  View view_bar;

    public NavigationExpandableListAdapter(Context context, List<MenuModel> listDataHeader,
                                           HashMap<MenuModel, List<MenuModel>> listChildData) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
        YourPreference yourPrefrence = YourPreference.getInstance(context);
        login = yourPrefrence.getData("status");
    }

    @Override
    public MenuModel getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = getChild(groupPosition, childPosition).menuName;
        final int imageicon = getChild(groupPosition,childPosition).itemimage;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_child, null);
        }

        TextView txtListChild = convertView.findViewById(R.id.lblListItem);
        ImageView imageView = convertView.findViewById(R.id.icon);


        imageView.setBackgroundResource(imageicon);
        txtListChild.setText(childText);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if (this.listDataChild.get(this.listDataHeader.get(groupPosition)) == null)
            return 0;
        else
            return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                    .size();
    }

    @Override
    public MenuModel getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = getGroup(groupPosition).menuName;
        final int imageicon = getGroup(groupPosition).itemimage;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_header, null);
        }
        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        ImageView imageView = convertView.findViewById(R.id.icon);
        ImageView imageView1 = convertView.findViewById(R.id.imageView1);
        imageView.setBackgroundResource(imageicon);
//        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);


        if (headerTitle.equals("Log out")){
            switch (login) {
                case "logout":
                    lblListHeader.setVisibility(View.INVISIBLE);
                    imageView.setVisibility(View.INVISIBLE);
                    imageView1.setVisibility(View.INVISIBLE);
                    break;
                case "Patient":

                    break;
            }
        }else if (headerTitle.equals("लोग आउट") ){
            switch (login) {
                case "logout":
                    lblListHeader.setVisibility(View.INVISIBLE);
                    imageView.setVisibility(View.INVISIBLE);
                    imageView1.setVisibility(View.INVISIBLE);
                    break;
                case "Patient":

                    break;
            }
        }else {
            lblListHeader.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    @Override
    public int getGroupType(int groupPosition) {
        return super.getGroupType(groupPosition);
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return super.getChildType(groupPosition, childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}