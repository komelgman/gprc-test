package kom.test.grpc;

import io.grpc.stub.StreamObserver;
import rx.Observer;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by syungman on 02.12.2015
 */
public class ObserverProxy<V> implements StreamObserver<V> {
    private AtomicReference<Observer<V>> observer = new AtomicReference<Observer<V>>(null);

    @Override
    public void onNext(V v) {
        Observer<V> rxObserver = observer.get();
        if (rxObserver != null)
            rxObserver.onNext(v);
    }

    @Override
    public void onError(Throwable throwable) {
        Observer<V> rxObserver = observer.get();
        if (rxObserver != null)
            rxObserver.onError(throwable);
    }

    @Override
    public void onCompleted() {
        Observer<V> rxObserver = observer.get();
        if (rxObserver != null)
            rxObserver.onCompleted();
    }

    public void setObserver(Observer<V> rxObserver) {
        if (!observer.compareAndSet(null, rxObserver))
            throw new IllegalStateException("Proxy was initialized before");
    }
}