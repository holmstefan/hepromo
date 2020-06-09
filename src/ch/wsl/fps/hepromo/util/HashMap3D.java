/*******************************************************************************
 * Copyright 2020 Stefan Holm
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
 ******************************************************************************/
package ch.wsl.fps.hepromo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Stefan Holm
 *
 */
public class HashMap3D<K1,K2,K3,V> {
	
	private final Map<K1, Map<K2, Map<K3, V>>> internalMap = new HashMap<K1, Map<K2, Map<K3, V>>>();
	
	
	public void add(K1 key1, K2 key2, K3 key3, V value) {

		//get map associated with key1
		if (internalMap.containsKey(key1) == false) {
			internalMap.put(key1, new HashMap<K2, Map<K3,V>>());
		}
		Map<K2, Map<K3, V>> key1Map = internalMap.get(key1);
		

		//get map associated with key2
		if (key1Map.containsKey(key2) == false) {
			key1Map.put(key2, new HashMap<K3,V>());
		}
		Map<K3, V> key2Map = key1Map.get(key2);
		
		
		//store value
		key2Map.put(key3, value);
	}
	
	
	
	/**
	 * Returns the value associated with the given keys
	 * or null, if no such value exists.
	 */
	public V get(K1 key1, K2 key2, K3 key3) {

		//get map associated with key1
		Map<K2, Map<K3, V>> key1Map = internalMap.get(key1);
		if (key1Map == null) {
			return null;
		}
		

		//get map associated with key2
		Map<K3, V> key2Map = key1Map.get(key2);
		if (key2Map == null) {
			return null;
		}
		
		
		//get value
		return key2Map.get(key3);
	}

}
