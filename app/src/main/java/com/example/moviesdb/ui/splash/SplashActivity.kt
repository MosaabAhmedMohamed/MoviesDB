package com.example.moviesdb.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviesdb.R
import com.example.moviesdb.ui.home.HomeActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscriber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }

    private fun init() {
        Observable.timer(500,TimeUnit.MILLISECONDS)
            .subscribeOn(AndroidSchedulers.mainThread()).subscribe(
                object :Observer<Long>{
                override fun onComplete() {
                    goToHome()
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Long) {

                }

                override fun onError(e: Throwable) {
                }

            })
    }


    private fun goToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}