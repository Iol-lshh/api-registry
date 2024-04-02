package org.lshh.skeleton.core.router.implement;

import org.lshh.skeleton.core.router.Router;

public class SimpleRouter implements Router{
    private final RouterContext CONTEXT;

    SimpleRouter(RouterContext context) {
        CONTEXT = context;
    }
    public static Router of(RouterContext context) {
        return new SimpleRouter(context);
    }

    @Override
    public String getPath() {
        return CONTEXT.getPath();
    }

    @Override
    public Long getId() {
        return CONTEXT.getId();
    }

    @Override
    public String getName() {
        return CONTEXT.getName();
    }

    @Override
    public String getDescription() {
        return CONTEXT.getDescription();
    }

    @Override
    public Long getTaskId() {
        return CONTEXT.getTaskId();
    }
    // task id 요청
}
