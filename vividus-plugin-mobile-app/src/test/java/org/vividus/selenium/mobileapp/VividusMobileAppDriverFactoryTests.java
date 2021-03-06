/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vividus.selenium.mobileapp;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.jbehave.core.model.Scenario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.vividus.bdd.context.IBddRunContext;
import org.vividus.bdd.model.MetaWrapper;
import org.vividus.bdd.model.RunningStory;
import org.vividus.selenium.IGenericWebDriverFactory;
import org.vividus.selenium.VividusWebDriver;
import org.vividus.selenium.manager.IWebDriverManagerContext;

@ExtendWith(MockitoExtension.class)
class VividusMobileAppDriverFactoryTests
{
    @Mock private IBddRunContext bddRunContext;
    @Mock private IWebDriverManagerContext webDriverManagerContext;
    @Mock private IGenericWebDriverFactory driverFactory;
    @InjectMocks private VividusMobileAppDriverFactory factory;

    @Test
    void testConfigureVividusWebDriver()
    {
        DesiredCapabilities capabilities = mock(DesiredCapabilities.class);
        WebDriver webDriver = mock(WebDriver.class);
        VividusWebDriver vividusWebDriver = mock(VividusWebDriver.class);

        when(vividusWebDriver.getDesiredCapabilities()).thenReturn(capabilities);
        when(driverFactory.getRemoteWebDriver(capabilities)).thenReturn(webDriver);

        factory.configureVividusWebDriver(vividusWebDriver);

        verify(vividusWebDriver).setWebDriver(webDriver);
        verify(vividusWebDriver).setRemote(true);
    }

    @Test
    void testSetDesiredCapabilities()
    {
        DesiredCapabilities capabilities = mock(DesiredCapabilities.class);
        RunningStory runningStory = mock(RunningStory.class);
        Scenario scenario = mock(Scenario.class);
        MetaWrapper metaWrapper = mock(MetaWrapper.class);

        factory.setDesiredCapabilities(capabilities, runningStory, scenario, metaWrapper);

        verifyNoInteractions(capabilities, runningStory, scenario, metaWrapper, driverFactory, webDriverManagerContext,
                bddRunContext);
    }
}
