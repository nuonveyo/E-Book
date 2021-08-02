package com.veyo.ebook.helper.observable

import com.veyo.ebook.listener.OnCallbackListener
import com.veyo.ebook.listener.OnCompleteListener
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import timber.log.Timber


/**
 * Created by nuonveyo on 1/4/18.
 * Handle operate to communicate data between API to UI
 */
class ResponseObserverHelper<T : Response<*>>(
    private val observer: Observable<T>
) {

    fun execute(listener: OnCallbackListener<T>): Disposable {
        return observer.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { listener.onComplete(it) },
                { throwable ->
                    Timber.e(throwable)
                    listener.onFail(throwable)
                })
    }

    fun execute(listener: OnCompleteListener<T>): Disposable {
        return observer.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { listener.onComplete(it) },
                { throwable -> Timber.e(throwable) })
    }

    fun execute(): Disposable {
        return observer.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { throwable -> Timber.e(throwable) })
    }
}

