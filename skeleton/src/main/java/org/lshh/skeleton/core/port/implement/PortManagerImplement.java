package org.lshh.skeleton.core.port.implement;

import org.lshh.skeleton.core.port.PortManager;
import org.lshh.skeleton.core.resource.query.QueryManager;
import org.lshh.skeleton.core.resource.resourcer.ResourcerManager;
import org.lshh.skeleton.core.router.RouterManager;
import org.lshh.skeleton.core.task.TaskManager;

public abstract class PortManagerImplement implements PortManager {
    protected RouterManager routerManager;
    protected TaskManager taskManager;
    protected QueryManager queryManager;
    protected ResourcerManager resourcerManager;

    public PortManagerImplement(
            RouterManager routerManager,
            TaskManager taskManager,
            QueryManager queryManager,
            ResourcerManager resourcerManager
    ) {
        this.setRouterManager(routerManager)
            .setTaskManager(taskManager)
            .setQueryManager(queryManager)
            .setResourcerManager(resourcerManager);
    }

    @Override
    public PortManager setRouterManager(RouterManager manager) {
        this.routerManager = manager;
        return this;
    }

    @Override
    public PortManager setTaskManager(TaskManager manager) {
        this.taskManager = manager;
        return this;
    }

    @Override
    public PortManager setQueryManager(QueryManager manager) {
        this.queryManager = manager;
        return this;
    }

    @Override
    public PortManager setResourcerManager(ResourcerManager manager) {
        this.resourcerManager = manager;
        return this;
    }
}
