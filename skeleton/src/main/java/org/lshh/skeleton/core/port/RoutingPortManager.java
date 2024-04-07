package org.lshh.skeleton.core.port;

import org.lshh.skeleton.core.router.Router;

import java.util.Map;

public interface RoutingPortManager extends PortManager {

    Map<String, Object> request(String path, Map<String, Object> args);

    Router routing(String path);
}
