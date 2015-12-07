package kom.test.grpc;

import io.grpc.stub.StreamObserver;
import rx.subjects.PublishSubject;

/**
 * Created by Sergey on 03.12.2015
 */
public class ObserverProxy<V> implements StreamObserver<V> {

    private final PublishSubject<V> subject;

    public ObserverProxy(PublishSubject<V> subject) {
        this.subject = subject;
    }

    @Override
    public void onNext(V v) {
        subject.onNext(v);
    }

    @Override
    public void onError(Throwable throwable) {
        subject.onError(throwable);
    }

    @Override
    public void onCompleted() {
        subject.onCompleted();
    }
}
