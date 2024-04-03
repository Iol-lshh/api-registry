package org.lshh.skeleton.core.task;

import org.lshh.skeleton.core.task.dto.RollbackEvent;

public interface TaskManager {
    Task generate(Long startTaskId);
    // mangaer는 task를 만들어준다.
    // 하위 테스크까지 전부 다!
    // QueryTask의 경우, Resourcer와 Query 객체를 받아준다.
    // 각 도메인들의 Manager를 참조해야한다는 뜻!
    void handleRollback(RollbackEvent event);
}
