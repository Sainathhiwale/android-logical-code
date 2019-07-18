package com.examen.cacherxjava.ui.cache.source;

import com.examen.cacherxjava.ui.cache.model.Data;

import io.reactivex.Observable;

public class DiskDataSource {
    private Data data;

    public Observable<Data>getData(){
        return Observable.create(emitter -> {
            if (data!=null){
                emitter.onNext(data);
            }
            emitter.onComplete();
        });
    }

    public void saveToDisk(Data data)  {
        this.data = data.clone();
        this.data.source="disk";
    }
}
