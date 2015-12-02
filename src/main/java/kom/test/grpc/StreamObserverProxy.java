package kom.test.grpc;

import io.grpc.stub.StreamObserver;
import rx.Observer;

/**
 * Created by syungman on 02.12.2015
 */
public class StreamObserverProxy<V> implements Observer<V>  {

    private final StreamObserver<V> observer;

    public StreamObserverProxy() {
        this.observer = new StreamObserver<V>() {
            @Override
            public void onNext(V v) {
                StreamObserverProxy.this.onNext(v);
            }

            @Override
            public void onError(Throwable throwable) {
                StreamObserverProxy.this.onError(throwable);
            }

            @Override
            public void onCompleted() {
                StreamObserverProxy.this.onCompleted();
            }
        };
    }

    @Override
    public void onCompleted() {}

    @Override
    public void onError(Throwable e) {}

    @Override
    public void onNext(V v) {}

    public StreamObserver<V> getStreamObserver() {
        return observer;
    }
}
