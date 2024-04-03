package org.lshh.skeleton.core.port;

import org.lshh.skeleton.core.resource.query.QueryManager;
import org.lshh.skeleton.core.resource.resourcer.ResourcerManager;
import org.lshh.skeleton.core.router.RouterManager;
import org.lshh.skeleton.core.task.TaskManager;

public interface PortManager {
    PortManager setRouterManager(RouterManager routerManager);
    PortManager setTaskManager(TaskManager taskManager);
    PortManager setQueryManager(QueryManager queryManager);
    PortManager setResourcerManager(ResourcerManager resourcerManager);
}
