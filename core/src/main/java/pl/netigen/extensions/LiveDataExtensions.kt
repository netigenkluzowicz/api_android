package pl.netigen.extensions

import androidx.lifecycle.*


fun <T, R> LiveData<T>.map(transformation: (T) -> R): LiveData<R> = Transformations.map(this, transformation)

fun <T, R> LiveData<T>.switchMap(transformation: (T) -> LiveData<R>): LiveData<R> = Transformations.switchMap(this, transformation)

fun <T> LiveData<T>.observeDistinct(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) = distinctUntilChanged().observe(lifecycleOwner, observer)

fun <T> LiveData<T>.distinctUntilChanged() = Transformations.distinctUntilChanged(this)

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) = observe(lifecycleOwner, Observer(observer))

fun <F, S> LiveData<F>.zipLiveData(liveData: LiveData<S>): LiveData<Pair<F, S>> {
    return MediatorLiveData<Pair<F, S>>().apply {
        var lastA: F? = null
        var lastB: S? = null

        fun update() {
            val localLastA = lastA
            val localLastB = lastB
            if (localLastA != null && localLastB != null)
                this.value = Pair(localLastA, localLastB)
        }

        addSource(this@zipLiveData) {
            lastA = it
            update()
        }
        addSource(liveData) {
            lastB = it
            update()
        }
    }
}

fun <F, S> Pair<LiveData<F>, LiveData<S>>.zipLiveData(): LiveData<Pair<F, S>> = first.zipLiveData(second)

fun <T> LiveData<T?>.nonNull(): LiveData<T> {
    return MediatorLiveData<T>().apply {
        addSource(this@nonNull) {
            if (it != null) this.value = it
        }
    }
}

fun <T> LiveData<T>.filter(predicate: (T) -> Boolean): LiveData<T> {
    return MediatorLiveData<T>().apply {
        addSource(this@filter) {
            if (it != null && predicate.invoke(it)) this.value = it
        }
    }
}

fun <F, S> LiveData<F>.withLatestFrom(liveData: LiveData<S>): LiveData<Pair<F, S>> {
    return MediatorLiveData<Pair<F, S>>().apply {
        var lastA: F? = null
        var lastB: S? = null

        fun update() {
            val localLastA = lastA
            val localLastB = lastB
            if (localLastA != null && localLastB != null)
                this.value = Pair(localLastA, localLastB)
        }

        addSource(this@withLatestFrom) {
            lastA = it
            update()
        }
        addSource(liveData) {
            lastB = it
        }
    }
}