package com.intelie.iem;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
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

import javax.portlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class DashboardPortlet extends GenericPortlet {

    private static final String NORMAL_VIEW = "/normal.jsp";
    private static final String MAXIMIZED_VIEW = "/maximized.jsp";
    private static final String HELP_VIEW = "/help.jsp";

    private PortletRequestDispatcher normalView;
    private PortletRequestDispatcher maximizedView;
    private PortletRequestDispatcher helpView;

    // async requests and responses are processed here
    public void serveResource(ResourceRequest req, ResourceResponse resp) throws PortletException, IOException {
        System.out.println(">>> SERVE RESOURCE");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.print("teste!!!!");
        writer.close();
    }

    // parent page delivery
    public void render(RenderRequest renderRequest, RenderResponse
            renderResponse) throws PortletException, IOException {

//        System.out.println(">>> DO VIEW");
//        if (WindowState.MINIMIZED.equals(request.getWindowState())) {
//            return;
//        }
//
//        if (WindowState.NORMAL.equals(request.getWindowState())) {
//            normalView.include(request, response);
//        } else {
//            maximizedView.include(request, response);
//        }

        System.out.println(">>> rENDER" + renderResponse.createResourceURL());
        PortletContext context = getPortletContext();
        PortletRequestDispatcher rd = context.getRequestDispatcher(NORMAL_VIEW);
        rd.include(renderRequest, renderResponse);
    }

}
