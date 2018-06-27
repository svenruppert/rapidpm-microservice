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
package org.rapidpm.microservice.rest.optionals.admin;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import static org.rapidpm.microservice.Main.stop;

@Path("/admin/basicadministration")
public class BasicAdministration {

  @GET()
  @Path("{timeout}")
  @Produces("text/plain")
  public String shutdownNow(@PathParam("timeout") final String timeout) {
    if (timeout == null || timeout.isEmpty()) {
      stop();
    } else {
      try {
        final Integer integer = Integer.valueOf(timeout);
        stop(integer);
      } catch (NumberFormatException e) {
        stop();
      }
    }
    return "code OK";
  }
}
