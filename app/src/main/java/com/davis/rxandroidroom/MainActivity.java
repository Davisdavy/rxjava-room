package com.davis.rxandroidroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "myApp";
    private String greetings = "Hello Rxxjava";
    private Observable<String> myObservable;
    private Observer<String> myObserver;
    @BindView(R.id.txtGreeting)
    TextView txtGreeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        myObservable = Observable.just(greetings);

        myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: called");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "onNext: called");
                txtGreeting.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: called");
            }

            @Override
            public void onComplete() {

            }
        };

        myObservable.subscribe(myObserver);
    }
}
