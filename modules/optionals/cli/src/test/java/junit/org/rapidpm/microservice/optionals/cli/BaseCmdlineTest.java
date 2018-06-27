/**
 * Copyright © 2013 Sven Ruppert (sven.ruppert@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package junit.org.rapidpm.microservice.optionals.cli;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.rapidpm.ddi.DI;
import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.microservice.Main;
import org.rapidpm.microservice.MainUndertow;
import org.rapidpm.microservice.test.system.JunitExitRuntimeException;

import java.util.Optional;

public class BaseCmdlineTest {

  @BeforeAll
  public static void setUpClass() {
    final PortUtils portUtils = new PortUtils();

    System.setProperty(MainUndertow.REST_HOST_PROPERTY, "127.0.0.1");
    System.setProperty(MainUndertow.SERVLET_HOST_PROPERTY, "127.0.0.1");

    System.setProperty(MainUndertow.REST_PORT_PROPERTY, portUtils.nextFreePortForTest() + "");
    System.setProperty(MainUndertow.SERVLET_PORT_PROPERTY, portUtils.nextFreePortForTest() + "");
  }


  @BeforeEach
  public void setUp() throws Exception {
    DI.clearReflectionModel();
    DI.activatePackages(getClass().getPackage().getName());
  }


  @AfterEach
  public void tearDown() throws Exception {
    DI.clearReflectionModel();
  }


  protected int startAndGetExit(String[] args) throws Throwable {
    try {
      Main.deploy(Optional.of(args));
      Main.stop();
    } catch (JunitExitRuntimeException e){
      return e.exitCode;
    }
    Assertions.fail("Exit not called");
    return -1;
  }


}
