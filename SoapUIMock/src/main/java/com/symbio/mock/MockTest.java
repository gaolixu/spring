package com.symbio.mock;
import java.io.IOException;

import javax.swing.JComponent;

import org.apache.xmlbeans.XmlException;

import com.eviware.soapui.config.MockResponseConfig;
import com.eviware.soapui.impl.WsdlInterfaceFactory;
import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.mock.DispatchException;
import com.eviware.soapui.impl.wsdl.mock.WsdlMockOperation;
import com.eviware.soapui.impl.wsdl.mock.WsdlMockResponse;
import com.eviware.soapui.impl.wsdl.mock.WsdlMockService;
import com.eviware.soapui.impl.wsdl.mock.dispatch.MockOperationDispatcher;
import com.eviware.soapui.model.mock.MockRequest;
import com.eviware.soapui.model.mock.MockResponse;
import com.eviware.soapui.model.mock.MockResult;
import com.eviware.soapui.support.SoapUIException;

public class MockTest {
	public static void main(String[] args) throws Exception {
		
		WsdlProject project = new WsdlProject();
		WsdlInterface iface = WsdlInterfaceFactory.importWsdl(project, "https://dev1-stw.ii-apps.com/SalesTax/services/salesTaxPassThroughManager?wsdl", true)[0];
		WsdlMockService service = project.addNewMockService("myMock");
		service.setPort(8089);
		WsdlMockOperation mockOperation = (WsdlMockOperation) service.addNewMockOperation(iface.getOperationByName("SalesTaxQuoteLookup"));
		//mockOperation.addMockResponse(response);
		//mockOperation.
		/*MockResponseConfig mrc = new MockResponseConfig;
		mrc.set
		mockOperation.addNewMockResponse(responseConfig);*/
		mockOperation.setDispatchStyle("SCRIPT");
		mockOperation.setScript("return 'response2'");
		/*MockOperationDispatcher mod = new MockOperationDispatcher() {
			
			@Override
			public void release() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public MockResponse selectMockResponse(MockRequest paramMockRequest, MockResult paramMockResult)
					throws DispatchException {
				System.out.println(paramMockRequest.getRequestContent());
				return null;
			}
			
			@Override
			public void releaseEditorComponent() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean hasDefaultResponse() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public JComponent getEditorComponent() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		mockOperation.setDispatcher(mod);*/
		WsdlMockResponse response = mockOperation.addNewMockResponse("response1", true);
		response.setResponseContent("ddddddddddddddddddddddddd");
		
		WsdlMockResponse response2 = mockOperation.addNewMockResponse("response2", true);
		response2.setResponseContent("2222222222222222");
		service.start();
		System.out.println(response.getResponseContent());
	}
}
