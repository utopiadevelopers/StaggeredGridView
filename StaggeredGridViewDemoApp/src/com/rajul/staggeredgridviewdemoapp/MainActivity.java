package com.rajul.staggeredgridviewdemoapp;

import java.util.ArrayList;
import java.util.Random;

import com.rajul.staggeredgridview.StaggeredGridView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	StaggeredGridView gridView;
	String content,temp;
	Random rand;
	int ITEM_COUNT = 5000,ITEM_SIZE_FACTOR=10;
	int sizeOfCell,col_count;
	ArrayList<String> contentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gridView = (StaggeredGridView) findViewById(R.id.gridView1);
		col_count = this.getResources().getInteger(R.integer.column_no);
		gridView.setColumnCount(col_count);
		
		content = "This is a test.";
		rand = new Random();
		contentList = new ArrayList<String>();
		for(int i=0;i<ITEM_COUNT;i++)
		{
			sizeOfCell = rand.nextInt(ITEM_SIZE_FACTOR)+1;
			
			//height = 200 + size * 20;
			//Log.d("Position",position+"  "+size+ "  "+ height);
			contentList.add(makeContent(content,sizeOfCell));
			
		}
		
		MyListAdapter adapter =new MyListAdapter(this,R.layout.gridviewlayout);
		int margin = getResources().getDimensionPixelSize(R.dimen.margin);

		gridView.setItemMargin(margin); // set the GridView margin

		gridView.setPadding(margin, 0, margin, 0); // have the margin on the sides as well 
		
		gridView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	public String makeContent(String content,int num)
	{
		StringBuffer buff = new StringBuffer();
		for(int i=0;i<num;i++)
			buff.append(content);
		
		return buff.toString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class MyListAdapter extends ArrayAdapter
	{
		Context context;
		
		public MyListAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
			this.context = context;
		}
		@Override
		public int getCount() {
			
			return contentList.size();
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater li = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			ViewHolder holder = null;
			if(convertView == null)
			{
				holder = new ViewHolder();
				convertView = li.inflate(R.layout.gridviewlayout, parent,false);
				holder.content = ((TextView)convertView.findViewById(R.id.textViewContent));
				convertView.setTag(holder);
			}
			else
			{
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.content.setText(contentList.get(position));
			//convertView.set
			return convertView;
		}
		
	}
	
	
	class ViewHolder
	{
		public TextView content;
	}
	
	

}
