package com.davis.rxandroidroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "myApp";
    private String greetings = "Hello From Rxjava";
    private Observable<String> myObservable;
    private DisposableObserver<String> myObserver;

    @BindView(R.id.txtGreeting)
    TextView txtGreeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        myObservable = Observable.just(greetings);
        myObservable.subscribeOn(Schedulers.io());

        myObservable.observeOn(AndroidSchedulers.mainThread());

//        myObserver = new Observer<String>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.d(TAG, "onSubscribe: called");
//
//                disposable=d;
//            }
//
//            @Override
//            public void onNext(@NonNull String s) {
//                Log.d(TAG, "onNext: called");
//                txtGreeting.setText(s);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.d(TAG, "onError: called");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete: called");
//            }
//        };
        myObserver= new DisposableObserver<String>() {
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
                Log.d(TAG, "onComplete: called");
            }
        };
        myObservable.subscribe(myObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myObserver.dispose();
    }
}
