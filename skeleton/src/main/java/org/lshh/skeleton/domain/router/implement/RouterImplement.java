package org.lshh.skeleton.domain.router.implement;

import org.lshh.skeleton.domain.router.Router;

public class RouterImplement implements Router{
    private RouterContext context;

    RouterImplement(RouterContext context) {
        this.context = context;
    }

    public static RouterImplement of(RouterContext context) {
        return new RouterImplement(context);
    }

    @Override
    public String getPath() {
        return context.getPath();
    }

    @Override
    public Long getId() {
        return context.getId();
    }
}
