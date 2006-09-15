/* Copyright 1998, 2002 The Apache Software Foundation or its licensors, as applicable
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

package java.net;


/**
 * This interface defines a factory for socket implementations. It is used by
 * the classes <code>Socket</code> and <code>ServerSocket</code> to create
 * socket implementations.
 */
public interface SocketImplFactory {
	/**
	 * Creates a new <code>SocketImpl</code> instance.
	 * 
	 * @return SocketImpl
	 */
	SocketImpl createSocketImpl();
}
