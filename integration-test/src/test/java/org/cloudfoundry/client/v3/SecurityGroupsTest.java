/*
 * Copyright 2013-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.client.v3;

import org.cloudfoundry.CloudFoundryVersion;
import org.cloudfoundry.IfCloudFoundryVersion;
import org.cloudfoundry.AbstractIntegrationTest;
import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.client.v3.securitygroups.CreateSecurityGroupRequest;
import org.cloudfoundry.client.v3.securitygroups.CreateSecurityGroupResponse;
import org.cloudfoundry.client.v3.securitygroups.Rule;
import org.cloudfoundry.util.JobUtils;
import org.cloudfoundry.util.PaginationUtils;
import org.cloudfoundry.util.ResourceUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.cloudfoundry.client.v3.securitygroups.Protocol.TCP;
import static org.cloudfoundry.util.tuple.TupleUtils.function;

public final class SecurityGroupsTest extends AbstractIntegrationTest {

        @Autowired
        private CloudFoundryClient cloudFoundryClient;

        @Test
        public void create() {
                String securityGroupName = this.nameFactory.getSecurityGroupName();

                this.cloudFoundryClient.securityGroupsV3()
                                .create(CreateSecurityGroupRequest.builder()
                                                .name(securityGroupName)
                                                .rule(Rule.builder()
                                                                .destination("0.0.0.0/0")
                                                                .log(false)
                                                                .ports("2048-3000")
                                                                .protocol(TCP)
                                                                .build())
                                                .build())
                                .map(response -> response.getName())
                                .as(StepVerifier::create)
                                .expectNext(securityGroupName)
                                .expectComplete()
                                .verify(Duration.ofMinutes(5));
        }

}