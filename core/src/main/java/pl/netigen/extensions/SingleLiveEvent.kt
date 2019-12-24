/*
 *  Copyright 2017 Google Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package pl.netigen.extensions

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

private const val TAG = "SingleLiveEvent"

abstract class SingleLiveEvent<T> : LiveData<T> {

    constructor(value: T) : super(value) {
        pending.set(true)
    }

    constructor() : super()

    private val pending = AtomicBoolean(false)

    private var subscribedObserver: Observer<in T>? = null


    @Synchronized
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (subscribedObserver != null) {
            Log.w(TAG, "Unsubscribing previous observer as only one can be registered to SingleLiveEvent")
        }
        subscribedObserver = observer
        super.observe(owner, Observer {
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        })
    }

    override fun postValue(value: T?) {
        pending.set(true)
        super.postValue(value)
    }

    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }

    protected open fun call() = postValue(null)

    fun removeObserver() {
        subscribedObserver?.let {
            removeObserver(it)
        }
    }

    @Synchronized
    override fun removeObserver(observer: Observer<in T>) {
        super.removeObserver(observer)
        subscribedObserver = null
    }
}
