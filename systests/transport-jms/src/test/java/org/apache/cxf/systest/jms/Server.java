/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cxf.systest.jms;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.testutil.common.AbstractBusTestServerBase;
import org.apache.cxf.testutil.common.EmbeddedJMSBrokerLauncher;

public class Server extends AbstractBusTestServerBase {
    public static final String PORT = allocatePort(Server.class);
    
    EmbeddedJMSBrokerLauncher broker;
    public Server(EmbeddedJMSBrokerLauncher b) {
        broker = b;
    }
    
    protected void run()  {
        Bus bus = BusFactory.getDefaultBus();
        setBus(bus);
        
        broker.updateWsdl(bus, "testutils/hello_world_doc_lit.wsdl");
        broker.updateWsdl(bus, "testutils/jms_test.wsdl");
        broker.updateWsdl(bus, "testutils/jms_test_mtom.wsdl");
        
        Endpoint.publish(null, new GreeterImplDoc());

        String address = null;
        Endpoint.publish(address, new GreeterImplTwoWayJMS());
        Endpoint.publish(null, new GreeterImplQueueOneWay());
        Endpoint.publish(null, new GreeterImplTopicOneWay());
        Endpoint.publish(null, new GreeterByteMessageImpl());
        Endpoint.publish(null, new SoapService6SoapPort6Impl());
        Endpoint.publish(null, new JmsDestPubSubImpl());

        Endpoint.publish(null, new SoapService7SoapPort7Impl());
        Endpoint.publish(null, new GreeterImplTwoWayJMSAppCorrelationIDNoPrefix());
        Endpoint.publish(null, new GreeterImplTwoWayJMSAppCorrelationIDStaticPrefixEng());
        Endpoint.publish(null, new GreeterImplTwoWayJMSAppCorrelationIDStaticPrefixSales());
        Endpoint.publish(null, new GreeterImplTwoWayJMSRuntimeCorrelationIDDynamicPrefix());
        Endpoint.publish(null, new GreeterImplTwoWayJMSRuntimeCorrelationIDStaticPrefixEng());
        Endpoint.publish(null, new GreeterImplTwoWayJMSRuntimeCorrelationIDStaticPrefixSales());
        Endpoint.publish(null, new GreeterImplTwoWayJMSAppCorrelationIDEng());
        Endpoint.publish(null, new GreeterImplTwoWayJMSAppCorrelationIDSales());

    }

}
