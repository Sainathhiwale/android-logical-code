package com.examen.cacherxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.examen.cacherxjava.ui.cache.model.Data;
import com.examen.cacherxjava.ui.cache.source.DataSource;
import com.examen.cacherxjava.ui.cache.source.DiskDataSource;
import com.examen.cacherxjava.ui.cache.source.MemoryDataSource;
import com.examen.cacherxjava.ui.cache.source.NetworkDataSource;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    Button btn;
    TextView textView;
    DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomeWork();
            }
        });

        dataSource = new DataSource(new DiskDataSource(),new MemoryDataSource(),new NetworkDataSource());

    }

    private void doSomeWork() {
        Observable<Data> memory = dataSource.getDataFromMemory();
        Observable<Data> disk = dataSource.getDataFromDisk();
        Observable<Data> network = dataSource.getDataFromNetwork();

        Observable.concat(memory,disk,network)
                .firstElement()
                .subscribeOn(Schedulers.io())
                .toObservable()
                .subscribe(getObserver());
    }

    private Observer<Data> getObserver() {
        return new Observer<Data>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Data data) {
               textView.append("Onnext "+data.source);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
