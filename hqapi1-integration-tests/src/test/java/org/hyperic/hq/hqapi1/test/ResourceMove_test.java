/*
 *
 * NOTE: This copyright does *not* cover user programs that use HQ
 * program services by normal system calls through the application
 * program interfaces provided as part of the Hyperic Plug-in Development
 * Kit or the Hyperic Client Development Kit - this is merely considered
 * normal use of the program, and does *not* fall under the heading of
 * "derived work".
 *
 * Copyright (C) [2008, 2009], Hyperic, Inc.
 * This file is part of HQ.
 *
 * HQ is free software; you can redistribute it and/or modify
 * it under the terms version 2 of the GNU General Public License as
 * published by the Free Software Foundation. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 *
 */

package org.hyperic.hq.hqapi1.test;

import org.hyperic.hq.hqapi1.ResourceApi;
import org.hyperic.hq.hqapi1.types.StatusResponse;
import org.hyperic.hq.hqapi1.types.Resource;

import java.util.List;

public class ResourceMove_test extends ResourceTestBase {

    public ResourceMove_test(String name) {
        super(name);
    }

    public void testResourceMoveInvalidResources() throws Exception {

        ResourceApi api = getApi().getResourceApi();

        Resource target = new Resource();
        target.setId(Integer.MAX_VALUE);

        Resource destination = new Resource();
        destination.setId(Integer.MAX_VALUE);                

        StatusResponse response = api.moveResource(target, destination);
        hqAssertFailureObjectNotFound(response);

    }

    public void testResourceMoveIncompatibleResources() throws Exception {

        ResourceApi api = getApi().getResourceApi();
        Resource platform = getLocalPlatformResource(false, true);

        List<Resource> servers = platform.getResource();

        assertTrue("More than 2 servers not found for platform " + platform.getName(),
                   servers.size() >= 2);

        Resource r1 = servers.get(0);
        Resource r2 = servers.get(1);

        // Attempt move of one server into another

        StatusResponse response = api.moveResource(r1, r2);
        hqAssertFailureInvalidParameters(response);  
    }
}
