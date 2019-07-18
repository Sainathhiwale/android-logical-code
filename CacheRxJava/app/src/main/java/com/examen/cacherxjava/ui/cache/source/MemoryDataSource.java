package com.examen.cacherxjava.ui.cache.source;

import com.examen.cacherxjava.ui.cache.model.Data;

import io.reactivex.Emitter;
import io.reactivex.Observable;

public class MemoryDataSource {
    private Data data;
   //1.observables representing sources of data
   //2.subscribers (or observers) listening to the observables
   //3.a set of methods for modifying and composing the data
    public Observable<Data>getData(){
        return Observable.create(emitter ->{
            if (data!=null){
                emitter.onNext(data);
            }
            emitter.onComplete();
        });
    }

    public void cacheInMemory(Data data){
        this.data = data.clone();
        this.data.source = "memory";
    }
}
