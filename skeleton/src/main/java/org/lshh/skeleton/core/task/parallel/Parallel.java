package org.lshh.skeleton.core.task.parallel;

import org.lshh.skeleton.core.task.Task;

public interface Parallel extends Task {
    // 묶음 열어 병렬적으로 처리(경합)
    // 경합 - 스트림 우선 처리, 모두 다 성공까지 대기
}
