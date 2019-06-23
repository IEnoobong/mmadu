package com.mmadu.tokenservice.populators;

import com.mmadu.tokenservice.services.AppTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.mmadu.tokenservice.utilities.DomainAuthenticationConstants.ADMIN_TOKEN_ID;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AdminTokenInitializerTest {

    @Mock
    private AppTokenService appTokenService;

    @InjectMocks
    private AdminTokenInitializer adminTokenInitializer = new AdminTokenInitializer();

    @Test
    public void initializeAdminToken() {
        adminTokenInitializer.initializeAdminToken();

        verify(appTokenService, times(1)).generateToken(ADMIN_TOKEN_ID);
    }
}