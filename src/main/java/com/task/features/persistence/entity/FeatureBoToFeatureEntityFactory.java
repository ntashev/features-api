package com.task.features.persistence.entity;

import com.task.features.service.model.FeatureBo;

/**
 * @author nikolay.tashev on 23/01/2018.
 */
public class FeatureBoToFeatureEntityFactory {

    public static FeatureBo toBo(FeatureEntity entity) {
        return toBo(entity, entity.isEnabled());
    }

    public static FeatureBo toBo(FeatureEntity entity, boolean isEnabled) {
        FeatureBo bo = new FeatureBo();
        bo.setEnabled(isEnabled);
        bo.setName(entity.getName());

        return bo;
    }

    public static FeatureEntity toEntity(FeatureBo bo) {
        FeatureEntity entity = new FeatureEntity();
        entity.setEnabled(bo.isEnabled());
        entity.setName(bo.getName());

        return entity;
    }
}
