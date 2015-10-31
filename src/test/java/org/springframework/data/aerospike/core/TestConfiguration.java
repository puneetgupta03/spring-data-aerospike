/*
 * Copyright 2015 the original author or authors.
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
package org.springframework.data.aerospike.core;

import org.springframework.context.annotation.Bean;
import org.springframework.data.aerospike.Constants;
import org.springframework.data.aerospike.MyLogCallback;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Log;
import com.aerospike.client.policy.ClientPolicy;

public class TestConfiguration {

	public @Bean(destroyMethod = "close") AerospikeClient aerospikeClient() {

		ClientPolicy policy = new ClientPolicy();
		policy.failIfNotConnected = true;
		policy.timeout = Constants.AS_TIMEOUT;
		
		Log.Callback mycallback = new MyLogCallback();
		Log.setCallback(mycallback);
		Log.setLevel(Log.Level.DEBUG);

		return new AerospikeClient(policy, Constants.AS_CLUSTER, Constants.AS_PORT); //AWS us-east
	}

	public @Bean AerospikeTemplate aerospikeTemplate() {
		return new AerospikeTemplate(aerospikeClient(), "test"); // TODO verify correct place for namespace
	}
	
}
