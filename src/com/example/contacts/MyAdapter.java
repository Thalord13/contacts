package com.example.contacts;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	
	Context context;
	ArrayList<Contacts> list;
	LayoutInflater inflater;
	
	public MyAdapter(Context context, ArrayList<Contacts> list) {
		super();
		this.context = context;
		this.list = list;
		this.inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		ContactView cv = null;
		if(arg1 == null){
			arg1=inflater.inflate(R.layout.contactlayout, null);
			cv=new ContactView();
			cv.iv=(ImageView) arg1.findViewById(R.id.imageView1);
			cv.name=(TextView) arg1.findViewById(R.id.textView1);
			cv.con=(TextView) arg1.findViewById(R.id.textView2);
			arg1.setTag(cv);
		}else cv=(ContactView) arg1.getTag();
		cv.iv.setImageURI(list.get(arg0).getUriImage());
		cv.name.setText(list.get(arg0).getName());
		cv.con.setText(list.get(arg0).getConNumber());
		
		return arg1;
	}
	
	static class ContactView{
		ImageView iv;
		TextView name,con;
	}

}
