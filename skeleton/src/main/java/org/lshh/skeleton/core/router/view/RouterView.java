package org.lshh.skeleton.core.router.view;

public record RouterView(
        Long id,
        String name,
        String path,
        String description,
        Long taskId
) {
     public static RouterView of(Long id, String name, String path, String description, Long taskId) {
        return new RouterView(id, name, path, description, taskId);
    }
}
