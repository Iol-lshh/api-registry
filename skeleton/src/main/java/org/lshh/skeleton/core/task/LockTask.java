package org.lshh.skeleton.core.task;

import static org.lshh.skeleton.core.task.Task.TaskType.LOCK;

public interface LockTask extends Task{
    // 어떻게 처리? 분산락이 필요할텐데?
    // 요청된 식별자에 대해 락을 걸지 선택하도록 한다.
    // 1. 라우터 단위
    // 2. 태스크 단위
    // 3. 리소서 단위
    // 4. 쿼리 단위

    @Override
    default TaskType getType(){
        return LOCK;
    }
}
