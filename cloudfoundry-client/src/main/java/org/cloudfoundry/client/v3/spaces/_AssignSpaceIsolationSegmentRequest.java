/*
 * Copyright 2013-2017 the original author or authors.
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

package org.cloudfoundry.client.v3.spaces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.cloudfoundry.Nullable;
import org.cloudfoundry.client.v3.Relationship;
import org.immutables.value.Value;

/**
 * The request payload for the Assign Isolation Segment operation
 */
@Value.Immutable
abstract class _AssignSpaceIsolationSegmentRequest {

    /**
     * The isolation segment to assign
     */
    @JsonProperty("data")
    @Nullable
    abstract Relationship getData();

    /**
     * The space id
     */
    @JsonIgnore
    abstract String getSpaceId();

}