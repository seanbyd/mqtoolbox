/**
 MIT License

Copyright (c) 2023 bdsaen

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

package com.mqtoolbox.conn;

import com.ibm.mq.MQQueueManager;
import com.ibm.mq.MQException;
import com.ibm.mq.constants.MQConstants;

/**
 * Bind connection to a queue manager running on the same machine
 *
 */
public class BindConnection {

	public static void main(String args[]) {
		BindConnection conn = new BindConnection();
		MQQueueManager qmgr = null;

		System.out.println("Bind connection");
		try {
			qmgr = conn.connect("QMGR1");
		} catch (MQException e) {
			System.out.println(
					String.format("MQ error details: %s(%s)\n\n%s", MQConstants.lookupReasonCode(e.getReason()), e.getReason(), e.getCause()));
		} finally {
			if (qmgr != null) {
				try {
					qmgr.disconnect();
				} catch (MQException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Return a queue manager object containing a connection to the queue manager
	 * 
	 * @param qmgrName Name of queue manager to connect to
	 * @return
	 * @throws MQException
	 */
	public final MQQueueManager connect(String qmgrName) throws MQException {
		return new MQQueueManager(qmgrName);
	}
}
