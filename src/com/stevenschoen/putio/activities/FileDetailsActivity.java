package com.stevenschoen.putio.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.stevenschoen.putio.PutioFileData;
import com.stevenschoen.putio.R;
import com.stevenschoen.putio.fragments.FileDetails;

public class FileDetailsActivity extends SherlockFragmentActivity {
	private FileDetails fileDetailsFragment;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		PutioFileData fileData = (PutioFileData) getIntent().getExtras().getParcelable("fileData");
		
		setContentView(R.layout.filedetailsphone);
		
		if (savedInstanceState == null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			fileDetailsFragment = new FileDetails(fileData);
			fragmentTransaction.add(R.id.DetailsHolder, fileDetailsFragment);
			fragmentTransaction.commit();
		} else {
			fileDetailsFragment = (FileDetails) getSupportFragmentManager().findFragmentById(R.id.DetailsHolder);
		}
		
		getSupportActionBar().setTitle(fileDetailsFragment.getOldFilename());
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		switch (menuItem.getItemId()) {
		case android.R.id.home:
			Intent homeIntent = new Intent(getApplicationContext(), Putio.class);
			homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(homeIntent);
			return true;
		}
		return (super.onOptionsItemSelected(menuItem));
	}
	
	@Override
	protected void onSaveInstanceState (Bundle outState) {
	    super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}