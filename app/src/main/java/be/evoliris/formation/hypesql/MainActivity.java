package be.evoliris.formation.hypesql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import be.evoliris.formation.hypesql.db.dao.MovieDAO;
import be.evoliris.formation.hypesql.models.Movie;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieDAO movieDAO = new MovieDAO(MainActivity.this);
//        movieDAO.openWriteable();
//
//        Movie m = new Movie();
//        m.setTitle("Flavian Movie (Horror)");
//        m.setDirector("Cognitic");
//        m.setSypnosis("La journée de dev du diable !!!!");
//
//        long id = movieDAO.insert(m);

        Movie m2 = movieDAO.openReadable().findById(2);

        Toast.makeText(this, m2.getTitle()+ " - "+ m2.getSypnosis(), Toast.LENGTH_LONG).show();
    }
}
