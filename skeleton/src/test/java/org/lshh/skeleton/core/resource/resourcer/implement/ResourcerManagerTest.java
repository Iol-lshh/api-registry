package org.lshh.skeleton.core.resource.resourcer.implement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lshh.skeleton.core.resource.resourcer.Resourcer;
import org.lshh.skeleton.core.resource.resourcer.ResourcerProvider;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerCreateCommand;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerUpdateCommand;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.lshh.skeleton.core.resource.resourcer.Resourcer.ResourcerType.RDBMS;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResourcerManagerTest {

    @Mock
    ResourcerProvider provider;
    @InjectMocks
    ResourcerManagerImplement resourcerManager;
    @Test
    void find_WithResourceNotInCache_ReturnProvidedResource() {
        long resourceId = 1L;
        Resourcer mockResourcer = mock(Resourcer.class);
        when(provider.find(resourceId)).thenReturn(Optional.of(mockResourcer));

        Resourcer resourcer = resourcerManager.find(resourceId);
        
        assertEquals(mockResourcer, resourcer);
        verify(provider, times(1)).find(resourceId);
    }
    
    @Test
    void find_WithResourceInCache_ReturnCachedResource() {
        long resourceId = 1L;
        ResourcerCreateCommand mockCreateCommand = mock(ResourcerCreateCommand.class);
        Resourcer mockCachedResourcer = mock(Resourcer.class);
        when(mockCachedResourcer.getId()).thenReturn(resourceId);
        when(provider.create(mockCreateCommand)).thenReturn(mockCachedResourcer);

        resourcerManager.create(mockCreateCommand);
        Resourcer resourcer = resourcerManager.find(resourceId);

        assertEquals(mockCachedResourcer, resourcer);
        verify(provider, times(0)).find(resourceId);
    }

    @Test
    void find_WithResourceNotFound_ReturnNull() {
        long resourceId = 1L;
        when(provider.find(resourceId)).thenReturn(Optional.empty());

        Resourcer resourcer = resourcerManager.find(resourceId);

        assertNull(resourcer);
    }

    @Test
    void findAll_ReturnsCorrectList_WhenCalled() {
        Resourcer resourcer1 = mock(Resourcer.class);
        Resourcer resourcer2 = mock(Resourcer.class);
        List<Resourcer> expect = Arrays.asList(resourcer1, resourcer2);
        when(provider.findAll()).thenReturn(expect);

        List<Resourcer> returnedResources = resourcerManager.findAll();

        assertEquals(expect.size(), returnedResources.size());
        assertTrue(returnedResources.containsAll(expect));
    }

    @Test
    public void update() {
        Resourcer resourcer = mock(Resourcer.class);
        when(resourcer.getId()).thenReturn(1L);
        ResourcerUpdateCommand resourcerUpdateCommand = mock(ResourcerUpdateCommand.class);
        when(provider.update(resourcerUpdateCommand)).thenReturn(resourcer);

        Resourcer resultResourcer = resourcerManager.update(resourcerUpdateCommand);
        assertEquals(resourcer, resultResourcer);
    }

    @Test
    void clearCache() {
        ResourcerCreateCommand command = ResourcerCreateCommand.of("name", "url", "description", RDBMS, "username", "password", "adaptorName");
        Resourcer result = mock(Resourcer.class);
        when(result.getId()).thenReturn(1L);
        when(provider.create(any(ResourcerCreateCommand.class))).thenReturn(result);
        resourcerManager.create(command);
        assertTrue(resourcerManager.isCached(1L));

        resourcerManager.clearCache();

        assertFalse(resourcerManager.isCached(1L));
    }
}