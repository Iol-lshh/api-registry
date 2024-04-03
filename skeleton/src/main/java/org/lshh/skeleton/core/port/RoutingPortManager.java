package org.lshh.skeleton.core.port;

import org.lshh.skeleton.core.router.Router;

import java.util.Map;

public interface RoutingPortManager extends PortManager {
    Map<String, Object> request(String path);
    Router routing(String path);
}
