/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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

package java.nio.channels;


/**
 * Thrown when a call is made to register a channel on a selector that has been
 * created by a different provider.
 * 
 */
public class IllegalSelectorException extends IllegalArgumentException {

	private static final long serialVersionUID = -8406323347253320987L;

	/**
	 * Default constructor.
	 * 
	 */
	public IllegalSelectorException() {
		super();
	}

}
