package com.cuukenn.common.netty.client.ui;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author changgg
 */
public abstract class BaseControlledStage implements ControlledStage {
    @Getter(AccessLevel.PROTECTED)
    private StageController stageController;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }
}
