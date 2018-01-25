package com.task.features.persistence.entity;

import com.task.features.service.model.FeatureBo;

/**
 * Factory to convert between {@link FeatureBo} and {@link FeatureEntity}.
 */
public class FeatureBoToFeatureEntityFactory {

    /**
     * Creates {@link FeatureBo} from entity.
     *
     * @param entity entity
     * @return feature business object
     */
    public static FeatureBo toBo(FeatureEntity entity) {
        return toBo(entity, entity.isGloballyEnabled());
    }

    /**
     * Creates {@link FeatureBo} from entity and sets isEnabled disregarding isGloballyEnabled.
     *
     * @param entity entity
     * @param isEnabled is enabled
     * @return feature business object
     */
    public static FeatureBo toBo(FeatureEntity entity, boolean isEnabled) {
        FeatureBo bo = new FeatureBo();
        bo.setEnabled(isEnabled);
        bo.setName(entity.getName());

        return bo;
    }

    /**
     * Creates {@link FeatureEntity} from business object
     *
     * @param bo business object
     * @return entity
     */
    public static FeatureEntity toEntity(FeatureBo bo) {
        FeatureEntity entity = new FeatureEntity();
        entity.setGloballyEnabled(bo.isEnabled());
        entity.setName(bo.getName());

        return entity;
    }
}
