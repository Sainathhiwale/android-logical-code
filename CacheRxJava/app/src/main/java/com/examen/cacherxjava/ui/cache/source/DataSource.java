package com.examen.cacherxjava.ui.cache.source;

import com.examen.cacherxjava.ui.cache.model.Data;

import io.reactivex.Observable;

public class DataSource {
private final DiskDataSource diskDataSource;
private final MemoryDataSource memoryDataSource;
private final NetworkDataSource networkDataSource;

    public DataSource(DiskDataSource diskDataSource, MemoryDataSource memoryDataSource, NetworkDataSource networkDataSource) {
        this.diskDataSource = diskDataSource;
        this.memoryDataSource = memoryDataSource;
        this.networkDataSource = networkDataSource;
    }

    public Observable<Data>getDataFromMemory(){
        return memoryDataSource.getData();
    }
    public Observable<Data>getDataFromDisk(){
        return diskDataSource.getData();
    }
    public Observable<Data>getDataFromNetwork(){
        return networkDataSource.getData();
    }
}
