/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @author Alexander Y. Kleymenov
 * @version $Revision$
 */

package org.apache.harmony.xnet.provider.jsse;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class acts like a filtered input stream: it takes
 * the bytes from another InputStream.
 */
public class SSLStreamedInput extends SSLInputStream {

    private InputStream in;

    public SSLStreamedInput(InputStream in) {
        this.in = in;
    }

    public int available() throws IOException {
        return in.available();
    }

    /**
     * Read an opaque value from the stream.
     * @return the value read from the underlying stream.
     * @throws IOException if the data could not be read from
     * the underlying stream
     * @throws org.apache.harmony.xnet.provider.jsse.EndOfSourceException if the end of the underlying
     * stream has been reached.
     */
    public int read() throws IOException {
        int res = in.read();
        if (res < 0) {
            throw new EndOfSourceException();
        }
        return res;
    }
}

