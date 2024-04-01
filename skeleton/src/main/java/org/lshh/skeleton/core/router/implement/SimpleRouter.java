package org.lshh.skeleton.core.router.implement;

import org.lshh.skeleton.core.router.Router;

public class SimpleRouter implements Router{
    private RouterContext context;

    SimpleRouter(RouterContext context) {
        this.context = context;
    }
    public static Router of(RouterContext context) {
        return new SimpleRouter(context);
    }

    @Override
    public String getPath() {
        return context.getPath();
    }

    @Override
    public Long getId() {
        return context.getId();
    }

    // task id 요청
}
