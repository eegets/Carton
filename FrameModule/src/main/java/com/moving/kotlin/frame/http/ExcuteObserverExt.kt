package com.secoo.commonsdk.ext

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

open class ExcuteObserverExt<T>(val onExcuteNext: (T) -> Unit = {},
                                val onExcuteComplete: () -> Unit = {},
                                val onExcuteError: (Throwable) -> Unit = {}): Observer<T> {

    private var disposable: Disposable? = null
    override fun onComplete() {
        onExcuteComplete()
    }

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    override fun onNext(t: T) {
        try {
            onExcuteNext(t)
            this.onComplete()
        }catch (e: Throwable){
            this.onError(e)
        }finally {
            disposable!!.dispose()
        }
    }

    override fun onError(t: Throwable) {
        onExcuteError(t)
    }

}