package org.lshh.skeleton.core.port.implement;

import org.lshh.skeleton.core.port.RoutingPortManager;
import org.lshh.skeleton.core.resource.query.QueryManager;
import org.lshh.skeleton.core.resource.resourcer.ResourcerManager;
import org.lshh.skeleton.core.router.Router;
import org.lshh.skeleton.core.router.RouterManager;
import org.lshh.skeleton.core.task.Task;
import org.lshh.skeleton.core.task.TaskManager;

import java.util.Map;

public class RoutingPortManagerImplement extends PortManagerImplement implements RoutingPortManager {

    public RoutingPortManagerImplement(RouterManager routerManager, TaskManager taskManager, QueryManager queryManager, ResourcerManager resourcerManager) {
        super(routerManager, taskManager, queryManager, resourcerManager);
    }

    @Override
    public Map<String, Object> request(String path, Map<String, Object> args) {
        Router router = routing(path);
        Task rootTask = taskManager.find(router.getTaskId());
        rootTask.setArgs(args);
        return rootTask.execute();
    }

    @Override
    public Router routing(String path) {
        return routerManager.findByPath(path);
    }
}
