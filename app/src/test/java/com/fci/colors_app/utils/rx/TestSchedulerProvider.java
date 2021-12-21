

package com.fci.colors_app.utils.rx;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

/**
 * Created by amitshekhar on 11/07/17.
 */

public class TestSchedulerProvider implements SchedulerProvider {

    private final TestScheduler mTestScheduler;

    public TestSchedulerProvider(TestScheduler testScheduler) {
        this.mTestScheduler = testScheduler;
    }

    @Override
    public Scheduler computation() {
        return mTestScheduler;
    }

    @Override
    public Scheduler io() {
        return mTestScheduler;
    }

    @Override
    public Scheduler ui() {
        return mTestScheduler;
    }
}
