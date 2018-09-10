package com.example.contacts;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddContact extends Activity implements OnClickListener {

	ImageView iv;
	EditText txtName, txtCon;
	Button btnCancel, btnOkey;
	private Uri uriImage;
	String name, contact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.addcontacts);
		
		this.iv=(ImageView) this.findViewById(R.id.imageView1);
		this.txtName=(EditText) this.findViewById(R.id.editText1);
		this.txtCon=(EditText) this.findViewById(R.id.editText2);
		this.btnOkey=(Button) this.findViewById(R.id.button1);
		this.btnCancel=(Button) this.findViewById(R.id.button2);
		
		this.iv.setOnClickListener(this);
		this.btnOkey.setOnClickListener(this);
		this.btnCancel.setOnClickListener(this);
		
		Bundle b = this.getIntent().getExtras();
		if(b!=null){
			uriImage = b.getParcelable("image");
			name = b.getString("name");
			contact = b.getString("con");
			
			this.iv.setImageURI(uriImage);
			this.txtName.setText(name);
			this.txtCon.setText(contact);
			
		}
		
		
	}

	@Override
	public void onClick(View arg0) {
		int id=arg0.getId();
		switch(id){
		case R.id.imageView1:
			
			Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			this.startActivityForResult(intent, 100);
			
			break;
		case R.id.button1:
			String name = this.txtName.getText().toString();
			String con = this.txtCon.getText().toString();
			if(!name.equals("") && uriImage!=null && !con.equals("")){
				Intent n = new Intent();
					n.putExtra("image", this.uriImage);
					n.putExtra("name", name);
					n.putExtra("con", con);
					this.setResult(Activity.RESULT_OK,n);
			}
			else Toast.makeText(this, "Please choose image and fill the blanks", Toast.LENGTH_SHORT).show();
			
			//break;
		case R.id.button2:
			this.finish();
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		uriImage = data.getData();
		this.iv.setImageURI(uriImage);
		
	}
	
	
	
	

}
