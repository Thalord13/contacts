package com.example.contacts;

import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements android.content.DialogInterface.OnClickListener {
	
	ListView lv;
	ArrayList<Contacts> list = new ArrayList<Contacts>();
	MyAdapter adapter;
	private Uri uriImage;
	private String name;
	private String contact;
	AdapterView.AdapterContextMenuInfo info;
	AlertDialog.Builder builder;
	Database db;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        this.db=new Database(this);
        
        builder = new AlertDialog.Builder(this);
        
        this.lv=(ListView) this.findViewById(R.id.listView1);
        this.adapter=new MyAdapter(this,list);
        this.lv.setAdapter(adapter);
        this.registerForContextMenu(lv);
        
    }
    
    @Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
    	
    	int id = item.getItemId();
    	switch(id){
    	case R.id.update:
    		
    		Intent intent = new Intent(this, AddContact.class);
    		
    		uriImage=list.get(info.position).getUriImage();
    		name=list.get(info.position).getName();
    		contact=list.get(info.position).getConNumber();
    		
    		intent.putExtra("name", name);
			intent.putExtra("image", uriImage);
			intent.putExtra("con", contact);
			
    		this.startActivityForResult(intent, 2);
    		
    		break;
    	case R.id.delete:
    		builder.setTitle("Message");
    		builder.setMessage("DO YOU REALLY WANT TO DELETE?");
    		builder.setPositiveButton("Yes",this);
    		builder.setNegativeButton("NO", this);
    		
    		AlertDialog dialog = builder.create();
    		dialog.show();
    		
    	}
    	
		return super.onContextItemSelected(item);
	}


	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.contextmenu,menu);
		
		info=(AdapterContextMenuInfo) menuInfo;
		String title=list.get(info.position).getName();
		menu.setHeaderTitle(title);
		
	}



	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		Intent intent = new Intent(this, AddContact.class);
		this.startActivityForResult(intent, 1);
		
		return super.onOptionsItemSelected(item);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == Activity.RESULT_OK){
			if(requestCode == 1){
				Bundle b = data.getExtras();
				uriImage = b.getParcelable("image");
				name = b.getString("name");
				contact = b.getString("con");
				
				/*long result=db.addContact(contact, name, uriImage);
				String message=(result>0)?"New Person Added":"Error Adding Person";
				Toast.makeText(this, message, Toast.LENGTH_LONG).show();*/
				
				list.add(new Contacts(uriImage, name, contact));
				adapter.notifyDataSetChanged();
				
			}
			if(requestCode == 2){
				Bundle b = data.getExtras();
				uriImage = b.getParcelable("image");
				name = b.getString("name");
				contact = b.getString("con");
				
				list.set(info.position,new Contacts(uriImage, name, contact));
				adapter.notifyDataSetChanged();
				Toast.makeText(this, "PERSON IS UPDATED", Toast.LENGTH_SHORT).show();
				
			}
		}
		
	}

	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		
		switch(arg1){
		case DialogInterface.BUTTON_POSITIVE:
			list.remove(info.position);
    		adapter.notifyDataSetChanged();
			break;
		case DialogInterface.BUTTON_NEGATIVE:
			arg0.dismiss();
		}
		
	}

   
    
}
