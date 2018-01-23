package com.kuroptev.feedback.screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kuroptev.feedback.BuildConfig;
import com.kuroptev.feedback.MainActivity;
import com.kuroptev.feedback.R;
import com.kuroptev.feedback.data.FeedbackDataBase;
import com.kuroptev.feedback.pojo.Manager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.widget.ArrayAdapter.createFromResource;

public class NewManagerCreator extends AppCompatActivity implements View.OnClickListener{

    TextView nameManager;
    TextView surnameManager;
    TextView middleNameManager;
    Spinner choiceDepartment;
    Spinner choiceDealer;
    ImageView managerPhoto;
    Button addPhoto;
    Button saveManager;
    String department;
    File photoFile;
    Uri photoUri;
    final int CAMERA_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_manager);

        nameManager = (TextView) findViewById(R.id.name);
        surnameManager = (TextView) findViewById(R.id.surname);
        middleNameManager = (TextView) findViewById(R.id.middleName);
        managerPhoto = (ImageView) findViewById(R.id.managerPhoto);

        addPhoto = (Button) findViewById(R.id.addPhoto);
        addPhoto.setOnClickListener(this);
        saveManager = (Button) findViewById(R.id.save);
        saveManager.setOnClickListener(this);

        choiceDepartment = (Spinner) findViewById(R.id.department_spinner);
        choiceDepartment.setAdapter(getAdapter());
        choiceDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] departs = getResources().getStringArray(R.array.department);
                department = departs[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                department = " ";
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CAPTURE) {
            if (data != null) {
                if (data.hasExtra("data")) {
                    Bitmap thumbnailBitmap = data.getParcelableExtra("data");
                    managerPhoto.setImageBitmap(thumbnailBitmap);
                }
            } else {
                managerPhoto.setImageURI(photoUri);
            }
        }
    }

    private ArrayAdapter<?> getAdapter(){
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this, R.array.department, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        return adapter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addPhoto:
                createPhoto();
                break;
            case R.id.save:
                Manager newManager = new Manager();
                newManager.setName(nameManager.getText().toString());
                newManager.setSurname(surnameManager.getText().toString());
                newManager.setMiddleName(middleNameManager.getText().toString());
                newManager.setUrlPhoto(photoUri.toString());
                newManager.setDepartment(department);
                saveInBase(newManager);
                break;
        }
    }

    @SuppressLint("SimpleDateFormat")
    private File createImage() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "JPEG_" + timeStamp + "_";
        File storageEnv = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = new File(storageEnv,imageName +".jpg");
        return image;
    }

    private void createPhoto(){
        try {
            photoFile = createImage();
        }catch (IOException e){
            Toast.makeText(this, "IOException e", Toast.LENGTH_SHORT).show();
        }
        if (photoFile != null){
            photoUri = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                photoUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".provider", photoFile);
            } else{
                photoUri = Uri.fromFile(photoFile);
            }

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, CAMERA_CAPTURE);
        }


    }

    private void saveInBase(Manager manager){
        Task task = new Task();
        task.execute(manager);
        Toast.makeText(this, "Сотрудник сохранён!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    class Task extends AsyncTask<Manager, Void, Void> {
        @Override
        protected Void doInBackground(Manager... manager) {
            FeedbackDataBase.getDataBase(NewManagerCreator.this).getManagersDao().addManager(manager);
            return null;
        }
    }
}
