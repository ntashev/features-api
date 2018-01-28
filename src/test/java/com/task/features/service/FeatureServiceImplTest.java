package com.task.features.service;

import com.task.features.persistence.entity.FeatureEntity;
import com.task.features.persistence.repository.FeatureRepo;
import com.task.features.service.exception.EntityNotFoundException;
import com.task.features.service.model.FeatureBo;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author nikolay.tashev on 25/01/2018.
 */
public class FeatureServiceImplTest {

    private FeatureServiceImpl service;
    private FeatureRepo repo;

    @Before
    public void setUp() {
        repo = mock(FeatureRepo.class);
        service = new FeatureServiceImpl();
        service.setRepo(repo);
    }

    @Test
    public void testGetAllFeaturesTwoEntriesOnlyOneEnabled() {
        FeatureEntity e1 = new FeatureEntity();
        FeatureEntity e2 = new FeatureEntity();
        e1.setName("test");
        e1.setGloballyEnabled(true);
        e2.setName("test2");
        e2.setGloballyEnabled(false);
        Set<FeatureEntity> entities = new HashSet<>(Arrays.asList(e1, e2));
        when(repo.findAll()).thenReturn(entities.stream());

        Set<FeatureBo> result = service.getEnabledFeatures();

        assertNotNull(result);
        assertEquals(1, result.size());
        FeatureBo bo = result.iterator().next();
        assertNotNull(bo);
        assertEquals("test", bo.getName());
        assertTrue(bo.isEnabled());
    }

    @Test
    public void testGetAllFeaturesNonFound() {
        when(repo.findAll()).thenReturn(new HashSet<FeatureEntity>().stream());

        Set<FeatureBo> result = service.getEnabledFeatures();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testGetAllFeaturesTwoEntriesTwoEnabled() {
        FeatureEntity e1 = new FeatureEntity();
        FeatureEntity e2 = new FeatureEntity();
        e1.setName("test");
        e1.setGloballyEnabled(true);
        e2.setName("test2");
        e2.setGloballyEnabled(true);
        Set<FeatureEntity> entities = new HashSet<>(Arrays.asList(e1, e2));
        when(repo.findAll()).thenReturn(entities.stream());
        FeatureBo bo1 = new FeatureBo();
        FeatureBo bo2 = new FeatureBo();
        bo1.setName("test");
        bo1.setEnabled(true);
        bo2.setName("test2");
        bo2.setEnabled(true);
        Set<FeatureBo> expected = new HashSet<>(Arrays.asList(bo1, bo2));

        Set<FeatureBo> result = service.getEnabledFeatures();

        assertEquals(expected, result);
    }

    @Test
    public void testGetAllFeaturesTwoEntriesNoneEnabled() {
        FeatureEntity e1 = new FeatureEntity();
        FeatureEntity e2 = new FeatureEntity();
        e1.setName("test");
        e1.setGloballyEnabled(false);
        e2.setName("test2");
        e2.setGloballyEnabled(false);
        Set<FeatureEntity> entities = new HashSet<>(Arrays.asList(e1, e2));
        when(repo.findAll()).thenReturn(entities.stream());

        Set<FeatureBo> result = service.getEnabledFeatures();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testUpdateFeatureSuccess() {
        FeatureBo bo = new FeatureBo();
        bo.setName("updated");
        bo.setEnabled(true);
        FeatureEntity e = new FeatureEntity();
        e.setName("test");
        e.setGloballyEnabled(false);
        when(repo.findOneById(1)).thenReturn(Optional.of(e));
        FeatureEntity expected = new FeatureEntity();
        expected.setGloballyEnabled(true);
        expected.setName("updated");

        service.updateFeature(1, bo);

        verify(repo).findOneById(1);
        verify(repo).save(expected);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testUpdateFeatureNotFound() {
        FeatureBo bo = new FeatureBo();
        bo.setName("updated");
        bo.setEnabled(true);
        when(repo.findOneById(1)).thenReturn(Optional.empty());

        service.updateFeature(1, bo);
    }

    @Test
    public void testCreateFeature() {
        FeatureBo toCreate = new FeatureBo();
        toCreate.setName("test");
        toCreate.setEnabled(true);
        FeatureEntity created = new FeatureEntity();
        created.setName("test");
        created.setGloballyEnabled(true);
        created.setId(1);
        when(repo.save(any())).thenReturn(created);

        Integer id = service.createFeature(toCreate);

        assertNotNull(id);
        assertEquals(Integer.valueOf(1), id);
    }

    @Test
    public void testDeleteFeatureSuccess() {
        FeatureEntity e = new FeatureEntity();
        e.setName("test");
        e.setGloballyEnabled(false);
        when(repo.findOneById(1)).thenReturn(Optional.of(e));

        service.deleteFeature(1);

        verify(repo).findOneById(1);
        verify(repo).delete(e);
    }
}
